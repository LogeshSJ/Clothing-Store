package com.restapi.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CartRequest {
    private Long userId;
    private Long clothId;
    @NotNull
    @Min(value=1,message = "count must not be empty")
    private Integer count;
}
