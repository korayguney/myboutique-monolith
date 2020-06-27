package com.korayshop.myboutique.repository;

import com.korayshop.myboutique.domain.Product;
import com.korayshop.myboutique.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
}
