package com.teamfinder.domain.authorization;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Table(name = "CONFIRMATION_TOKEN")
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE",nullable = false)
    private Date createdDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private Account account;

    public ConfirmationToken(){}

    public ConfirmationToken(Account account, Date createdDate, String token) {
        this.createdDate = createdDate;
        this.token = token;
        this.account = account;
    }

    static ConfirmationToken generateConfirmToken(Account account) {
        return new ConfirmationToken(account,new Date(), UUID.randomUUID().toString());
    }

}