package com.mcsv.user.response;

import com.mcsv.user.entities.Rating;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDtoResponse {
    private String name;
    private String email;
    private List<Rating> ratings = new ArrayList<>();
}
