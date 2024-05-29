package com.proinsight.domain.repository;

import com.proinsight.domain.model.User;

import java.util.Optional;

public interface UserRepository extends CustomJpaRepository<User, Long>{

    Optional<User> findByEmail(String email);

    Optional<User> findByCpf(String cpf);
}


