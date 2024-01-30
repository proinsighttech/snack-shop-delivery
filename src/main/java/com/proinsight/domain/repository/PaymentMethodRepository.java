package com.proinsight.domain.repository;

import com.proinsight.domain.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

    List<PaymentMethod> findListByDescriptionContaining(String description);

    Optional<PaymentMethod> findByDescription(String description);

    boolean existsByDescription(String description);

}