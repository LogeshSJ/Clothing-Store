package com.restapi.dto;

import com.restapi.model.Category;
import com.restapi.model.Cloth;
import com.restapi.request.ClothRequest;
import com.restapi.response.ClothResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClothDto {
    public ClothResponse mapToClothResponse(List<Cloth> clothList) {
        return new ClothResponse(clothList);
    }

    public Cloth mapToCloth(ClothRequest clothRequest) {
        Cloth cloth = new Cloth();
        if (clothRequest.getId() != null) {
            cloth.setId(clothRequest.getId());
        }
        Category category = new Category();
        category.setId(clothRequest.getCategoryId());
        cloth.setPrice(clothRequest.getPrice());
        cloth.setDescription(clothRequest.getDescription());
        cloth.setTitle(clothRequest.getTitle());
//        cloth.setPhoto(clothRequest.getPhoto());
        cloth.setCategory(category);
        return cloth;
    }

}
