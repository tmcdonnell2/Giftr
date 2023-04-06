package com.giftr.service;

import com.giftr.model.Gift;
import com.giftr.model.Gifter;

import java.time.LocalDate;
import java.util.List;

public class Gifting {
    private Gift gift;
    private List<Gifter> receivers;
    private List<Gifter> givers;

    private String giftName;
    private String occasion;
    private LocalDate date;

    private boolean favourite;

    public Gift getGift() {
        return gift;
    }

    public void setGift(Gift gift) {
        this.gift = gift;
    }

    public List<Gifter> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<Gifter> receivers) {
        this.receivers = receivers;
    }

    public List<Gifter> getGivers() {
        return givers;
    }

    public void setGivers(List<Gifter> givers) {
        this.givers = givers;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getOccasion() {
        return occasion;
    }

    public void setOccasion(String occasion) {
        this.occasion = occasion;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }
}
