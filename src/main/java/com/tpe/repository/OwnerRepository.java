package com.tpe.repository;

import com.tpe.domain.Owner;
import com.tpe.dto.OwnerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import java.util.Optional;

//@Repository okunabilirlik icin opsiyonel
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    boolean existsByEmail(String email);

    //3-c
    @Query("SELECT new com.tpe.dto.OwnerDto(o) FROM Owner o WHERE o.id=:pId")
    Optional<OwnerDto> findOwnerDTOById(@Param("pId") Long id);
}
