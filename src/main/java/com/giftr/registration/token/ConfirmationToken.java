package com.giftr.registration.token;

import com.giftr.appuser.Gifter;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ConfirmationToken {

    @SequenceGenerator(
            name = "confirmation_token_sequence",
            sequenceName = "confirmation_token_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "confirmation_token_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "gifter_id"
    )
    private Gifter gifter;

    public ConfirmationToken() {}

    public ConfirmationToken(String token,
                            LocalDateTime createdAt,
                            LocalDateTime expiresAt,
                            Gifter gifter) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.gifter = gifter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiredAt() {
        return expiresAt;
    }

    public void setExpiredAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public LocalDateTime getConfirmedAt() {
        return confirmedAt;
    }

    public void setConfirmedAt(LocalDateTime confirmedAt) {
        this.confirmedAt = confirmedAt;
    }

    public Gifter getAppUser() {
        return gifter;
    }

    public void setAppUser(Gifter gifter) {
        this.gifter = gifter;
    }
}
