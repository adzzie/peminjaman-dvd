package com.astra.peminjaman.repository;

import com.astra.peminjaman.entity.Pelanggan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PelangganRepository extends JpaRepository<Pelanggan, Long> {
    Optional<Pelanggan> findByNama(String nama);
}
