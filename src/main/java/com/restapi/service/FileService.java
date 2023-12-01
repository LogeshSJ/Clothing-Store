package com.restapi.service;

import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Category;
import com.restapi.model.Cloth;
import com.restapi.repository.CategoryRepository;
import com.restapi.repository.ClothRepository;
import com.restapi.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import javax.persistence.Access;
import javax.xml.stream.Location;
import java.io.File;
import java.io.IOException;
@Service
public class FileService {
    @Autowired
    StorageService storageService;

    @Autowired
    ClothRepository clothRepository;

    public File getFile(long id) throws IOException {
        Cloth cloth = clothRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id", "id", id));
        Resource resource = storageService.loadFileAsResource(cloth.getPhoto());
        return resource.getFile();
    }
}