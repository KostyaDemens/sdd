package by.bsuir.kostyademens.sdd.veterinarian.repository;

import by.bsuir.kostyademens.sdd.veterinarian.entity.Veterinarian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeterinarianRepository extends JpaRepository<Veterinarian, Long> {
}
