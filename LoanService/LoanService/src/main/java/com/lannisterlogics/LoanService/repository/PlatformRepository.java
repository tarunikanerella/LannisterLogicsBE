package com.lannisterlogics.LoanService.repository;

import com.lannisterlogics.LoanService.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformRepository extends JpaRepository<Platform, Long> {
}
