package com.teamfinder.domain.authorization;

import com.teamfinder.client.transfer.RegistrationCommand;
import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Entity
@Table(name = "ACCOUNT")
@Getter
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "validation.email.syntax")
    @Column(nullable = false,unique = true, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(name="ENABLED",nullable = false)
    private Boolean isEnabled;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID",nullable = false)
    private Role role;

    @OneToOne(mappedBy = "account",cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private ConfirmationToken confirmationToken;

    public Account() {
    }

    Account(RegistrationCommand registrationCommand, Role role) {
        this.confirmationToken = ConfirmationToken.generateConfirmToken(this);
        this.email = registrationCommand.getEmail();
        this.password = new BCryptPasswordEncoder().encode(registrationCommand.getPassword());
        this.isEnabled = false;
        this.role = role;
    }

    void activeUser() {
        this.isEnabled = true;
    }

    static UserDetails createUserDetails(Account account) {
        return org.springframework.security.core.userdetails.User
                .withUsername(account.getEmail())
                .password(account.getPassword())
                .roles(account.getRole().getName().toString())
                .build();
    }
}
