package com.proinsight.domain.repository.impl;

import com.proinsight.domain.model.SnackShop;
import com.proinsight.domain.repository.SnackShopRepository;
import com.proinsight.domain.repository.SnackShopRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SnackShopRepositoryQueriesImpl implements SnackShopRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    @Lazy
    private SnackShopRepository snackShopRepository;

    @Override
    public List<SnackShop> find(String name) {
        var builder = manager.getCriteriaBuilder();

        var criteria = builder.createQuery(SnackShop.class);
        var root = criteria.from(SnackShop.class);

        var predicates = new ArrayList<Predicate>();

        if (StringUtils.hasText(name)) {
            predicates.add(builder.like(root.get("nome"), "%" + name + "%"));
        }

        criteria.where(predicates.toArray(new Predicate[0]));

        var query = manager.createQuery(criteria);
        return query.getResultList();
    }

}
