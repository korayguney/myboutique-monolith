package com.korayshop.myboutique.service;


import com.korayshop.myboutique.domain.Cart;
import com.korayshop.myboutique.domain.Customer;
import com.korayshop.myboutique.domain.Order;
import com.korayshop.myboutique.domain.enumeration.CartStatus;
import com.korayshop.myboutique.repository.CartRepository;
import com.korayshop.myboutique.repository.CustomerRepository;
import com.korayshop.myboutique.web.dto.CartDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class CartService {
    @Autowired
    private final CartRepository cartRepository;
    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final OrderService orderService;

    public static CartDto mapToDto(Cart cart) {
        if (cart != null) {
            return new CartDto(
                    cart.getId(),
                    cart.getOrder().getId(),
                    CustomerService.mapToDto(cart.getCustomer()),
                    cart.getStatus().name()
            );
        }
        return null;
    }

    public List<CartDto> findAll() {
        log.debug("Request to get all Carts");
        return this.cartRepository.findAll().stream().map(CartService::mapToDto).collect(Collectors.toList());
    }

    public List<CartDto> findAllActiveCarts() {
        log.debug("Request to get all ACTIVE Carts");
        return this.cartRepository.findByStatus(CartStatus.NEW).stream().map(CartService::mapToDto).collect(Collectors.toList());
    }

    public CartDto create(Long customerId) {
        if (this.getActiveCart(customerId) == null) {
            Customer customer = this.customerRepository.findById(customerId).orElseThrow(() -> new IllegalStateException("The customer does not exist!!!"));
            Cart cart = new Cart(null, customer, CartStatus.NEW);
            Order order = this.orderService.create(cart);
            cart.setOrder(order);

            return mapToDto(this.cartRepository.save(cart));
        } else {
            throw new IllegalStateException("There is already an active cart");
        }
    }

    @Transactional(readOnly = true)
    public CartDto findById(Long id) {
        log.debug("Request to get Cart : {}", id);
        return this.cartRepository.findById(id).map(CartService::mapToDto).orElse(null);
    }

    public void delete(Long id) {
        log.debug("Request to delete Cart : {}", id);
        Cart cart = this.cartRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("Cannot find cart with id " + id));

        cart.setStatus(CartStatus.CANCELED);
        this.cartRepository.save(cart);
    }

    public CartDto getActiveCart(Long customerId) {
        List<Cart> carts = this.cartRepository.findByStatusAndCustomerId(CartStatus.NEW, customerId);
        if (carts != null) {
            if (carts.size() == 1) {
                return mapToDto(carts.get(0));
            } else if (carts.size() > 1) {
                throw new IllegalStateException("Many active carts are detected !!!");
            }
        }
        return null;
    }

}
