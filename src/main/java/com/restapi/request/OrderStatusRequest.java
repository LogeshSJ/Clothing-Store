package com.restapi.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderStatusRequest {
    @NotEmpty
    private Long orderId;
    @NotEmpty
    private Long statusId;

}
