package com.korayshop.myboutique.repository;

import com.korayshop.myboutique.domain.OrderItem;
import com.korayshop.myboutique.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
