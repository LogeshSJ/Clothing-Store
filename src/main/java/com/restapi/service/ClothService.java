package com.restapi.service;

import com.restapi.dto.ClothDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Category;
import com.restapi.model.Cloth;
import com.restapi.repository.CategoryRepository;
import com.restapi.repository.ClothRepository;
import com.restapi.request.ClothRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClothService {

    @Autowired
    private ClothDto clothDto;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ClothRepository clothRepository;

    public List<Cloth> findAll() {
        return clothRepository.findAll();
    }

    @Transactional
    public List<Cloth> createCloth(ClothRequest clothRequest) {
        Cloth cloth = clothDto.mapToCloth(clothRequest);
        Category category = categoryRepository.findById(clothRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("CategoryId",
                        "CategoryId", clothRequest.getCategoryId()));
        cloth.setCategory(category);
        clothRepository.save(cloth);
        return findAll();
    }

    @Transactional
    public List<Cloth> updateCloth(ClothRequest clothRequest) {
        Cloth cloth = clothDto.mapToCloth(clothRequest);
        Category category = categoryRepository.findById(clothRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("CategoryId",
                        "CategoryId", clothRequest.getCategoryId()));
        cloth.setCategory(category);
        clothRepository.save(cloth);
        return findAll();
    }

    public List<Cloth> deleteById(Integer id) {
        clothRepository.deleteById(Long.valueOf(id));
        return findAll();
    }

}
