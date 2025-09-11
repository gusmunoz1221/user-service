package com.mcsv.user.external.service;

import com.mcsv.user.response.RatingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name =  "RATING-SERVICE")
public interface RatingService {

    @PostMapping
    public ResponseEntity<RatingDto> saveRating(RatingDto ratingDto);
    @PutMapping("ratings/{ratingId}")
    public ResponseEntity<RatingDto> updateRating(@PathVariable String ratingId);
    @DeleteMapping("ratings/{ratingId}")
    public ResponseEntity<Void> deleteRating(@PathVariable String ratingId);
}
