package pl.ackstudio.skatecloud._development;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgressNotesRepository extends JpaRepository<ProgressNote, Long> {

}
