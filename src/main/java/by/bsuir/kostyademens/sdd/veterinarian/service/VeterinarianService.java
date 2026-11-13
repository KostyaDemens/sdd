package by.bsuir.kostyademens.sdd.veterinarian.service;

import by.bsuir.kostyademens.sdd.veterinarian.dto.VeterinarianRequest;
import by.bsuir.kostyademens.sdd.veterinarian.dto.VeterinarianResponse;
import by.bsuir.kostyademens.sdd.veterinarian.entity.Veterinarian;
import by.bsuir.kostyademens.sdd.veterinarian.mapper.VeterinarianMapper;
import by.bsuir.kostyademens.sdd.veterinarian.repository.VeterinarianRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VeterinarianService {

    private final VeterinarianRepository repository;
    private final VeterinarianMapper mapper;

    public VeterinarianService(VeterinarianRepository repository, VeterinarianMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public VeterinarianResponse create(VeterinarianRequest request) {
        Veterinarian veterinarian = mapper.toEntity(request);
        return mapper.toResponse(repository.save(veterinarian));
    }

    @Transactional(readOnly = true)
    public List<VeterinarianResponse> findAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public VeterinarianResponse findById(Long id) {
        return mapper.toResponse(findEntity(id));
    }

    public VeterinarianResponse update(Long id, VeterinarianRequest request) {
        Veterinarian veterinarian = findEntity(id);
        mapper.updateEntity(request, veterinarian);
        return mapper.toResponse(veterinarian);
    }

    public void delete(Long id) {
        repository.delete(findEntity(id));
    }

    private Veterinarian findEntity(Long id) {
        return repository.findById(id).orElseThrow(() -> new VeterinarianNotFoundException(id));
    }
}
