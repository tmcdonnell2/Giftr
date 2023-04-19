package com.giftr.gifting;

import com.giftr.appuser.Gifter;
import com.giftr.appuser.GifterRepository;
import com.giftr.gift.Gift;
import com.giftr.gift.GiftRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class GiftingService {
    private final GifterRepository gifterRepository;
    private final GiftRepository giftRepository;
    private final GiftingRepository giftingRepository;

    public GiftingService(GifterRepository gifterRepository, GiftRepository giftRepository,
            GiftingRepository giftingRepository) {
        this.gifterRepository = gifterRepository;
        this.giftRepository = giftRepository;
        this.giftingRepository = giftingRepository;
    }

    public List<Gifting> getGiftings() {
        Iterable<Gifting> giftingEvents = giftingRepository.findAll();
        List<Gifting> giftings = new ArrayList<>();
        giftingEvents.forEach(giftings::add);

        return giftings;
    }

    public List<Gifting> getGiftingForDate(LocalDate date) {
        Iterable<Gifting> giftingEvents = giftingRepository.findAllByDate(date);
        List<Gifting> giftings = new ArrayList<>();
        giftingEvents.forEach(giftings::add);

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

    public Gifter getGifterById(long id) {
        return gifterRepository.findById(id)
                    .orElseThrow(
                        () -> new IllegalStateException("Gifter could not be found with id")
                    );
    }

    public Gifter addGifter(Gifter gifter) {
        if (gifter == null) {
            throw new NullPointerException("Passed in Gifter cannot be null");
        }

        return gifterRepository.save(gifter);
    }

    public Gifting addGifting(Gifting gifting) {
        if (gifting == null) {
            throw new NullPointerException("Passed in gifting cannot be null");
        }

        return giftingRepository.save(gifting);
    }

    public Iterable<Gift> getGifts() {
        return giftRepository.findAll();
    }

    public List<Gifting> getGiftingByReceiver(Gifter receiver) {
        Iterable<Gifting> giftingEvents = giftingRepository.findAllByReceiver(receiver);
        List<Gifting> giftings = new ArrayList<>();
        giftingEvents.forEach(giftings::add);

        return giftings;
    }

    public Gifter getGifterByEmail(String email) {
        return gifterRepository.findByEmail(email)
            .orElseThrow(
                () -> new IllegalStateException(
                    String.format("Could not find user by email: %s", email))
            );
    }

    public List<Gifting> getGiversByReceiver(Gifter receiver) {
        return giftingRepository.findAllGiversByReceiver(receiver)
            .orElseThrow(
                () -> new IllegalStateException(
                        String.format("Could not find givers by receiver id %s",
                                receiver.getId())
                    )
                );
    }

    public void deleteGiftingById(Long giftingId) {
        giftingRepository.deleteById(giftingId);
    }
}
