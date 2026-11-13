package by.bsuir.kostyademens.sdd.veterinarian.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record VeterinarianRequest(
        @NotBlank String fullName,
        @NotBlank String specialization,
        @NotBlank String phoneNumber,
        @NotBlank @Email String email
) {
}
