package com.giftr.service;

import com.giftr.model.Gift;
import com.giftr.model.Gifter;
import com.giftr.model.GiftingEvent;
import com.giftr.repository.GiftRepository;
import com.giftr.repository.GifterRepository;
import com.giftr.repository.GiftingEventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GiftingService {
    private final GifterRepository gifterRepository;
    private final GiftRepository giftRepository;
    private final GiftingEventRepository giftingEventRepository;

    public GiftingService(GifterRepository gifterRepository, GiftRepository giftRepository,
            GiftingEventRepository giftingEventRepository) {
        this.gifterRepository = gifterRepository;
        this.giftRepository = giftRepository;
        this.giftingEventRepository = giftingEventRepository;
    }

    public List<Gifting> getGiftings() {
        Iterable<GiftingEvent> giftingEvents = giftingEventRepository.findAll();
        List<Gifting> giftings = new ArrayList<>();
        giftingEvents.forEach(giftingEvent -> {
            Gifting gifting = new Gifting();
            gifting.setGift(giftingEvent.getGift());
            gifting.setGiftName(giftingEvent.getGift().getName());
            gifting.setGivers(giftingEvent.getGivers());
            gifting.setReceivers(giftingEvent.getReceivers());
            gifting.setOccasion(giftingEvent.getOccasion());
            gifting.setFavourite(giftingEvent.isFavorite());
            gifting.setDate(giftingEvent.getDate());
            giftings.add(gifting);
        });

        return giftings;
    }

    public List<Gifting> getGiftingForDate(LocalDate date) {
        Iterable<GiftingEvent> giftingEvents = giftingEventRepository.findAllByDate(date);
        List<Gifting> giftings = new ArrayList<>();
        giftingEvents.forEach(giftingEvent -> {
            Gifting gifting = new Gifting();
            gifting.setGift(giftingEvent.getGift());
            gifting.setGiftName(giftingEvent.getGift().getName());
            gifting.setGivers(giftingEvent.getGivers());
            gifting.setReceivers(giftingEvent.getReceivers());
            gifting.setOccasion(giftingEvent.getOccasion());
            gifting.setFavourite(giftingEvent.isFavorite());
            gifting.setDate(giftingEvent.getDate());
            giftings.add(gifting);
        });

        return giftings;
    }

    public List<Gifter> getGifters() {
        Iterable<Gifter> gifters = gifterRepository.findAll();
        List<Gifter> gifterList = new ArrayList<>();
        gifters.forEach(gifterList::add);
        gifterList.sort((o1,o2) -> {
            if (o1.getLastName().equals(o2.getLastName())) {
                return o1.getFirstName().compareTo(o2.getFirstName());
            }
            return o1.getLastName().compareTo(o2.getLastName());
        });
        return gifterList;
    }

    public Optional<Gifter> getGifterById(long id) {
        return gifterRepository.findById(id);
    }

    public Gifter add(Gifter gifter) {
        if (gifter == null) {
            throw new NullPointerException("Passed in Gifter cannot be null");
        }

        return gifterRepository.save(gifter);
    }

    public Iterable<Gift> getGifts() {
        return giftRepository.findAll();
    }

    public List<Gifting> getGiftingByReceivers(Gifter receiver) {
        Iterable<GiftingEvent> giftingEvents = giftingEventRepository.findAllByReceivers(receiver);
        List<Gifting> giftings = new ArrayList<>();
        giftingEvents.forEach(giftingEvent -> {
            Gifting gifting = new Gifting();
            gifting.setGift(giftingEvent.getGift());
            gifting.setGiftName(giftingEvent.getGift().getName());
            gifting.setGivers(giftingEvent.getGivers());
            gifting.setReceivers(giftingEvent.getReceivers());
            gifting.setOccasion(giftingEvent.getOccasion());
            gifting.setFavourite(giftingEvent.isFavorite());
            gifting.setDate(giftingEvent.getDate());
            giftings.add(gifting);
        });

        return giftings;
    }
}
