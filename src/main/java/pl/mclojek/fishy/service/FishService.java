package pl.mclojek.fishy.service;

import lombok.NoArgsConstructor;
import pl.mclojek.fishy.entity.Fish;
import pl.mclojek.fishy.repository.FishRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@NoArgsConstructor
public class FishService {

    private FishRepository repository;

    @Inject
    public FishService(FishRepository repository) {
        this.repository = repository;
    }

    public List<Fish> findAll() {
        return repository.findAll();
    }

    public Optional<Fish> find(Long id) {
        return repository.find(id);
    }

    public void create(Fish fish) {
        repository.create(fish);
    }

    public void update(Fish fish) {
        repository.update(fish);
    }

    public void delete(Long id) {
        repository.delete(find(id).orElseThrow());
    }


}
