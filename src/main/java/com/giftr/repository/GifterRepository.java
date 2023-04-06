package com.giftr.repository;

import com.giftr.model.Gifter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GifterRepository extends CrudRepository<Gifter, Long> {
    public List<Gifter> findAll();
    public Optional<Gifter> findById(@Param(value="id") long id);
    public Optional<Gifter> findByEmail(@Param(value = "email") String username);
}
