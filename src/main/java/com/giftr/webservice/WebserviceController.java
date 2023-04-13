package com.giftr.webservice;

import com.giftr.gift.Gift;
import com.giftr.appuser.Gifter;
import com.giftr.gifting.Gifting;
import com.giftr.gifting.GiftingService;
import com.giftr.util.DateUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WebserviceController {
    private final GiftingService giftingService;

    public WebserviceController(GiftingService giftingService) {
        this.giftingService = giftingService;
    }

    @GetMapping(path="giftings/{date}")
    public List<Gifting> getGiftingsByDate(@RequestParam(value="date", required=false) String dateString) {
        LocalDate date = DateUtil.getDate(dateString);
        return giftingService.getGiftingForDate(date);
    }

    @GetMapping(path="giftings")
    public List<Gifting> getGiftings() {
        return giftingService.getGiftings();
    }

    @GetMapping(path="/gifters")
    public List<Gifter> getGifters() {
        return giftingService.getGifters();
    }

    @GetMapping(path="/gifts")
    public List<Gift> getGifts() {
        List<Gift> gifts = new ArrayList<>();
        giftingService.getGifts().forEach(gifts::add);
        return gifts;
    }
}
