package com.example.demo.model.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {

    private Long no;
    private String name;
    private String shortDescription;
    private String listImageUrl;
    private Integer salesPrice;
    private Integer discountedPrice;

}
