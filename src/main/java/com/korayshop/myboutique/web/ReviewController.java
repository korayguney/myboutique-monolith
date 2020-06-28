package com.korayshop.myboutique.web;

import com.korayshop.myboutique.service.ReviewService;
import com.korayshop.myboutique.web.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.korayshop.myboutique.web.utility.Web.API;

@RequiredArgsConstructor
@RestController
@RequestMapping(API + "/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    public List<ReviewDto> findAll() {
        return this.reviewService.findAll();
    }

    @GetMapping("/{id}")
    public ReviewDto findById(@PathVariable Long id) {
        return this.reviewService.findById(id);
    }

    @PostMapping
    public ReviewDto create(@RequestBody ReviewDto reviewDto) {
        return this.reviewService.create(reviewDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.reviewService.delete(id);
    }
}