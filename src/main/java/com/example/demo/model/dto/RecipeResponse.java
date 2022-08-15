package com.example.demo.model.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RecipeResponse {

    private Long id;
    private String name;
    private String cursor;
    private List<ProductResponse> productList;

    @QueryProjection
    public RecipeResponse(Long id, String name, String cursor){
        this.id = id;
        this.name = name;
        this.cursor = cursor;
    }
}
