package com.korayshop.myboutique.repository;

import com.korayshop.myboutique.domain.Category;
import com.korayshop.myboutique.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
