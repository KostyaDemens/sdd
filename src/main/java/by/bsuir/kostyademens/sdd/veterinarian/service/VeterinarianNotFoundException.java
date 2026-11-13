package by.bsuir.kostyademens.sdd.veterinarian.service;

public class VeterinarianNotFoundException extends RuntimeException {
    public VeterinarianNotFoundException(Long id) {
        super("Veterinarian with id " + id + " was not found");
    }
}
