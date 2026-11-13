package by.bsuir.kostyademens.sdd.veterinarian.mapper;

import by.bsuir.kostyademens.sdd.veterinarian.dto.VeterinarianRequest;
import by.bsuir.kostyademens.sdd.veterinarian.dto.VeterinarianResponse;
import by.bsuir.kostyademens.sdd.veterinarian.entity.Veterinarian;
import org.springframework.stereotype.Component;

@Component
public class VeterinarianMapper {

    public Veterinarian toEntity(VeterinarianRequest request) {
        return new Veterinarian(
                request.fullName(),
                request.specialization(),
                request.phoneNumber(),
                request.email()
        );
    }

    public VeterinarianResponse toResponse(Veterinarian veterinarian) {
        return new VeterinarianResponse(
                veterinarian.getId(),
                veterinarian.getFullName(),
                veterinarian.getSpecialization(),
                veterinarian.getPhoneNumber(),
                veterinarian.getEmail()
        );
    }

    public void updateEntity(VeterinarianRequest request, Veterinarian veterinarian) {
        veterinarian.update(
                request.fullName(),
                request.specialization(),
                request.phoneNumber(),
                request.email()
        );
    }
}
