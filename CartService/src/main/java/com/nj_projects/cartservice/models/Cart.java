package com.nj_projects.cartservice.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Cart {
    private Long id;
    private Long userId;
    private LocalDate date;

    private List<Product> products;


}
