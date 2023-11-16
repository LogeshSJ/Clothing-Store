package com.restapi.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryRequest {
    private Long id;
    @NotEmpty
    @Size(min=2,message="title must not empty")
    private String title;

    public CategoryRequest(String title) {
        this.title = title;
    }

}
