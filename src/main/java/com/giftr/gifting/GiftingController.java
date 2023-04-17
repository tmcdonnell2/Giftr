package com.giftr.gifting;

import com.giftr.appuser.Gifter;
import com.giftr.util.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

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
        Gifter receiver = giftingService.getGifterById(receiverId);
        List<Gifting> giftings = giftingService.getGiftingByReceiver(receiver);
        model.addAttribute("receivers_giftings", giftings);
        return "gifts_by_receiver";
    }
}
