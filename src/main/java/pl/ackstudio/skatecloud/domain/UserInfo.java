package pl.ackstudio.skatecloud.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class UserInfo {

    private static final long   serialVersionUID = 1l;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private              Long   id;
    @OneToOne
    private final        User   user;
    private final        String fullname;
    private final        String address;
    private final        String city;
    private final        States state;
    private final        String zip;
    private final        Date   birthDate;
    private final        int    phoneNmber;
}
