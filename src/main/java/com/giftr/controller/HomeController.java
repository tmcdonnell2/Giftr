package com.giftr.controller;

import com.giftr.appuser.Gifter;
import com.giftr.gifting.Gifting;
import com.giftr.gifting.GiftingService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final GiftingService giftingService;

    public HomeController(GiftingService giftingService) {
        this.giftingService = giftingService;
    }

    @GetMapping()
    public String getGifter(Model model, @AuthenticationPrincipal Gifter user) {
        Gifter gifter =
                giftingService.getGifterByEmail(user.getEmail());
        model.addAttribute("gifter", gifter);
        return "home";
    }


    @GetMapping("/add")
    public String getGivers(Model model, @AuthenticationPrincipal Gifter gifter) {
        List<Gifting> giftingEvents  =
                giftingService.getGiversByReceivers(List.of(gifter));
        Set<Gifter> giversSet = new HashSet<>();
        for (Gifting gifting : giftingEvents) {
            giversSet.addAll(gifting.getGivers());
        }

        List<Gifter> givers = giversSet.stream().toList();
        model.addAttribute("givers", givers);
        model.addAttribute("gifting", new Gifting());
        return "add";
    }

    @PostMapping("/add")
    public String addGift(@ModelAttribute Gifting gifting, @AuthenticationPrincipal
    Gifter gifter) {
        gifting.setReceivers(List.of(gifter));
        giftingService.addGifting(gifting);
        return "add";
    }
}
