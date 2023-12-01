package com.restapi.controller.admin;

import com.restapi.model.Category;
import com.restapi.model.Cloth;
import com.restapi.model.Role;
import com.restapi.request.ClothRequest;
import com.restapi.response.common.APIResponse;
import com.restapi.service.ClothService;
import com.restapi.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping("/api/admin/cloth")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
@RolesAllowed(Role.ADMIN)
public class AdminClothController {
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private ClothService clothService;

    @Autowired
    private StorageService storageService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllCloths() {
        List<Cloth> clothList = clothService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(clothList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

//

    @PostMapping
    public ResponseEntity<APIResponse> createCloth(@RequestBody ClothRequest clothRequest) {
        List<Cloth> clothList = clothService.createCloth(clothRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(clothList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }



    @PutMapping
    public ResponseEntity<APIResponse> updateCloth(@RequestBody ClothRequest clothRequest) {
        List<Cloth> clothList = clothService.updateCloth(clothRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(clothList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteCloth(@PathVariable Integer id) {
        List<Cloth> clothList = clothService.deleteById(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(clothList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/downloadFile/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) throws IOException {

        File file = clothService.getFile(id);

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body((Resource) resource);
    }
}

