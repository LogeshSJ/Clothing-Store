package com.restapi.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClothRequest {

    private Long id;
    private Long categoryId;
    @NotEmpty
    @Size(min=2,message="title must not empty")
    private String title;
    @NotEmpty
    @Size(min=2,message="description must not empty")
    private String description;
    @NotNull
    @Min(value=3,message = "price atleast have 3 character")
    private Double price;


//    private String photo;
}
