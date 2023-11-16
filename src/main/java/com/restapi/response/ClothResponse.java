package com.restapi.response;

import com.restapi.model.Cloth;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ClothResponse {
    private List<Cloth> clothList;
}
