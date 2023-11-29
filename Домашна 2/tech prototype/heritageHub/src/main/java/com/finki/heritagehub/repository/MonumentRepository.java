package com.finki.heritagehub.repository;

import com.finki.heritagehub.model.Monument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MonumentRepository extends JpaRepository<Monument, Long> {
    List<Monument> findAllByHistoricIsTrue();
    List<Monument> findAllByCulturalIsTrue();
}
