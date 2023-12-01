package com.restapi.request;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressRequest {

    private Long id;
    @NotNull
    private Long userId;
    @NotEmpty
    private String address;
    @NotEmpty
    private String city;
    @NotNull
    private Integer zipcode;
}
