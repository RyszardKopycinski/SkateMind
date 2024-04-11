package pl.ackstudio.skatecloud._development;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import pl.ackstudio.skatecloud.domain.StatusInfo;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class ProgressNote {

    private final LocalDate date;
    private final String     description;
    private final StatusInfo statusInfo;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private       Long       id;
}
