package com.korayshop.myboutique.repository;

import com.korayshop.myboutique.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByCartCustomer_Id(Long id);
}
