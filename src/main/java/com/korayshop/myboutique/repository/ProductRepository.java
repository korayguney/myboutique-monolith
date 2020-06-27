package com.korayshop.myboutique.repository;

import com.korayshop.myboutique.domain.Payment;
import com.korayshop.myboutique.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
