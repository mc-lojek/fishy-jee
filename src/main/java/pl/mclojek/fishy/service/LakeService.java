package pl.mclojek.fishy.service;

import lombok.NoArgsConstructor;
import pl.mclojek.fishy.entity.Lake;
import pl.mclojek.fishy.repository.LakeRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@NoArgsConstructor
public class LakeService {

    private LakeRepository repository;

    @Inject
    public LakeService(LakeRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<Lake> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Optional<Lake> find(Long id) {
        return repository.find(id);
    }

    @Transactional
    public void create(Lake lake) {
        repository.create(lake);
    }

    @Transactional
    public void update(Lake lake) {
        repository.update(lake);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(find(id).orElseThrow());
    }



}
