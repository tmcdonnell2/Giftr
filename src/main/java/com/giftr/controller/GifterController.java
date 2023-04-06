package com.giftr.controller;

import com.giftr.model.Gifter;
import com.giftr.service.GiftingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/gifters")
public class GifterController {

    private final GiftingService giftingService;

    public GifterController(GiftingService giftingService) {
        this.giftingService = giftingService;
    }

    @GetMapping
    public String getGifters(Model model) {
        List<Gifter> gifters = giftingService.getGifters();
        model.addAttribute("gifters", gifters);
        return "gifters";
    }
}
