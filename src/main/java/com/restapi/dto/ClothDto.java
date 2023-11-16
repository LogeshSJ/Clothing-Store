package com.restapi.dto;

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
        cloth.setPrice(clothRequest.getPrice());
        cloth.setDescription(clothRequest.getDescription());
        cloth.setTitle(clothRequest.getTitle());
        cloth.setPhoto(clothRequest.getPhoto());
        return cloth;
    }

}
