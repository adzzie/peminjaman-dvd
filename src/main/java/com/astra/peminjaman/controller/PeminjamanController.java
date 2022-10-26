package com.astra.peminjaman.controller;

import com.astra.peminjaman.entity.Pelanggan;
import com.astra.peminjaman.entity.Peminjaman;
import com.astra.peminjaman.repository.DvdRepository;
import com.astra.peminjaman.repository.PelangganRepository;
import com.astra.peminjaman.repository.PeminjamanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequestMapping(value = {"/peminjaman",""})
public class PeminjamanController {
    @Autowired
    private PeminjamanRepository peminjamanRepository;

    @Autowired
    private PelangganRepository pelangganRepository;

    @Autowired
    private DvdRepository dvdRepository;

    @GetMapping
    public String list(ModelMap mm, @PageableDefault Pageable pageable){
        mm.addAttribute("datas",peminjamanRepository.findAll(pageable));
        return "peminjaman/list";
    }

    @GetMapping("/form")
    public String form(ModelMap mm){
        mm.addAttribute("dataPelanggan", pelangganRepository.findAll());
        mm.addAttribute("dataDvd", dvdRepository.findAll());
        mm.addAttribute("data", Peminjaman.builder().tanggalPeminjaman(LocalDateTime.now()).build());
        return "peminjaman/form";
    }

    @PostMapping
    @Transactional
    public String simpan(@Valid @ModelAttribute("data") Peminjaman peminjaman, BindingResult bind, RedirectAttributes redir, ModelMap mm){
        System.out.println("bind : " + bind.toString());
        System.out.println("peminjaman : " + peminjaman.toString());
//        try {
//        LocalDateTime dt = LocalDateTime.from(peminjaman.getTanggalPeminjaman().toLocalTime());
            if (bind.hasErrors()) {
//                throw new Exception("Error : " + bind.getFieldError());
                mm.addAttribute("dataPelanggan", pelangganRepository.findAll());
                mm.addAttribute("dataDvd", dvdRepository.findAll());
                mm.addAttribute("data",peminjaman);
                return "peminjaman/form";
            }
            peminjamanRepository.save(peminjaman);
            redir.addFlashAttribute("success", "Success saved data!");
            return "redirect:/peminjaman";
//        }catch (Exception e){
//            mm.addAttribute("dataPelanggan", pelangganRepository.findAll());
//            mm.addAttribute("dataDvd", dvdRepository.findAll());
//            mm.addAttribute("data",peminjaman);
//            mm.addAttribute("error", e.getMessage());
//            return "peminjaman/form";
//        }
    }
}
