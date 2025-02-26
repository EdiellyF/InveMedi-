package com.br.InveMedi.inveMedi.repositories;

import com.br.InveMedi.inveMedi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByCargo(String cargo);
}
