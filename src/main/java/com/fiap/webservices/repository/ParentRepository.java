package com.fiap.webservices.repository;

import com.fiap.webservices.domain.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Integer> {
    @Query("SELECT p FROM Parent p WHERE p.cpf = :cpf")
    Optional<Parent> findParentByCpf(@Param("cpf") String cpf);

}