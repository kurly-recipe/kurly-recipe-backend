package com.example.demo.repository;

import com.example.demo.model.dto.ProductResponse;
import com.example.demo.model.dto.RecipeCondition;
import com.example.demo.model.dto.RecipeResponse;
import com.example.demo.model.entity.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeQueryRepositoryImpl extends QuerydslRepositorySupport implements  RecipeQueryRepository {


    @Value("${server.kurly.api}")
    private String kurlyApi;
    private final JPAQueryFactory queryFactory;

    @Autowired
    RecipeQueryRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Recipe.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<RecipeResponse> findAllRecipeByCondition(Pageable page, RecipeCondition recipeCondition, String cursor) {

        List<RecipeResponse> res = findRecipeList(page, recipeCondition, cursor);

        res.forEach(
                r -> r.setProductList(getProductList(r))
        );

        long total = res.size();
        return new PageImpl<>(res, page, total);

    }

    private List<RecipeResponse> findRecipeList(Pageable page, RecipeCondition recipeCondition, String cursor){
        return null;
    }


    private List<ProductResponse> getProductList(RecipeResponse recipe) {

        /** recipe 재료 성분 이용해서 kurly 검색 API 호출 시
         recipe.ingredients의 Lenth 만큼 아래 과정을 반복해야 함
         ingredient 마다 API keyword = ingredient , sort_type = 1 (판매량 순)으로 호출해서
         제일 처음 product를 prodcut 리스트에 추가
         **/

        List<ProductResponse> productList = new ArrayList<>();

        Map<String, Object> params = new HashMap<>();
        params.put("keyword", null); // null 자리에 recipe.ingredient 들어가야 함
        params.put("sort_type", 1);
        RestTemplate restTemplate = new RestTemplate();
        String response = "";

        try {
            response = restTemplate.getForObject(kurlyApi, String.class, params);
        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {

            }
        }

       //response.data 를 productList에 추가

        return productList;
    }
}
