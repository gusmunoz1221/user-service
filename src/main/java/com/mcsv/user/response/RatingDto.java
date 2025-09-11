package com.mcsv.user.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatingDto {
   private String id;
   private String userId;
   private String hotelId;
   private int rating;
   private String observations;
   private HotelDto hotelDto;
}
