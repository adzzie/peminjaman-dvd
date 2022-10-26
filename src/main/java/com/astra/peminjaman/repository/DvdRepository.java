package com.astra.peminjaman.repository;

import com.astra.peminjaman.entity.Dvd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DvdRepository extends JpaRepository<Dvd, Long> {
    Optional<Dvd> findByJudulFilm(String judulFilm);
}
