package com.korayshop.myboutique.repository;

import com.korayshop.myboutique.domain.Cart;
import com.korayshop.myboutique.domain.Customer;
import com.korayshop.myboutique.domain.enumeration.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    //List<Cart> findAllByEnabled(Boolean enabled);
}
