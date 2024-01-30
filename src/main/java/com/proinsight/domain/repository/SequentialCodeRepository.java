package com.proinsight.domain.repository;

import com.proinsight.domain.model.SequentialCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface SequentialCodeRepository extends JpaRepository<SequentialCode, Long>{

    @Query("select sc from SequentialCode sc where sc.category = :category")
    Optional<SequentialCode> findByCategory(String category);

    @Modifying
    @Transactional
    @Query("UPDATE SequentialCode sc SET sc.code = :code WHERE sc.category = :category")
    void updateSequanceCode(String category, Long code);
}
