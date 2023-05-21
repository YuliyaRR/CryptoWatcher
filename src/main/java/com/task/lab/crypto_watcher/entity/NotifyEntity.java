package com.task.lab.crypto_watcher.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(schema = "app", name = "user_crypto")
public class NotifyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_uuid")
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "crypto_id")
    private CryptoEntity crypto;
    @Column(name = "basic_rate")
    private double basicRate;

    public NotifyEntity() {
    }

    public NotifyEntity(UserEntity user, CryptoEntity crypto, double basicRate) {
        this.user = user;
        this.crypto = crypto;
        this.basicRate = basicRate;
    }

    public long getId() {
        return id;
    }

    public UserEntity getUser() {
        return user;
    }

    public CryptoEntity getCrypto() {
        return crypto;
    }

    public double getBasicRate() {
        return basicRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotifyEntity that = (NotifyEntity) o;
        return id == that.id
                && Double.compare(that.basicRate, basicRate) == 0
                && Objects.equals(user, that.user)
                && Objects.equals(crypto, that.crypto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, crypto, basicRate);
    }
}
