package com.giftr.controller;

import com.giftr.appuser.Gifter;
import com.giftr.gifting.GiftingService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
