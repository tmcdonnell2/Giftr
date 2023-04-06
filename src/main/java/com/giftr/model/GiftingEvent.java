package com.giftr.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class GiftingEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany
    @JoinColumn(name = "gifter_id")
    private List<Gifter> givers;

    @ManyToMany
    @JoinColumn(name = "gifter_id")
    private List<Gifter> receivers;

    @OneToOne
    @JoinColumn(name = "gift_id")
    private Gift gift;

    private String occasion;
    private boolean favorite;
    private LocalDate date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public List<Gifter> getGivers() {
        return givers;
    }

    public void setGivers(List<Gifter> givers) {
        this.givers = givers;
    }

    public List<Gifter> getReceivers() {
        return this.receivers;
    }

    public void setReceivers(List<Gifter> receivers) {
        this.receivers = receivers;
    }

    public Gift getGift() {
        return gift;
    }

    public void setGift(Gift gift) {
        this.gift = gift;
    }
}
