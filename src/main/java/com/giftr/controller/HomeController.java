package com.giftr.controller;

import com.giftr.model.Gifter;
import com.giftr.repository.GifterRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final GifterRepository gifterRepository;

    public HomeController(GifterRepository gifterRepository) {
        this.gifterRepository = gifterRepository;
    }

    @GetMapping
    public String getGifter(Model model, Principal user) {
        Optional<Gifter> gifterOptional =
                gifterRepository.findByEmail(user.getName());
        if (gifterOptional.isPresent()) {
            model.addAttribute("gifter", gifterOptional.get());
            return "home";
        }
        throw new IllegalStateException("Gifter was not found for home page");
    }
}
