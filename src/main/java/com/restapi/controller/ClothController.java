package com.restapi.controller;

import com.restapi.model.Cloth;
import com.restapi.model.Role;
import com.restapi.response.common.APIResponse;
import com.restapi.service.ClothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/cloth")
//@PreAuthorize("hasRole('ROLE_USER')")
@RolesAllowed(Role.USER)
public class ClothController {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private ClothService clothService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllcloths() {
        List<Cloth> clothList = clothService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(clothList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
