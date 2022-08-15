package com.example.demo.controller;

import com.example.demo.model.Header;
import com.example.demo.model.dto.RecipeCondition;
import com.example.demo.model.dto.RecipeResponse;
import com.example.demo.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private RecipeService recipeService;

    @Autowired
    RecipeController(RecipeService recipeService){
        this.recipeService = recipeService;
    }

    @GetMapping("")
    public Page<RecipeResponse> selectClub(Pageable page, RecipeCondition recipeCondition, String cursor){
        return recipeService.getRecipeList(page, recipeCondition, cursor);
    }



}
