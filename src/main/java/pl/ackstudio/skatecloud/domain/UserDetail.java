package pl.ackstudio.skatecloud.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class UserDetail {

    private static final long      serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private              Long      id;
    @OneToOne
    private final        User      user;
    private              String    fullname;
    private              String    address;
    private              String    city;
    private              State     state;
    private              String    zip;
    private              LocalDate birthDate;
    private              String    phoneNumber;
}
