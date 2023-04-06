package com.giftr.controller;

import com.giftr.model.Gifter;
import com.giftr.service.Gifting;
import com.giftr.service.GiftingService;
import com.giftr.util.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/giftings")
public class GiftingController {

    private final GiftingService giftingService;

    public GiftingController(GiftingService giftingService) {
        this.giftingService = giftingService;
    }

    @GetMapping
    public String getGiftingsByDate(@RequestParam(value = "date", required = false) String dateString, Model model) {
        LocalDate date = DateUtil.getDate(dateString);

        List<Gifting> giftings = giftingService.getGiftingForDate(date);
        model.addAttribute("giftings", giftings);
        return "giftings";
    }

    @GetMapping(path = "/user")
    public String getGiftingsByReceiver(@RequestParam(value = "id", required = true)
    long receiverId, Model model) {
        Optional<Gifter> receiver = giftingService.getGifterById(receiverId);
        if (receiver.isPresent()) {
            List<Gifting> giftings = giftingService.getGiftingByReceivers(receiver.get());
            model.addAttribute("receivers_giftings", giftings);
            return "gifts_by_receiver";
        }
        return "error";
    }
}
