package com.giftr.gift;

import com.giftr.gift.Gift;
import org.springframework.data.repository.CrudRepository;

public interface GiftRepository extends CrudRepository<Gift, Long> {}
