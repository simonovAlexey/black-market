package com.simonov.blackMarket.entity;

import lombok.*;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Data
public class Ad implements Identifiable<Integer> {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type {

        BUY,

        SELL

    }

    @Column(nullable = false)
    private BigInteger amount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    public enum Currency {

        USD,

        EUR

    }

    @Column(nullable = false)
    private BigDecimal rate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Location location;

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Location {

        @Column(nullable = false)
        private String city;

        private String area;

    }

    private String comment;

    @Lob
    @Column(name = "published", nullable = false)
    private LocalDateTime publishedAt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.NEW;

    public enum Status {

        NEW,

        PUBLISHED,

        EXPIRED

    }

    public Ad publish() {
        if (status == Status.NEW) {
            publishedAt = LocalDateTime.now();
            status = Status.PUBLISHED;
        }
        else {
            throw new IllegalStateException("Ad is already published");
        }
        return this;
    }

    public Ad expire() {
        if (status == Status.PUBLISHED) {
            status = Status.EXPIRED;
        }
        else {
            throw new IllegalStateException(
                    "Ad can be expire only when it is in the " + Status.PUBLISHED + " state");
        }
        return this;
    }


}
