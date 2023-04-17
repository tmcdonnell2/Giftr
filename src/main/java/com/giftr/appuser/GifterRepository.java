package com.giftr.appuser;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface GifterRepository extends CrudRepository<Gifter, Long> {
    public List<Gifter> findAll();
    public Optional<Gifter> findById(@Param(value="id") long id);
    public Optional<Gifter> findByEmail(@Param(value = "email") String username);

    @Transactional
    @Modifying
    @Query("""
            Update Gifter g \
            SET g.enabled = TRUE WHERE g.email = ?1"""
    )
    int enableAppUser(String email);
}
