package com.giftr.gifting;

import com.giftr.appuser.Gifter;
import com.giftr.gift.Gift;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Gifting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private List<String> givers;

    @ManyToOne
    @JoinColumn(name = "gifter_id")
    private Gifter receiver;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gift_id")
    private Gift gift;

    private String occasion;
    private boolean favorite;
    private LocalDate date;

    private String comment;

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

    public void setFavourite(boolean favorite) {
        this.favorite = favorite;
    }

    public List<String> getGivers() {
        return givers;
    }

    public void setGivers(List<String> givers) {
        this.givers = givers;
    }

    public Gifter getReceiver() {
        return this.receiver;
    }

    public void setReceiver(Gifter receiver) {
        this.receiver = receiver;
    }

    public Gift getGift() {
        return gift;
    }

    public void setGift(Gift gift) {
        this.gift = gift;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
