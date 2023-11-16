package com.restapi.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressRequest {

    private Long id;
    @NotEmpty
    private Long userId;
    @NotEmpty
    @Size(min=2,message="address must not empty")
    private String address;
    @NotEmpty
    @Size(min=2,message="city must not empty")
    private String city;
    @NotEmpty
    @Size(min=2,message="zipcode must not empty")
    private Integer zipcode;
}
