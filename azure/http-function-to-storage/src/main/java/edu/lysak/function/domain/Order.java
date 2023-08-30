package edu.lysak.function.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String id;
    private String email;
    private List<Product> products;
    private OrderStatus status;
    private Boolean complete;
}
