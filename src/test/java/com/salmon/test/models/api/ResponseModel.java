package com.salmon.test.models.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

// Response Container model for Colors Model

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE) // Removes necessity to specify access modifier for every fields
public class ResponseModel {
    List<Colors> colors;

//Child Inner Class Colors
    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE) // Removes necessity to specify access modifier for every fields

    public class Colors {
        String colorSpaceId;
        String sensation;
        String hue;
        String name;
        String colorId;
        String uriFriendlyName;
        String rgb;
        String collection;
    }

}
