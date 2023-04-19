package com.giftr.gifting;

import com.giftr.appuser.Gifter;
import com.giftr.util.DateUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/home/giftings")
public class GiftingController {

    private final GiftingService giftingService;

    public GiftingController(GiftingService giftingService) {
        this.giftingService = giftingService;
    }

    @GetMapping("/date")
    public String getGiftingsByDate(@RequestParam(value = "date", required = false) String dateString, Model model) {
        LocalDate date = DateUtil.getDate(dateString);

        List<Gifting> giftings = giftingService.getGiftingForDate(date);
        model.addAttribute("giftings", giftings);
        return "giftings";
    }

    @GetMapping
    public String getGiftings(Model model, @AuthenticationPrincipal Gifter gifter) {
        List<Gifting> giftings  =
                giftingService.getGiversByReceiver(gifter);
        Set<String> giversSet = new HashSet<>();
        for (Gifting gifting : giftings) {
            giversSet.addAll(gifting.getGivers());
        }

        List<String> givers = giversSet.stream().toList();
        model.addAttribute("gifting", new Gifting());
        model.addAttribute("giftings", giftings);
        model.addAttribute("givers", givers);
        return "giftings";
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String addGifting(@ModelAttribute Gifting gifting,
            @AuthenticationPrincipal Gifter gifter,
            Model model) {

        gifting.setReceiver(gifter);
        try {
            giftingService.addGifting(gifting);
            for (String key : model.asMap().keySet()) {
                System.out.println(key);
            }
            model.addAttribute("success",true);
            model.addAttribute("message","Gift added!");
        } catch (Exception e) {
            model.addAttribute("error",true);
            model.addAttribute("message","An error occurred. Please try again later.");
        }
        return "giftings :: #giftings-table";
    }

    @DeleteMapping("/deleteGifting/{id}")
    public String deleteEmployee(@PathVariable("id") long giftingId) {
        giftingService.deleteGiftingById(giftingId);
        return "giftings :: #giftings-table";
    }
}
