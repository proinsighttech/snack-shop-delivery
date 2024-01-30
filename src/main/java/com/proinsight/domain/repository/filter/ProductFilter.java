package com.proinsight.domain.repository.filter;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductFilter {

    private String category;

    private Long snackShopId;

    private Boolean active;

}
