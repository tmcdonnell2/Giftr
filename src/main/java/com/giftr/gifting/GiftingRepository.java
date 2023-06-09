package com.giftr.gifting;

import com.giftr.appuser.Gifter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface GiftingRepository extends CrudRepository<Gifting, Long> {
    Iterable<Gifting> findAllByDate(@Param("date") LocalDate date);
    Iterable<Gifting> findAllByReceiver(Gifter receiver);
    Optional<List<Gifting>> findAllGiversByReceiver(Gifter receiver);
}
