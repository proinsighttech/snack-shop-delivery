package com.proinsight.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "sequential_code")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SequentialCode {

    @Id
    @EqualsAndHashCode.Include
    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Long code;

}
