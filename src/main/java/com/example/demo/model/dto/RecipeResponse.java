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
    private List<ProductResponse> productList;

    @QueryProjection
    public RecipeResponse(Long id){
        this.id = id;
    }
}
