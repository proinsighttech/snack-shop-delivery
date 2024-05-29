package com.proinsight.domain.repository;

import com.proinsight.domain.model.SnackShop;

import java.util.List;

public interface SnackShopRepositoryQueries {

    List<SnackShop> find(String nome);

}

