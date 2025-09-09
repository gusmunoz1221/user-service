package com.mcsv.user.external.service;

import com.mcsv.user.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name =  "RATING-SERVICE")
public interface RatingService {

    @PostMapping
    public ResponseEntity<Rating> saveRating(Rating rating);
    @PutMapping("ratings/{ratingId}")
    public ResponseEntity<Rating> updateRating(@PathVariable String ratingId);
    @DeleteMapping("ratings/{ratingId}")
    public ResponseEntity<Void> deleteRating(@PathVariable String ratingId);
}
