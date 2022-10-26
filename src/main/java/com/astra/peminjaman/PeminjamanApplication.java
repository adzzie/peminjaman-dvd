package com.astra.peminjaman;

import com.astra.peminjaman.entity.Dvd;
import com.astra.peminjaman.entity.Pelanggan;
import com.astra.peminjaman.repository.DvdRepository;
import com.astra.peminjaman.repository.PelangganRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class PeminjamanApplication {
	@Autowired
	private PelangganRepository pelangganRepository;

	@Autowired
	private DvdRepository dvdRepository;

	public static void main(String[] args) {
		SpringApplication.run(PeminjamanApplication.class, args);
	}

	@Bean
	public void createPelanggan(){
		List<Pelanggan> pelanggans = List.of(
				Pelanggan.builder().nama("aji").build(),
				Pelanggan.builder().nama("gojal").build()
		);
		for (Pelanggan pelanggan: pelanggans
			 ) {
			if(!pelangganRepository.findByNama(pelanggan.getNama()).isPresent()){
				pelangganRepository.save(pelanggan);
			}
		}
	}

	@Bean
	public void createDvd(){
		List<Dvd> dvds = List.of(
				Dvd.builder().judulFilm("Pocong Ganteng").build(),
				Dvd.builder().judulFilm("Suster Jalan kaki").build()
		);
		for (Dvd dvd: dvds
		) {
			if(!dvdRepository.findByJudulFilm(dvd.getJudulFilm()).isPresent()){
				dvdRepository.save(dvd);
			}
		}
	}

}
