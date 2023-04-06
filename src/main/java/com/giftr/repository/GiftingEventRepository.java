package com.giftr.repository;

import com.giftr.model.Gifter;
import com.giftr.model.GiftingEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface GiftingEventRepository extends CrudRepository<GiftingEvent, Long> {
    Iterable<GiftingEvent> findAllByDate(@Param("date") LocalDate date);
    Iterable<GiftingEvent> findAllByReceivers(Gifter receiver);
}
