package by.bsuir.kostyademens.sdd.veterinarian.dto;

public record VeterinarianResponse(
        Long id,
        String fullName,
        String specialization,
        String phoneNumber,
        String email
) {
}
