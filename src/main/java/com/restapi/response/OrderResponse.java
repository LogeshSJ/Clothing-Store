package com.restapi.response;

import com.restapi.model.Address;
import com.restapi.model.OrderedCloth;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Long id;
    private List<OrderedCloth> clothList;
    private Long userId;
    private String name;
    private String username;
    private Address address;
    private String orderStatus;
}
