package pl.mclojek.fishy.service;

import lombok.NoArgsConstructor;
import pl.mclojek.fishy.entity.Fish;
import pl.mclojek.fishy.repository.FishRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@NoArgsConstructor
public class FishService {

    private FishRepository repository;

    @Inject
    public FishService(FishRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<Fish> findAll() {
        return repository.findAll();
    }

    @Transactional
    public List<Fish> findAllForLake(Long lakeId) {
        return repository.findAll().stream().filter(f -> f.getLake().getId() == lakeId).collect(Collectors.toList());
    }

    @Transactional
    public Optional<Fish> find(Long id) {
        return repository.find(id);
    }

    @Transactional
    public Optional<Fish> findForLake(Long id, Long lakeId) {

        return repository.find(id).filter(f -> f.getLake().getId() == lakeId);
    }
    @Transactional
    public void create(Fish fish) {
        repository.create(fish);
    }
    @Transactional
    public void update(Fish fish) {
        repository.update(fish);
    }
    @Transactional
    public void delete(Long id) {
        repository.delete(find(id).orElseThrow());
    }


}
