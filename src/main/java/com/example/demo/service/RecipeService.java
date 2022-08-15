package com.example.demo.service;

import com.example.demo.model.Header;
import com.example.demo.model.dto.RecipeCondition;
import com.example.demo.model.dto.RecipeResponse;
import com.example.demo.repository.RecipeQueryRepository;
import com.example.demo.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeQueryRepository recipeQueryRepository;

    @Autowired
    RecipeService(RecipeRepository recipeRepository, RecipeQueryRepository recipeQueryRepository){
        this.recipeRepository = recipeRepository;
        this.recipeQueryRepository = recipeQueryRepository;
    }

    public Page<RecipeResponse> getRecipeList(Pageable page, RecipeCondition recipeCondition, String cursor){
        return recipeQueryRepository.findAllRecipeByCondition(page, recipeCondition, cursor);
    }
}
