package com.example.demo.repository;

import com.example.demo.model.dto.RecipeCondition;
import com.example.demo.model.dto.RecipeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecipeQueryRepository {

    Page<RecipeResponse> findAllRecipeByCondition(Pageable page, RecipeCondition recipeCondition, String customCursor);

}
