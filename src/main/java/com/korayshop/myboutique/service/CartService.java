package com.korayshop.myboutique.service;


import com.korayshop.myboutique.domain.Cart;
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

    public List<CartDto> findAll(){
        log.debug("Request to get all Carts");
        return this.cartRepository.findAll().stream().map(CartService::mapToDto).collect(Collectors.toList());
    }

    public  List<CartDto> findAllActiveCarts(){
        log.debug("Request to get all ACTIVE Carts");
        return this.cartRepository.findByStatus(CartStatus.NEW).stream().map(CartService::mapToDto).collect(Collectors.toList());
    }

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

}
