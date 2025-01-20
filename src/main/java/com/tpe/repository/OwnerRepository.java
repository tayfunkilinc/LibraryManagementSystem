package com.tpe.repository;

import com.tpe.domain.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository okunabilirlik icin opsiyonel
public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
