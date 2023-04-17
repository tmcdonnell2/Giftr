package com.giftr.controller;

import com.giftr.appuser.Gifter;
import com.giftr.gifting.Gifting;
import com.giftr.gifting.GiftingService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/home/add")
public class AddController {

    private final GiftingService giftingService;

    public AddController(GiftingService giftingService) {
        this.giftingService = giftingService;
    }

    @GetMapping()
    public String getGivers(Model model, @AuthenticationPrincipal Gifter gifter) {
        List<Gifting> giftingEvents  =
                giftingService.getGiversByReceiver(gifter);
        Set<String> giversSet = new HashSet<>();
        for (Gifting gifting : giftingEvents) {
            giversSet.addAll(gifting.getGivers());
        }

        List<String> givers = giversSet.stream().toList();
        model.addAttribute("givers", givers);
        model.addAttribute("gifting", new Gifting());
        return "add";
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String addGifting(@ModelAttribute Gifting gifting,
            @AuthenticationPrincipal Gifter gifter) {
        gifting.setReceiver(gifter);
        giftingService.addGifting(gifting);
        return "forward:/home/add";
    }
}
