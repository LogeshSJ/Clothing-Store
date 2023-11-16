package com.restapi.controller.admin;

import com.restapi.model.Cloth;
import com.restapi.model.Role;
import com.restapi.request.ClothRequest;
import com.restapi.response.common.APIResponse;
import com.restapi.service.ClothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/cloth")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
@RolesAllowed(Role.ADMIN)
public class AdminClothController {
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private ClothService clothService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllCloths() {
        List<Cloth> clothList = clothService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(clothList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<APIResponse> createCloth(@Valid @RequestBody
                                                   ClothRequest clothRequest) {
        List<Cloth> clothList = clothService.createCloth(clothRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(clothList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<APIResponse> updateCloth(@Valid @RequestBody
                                                  ClothRequest clothRequest) {
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
}
