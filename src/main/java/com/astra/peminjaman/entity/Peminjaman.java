package com.astra.peminjaman.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Peminjaman {
    @Id @GeneratedValue
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_pelanggan", nullable = false)
    private Pelanggan pelanggan;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_dvd", nullable = false)
    private Dvd dvd;

    private LocalDateTime tanggalPeminjaman;
}
