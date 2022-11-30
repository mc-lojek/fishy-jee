package pl.mclojek.fishy.service;

import lombok.NoArgsConstructor;
import pl.mclojek.fishy.entity.Lake;
import pl.mclojek.fishy.repository.FishRepository;
import pl.mclojek.fishy.repository.LakeRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@NoArgsConstructor
public class LakeService {

    private LakeRepository repository;
    private FishRepository fishRepository;

    @Inject
    public LakeService(LakeRepository repository, FishRepository fishRepository) {
        this.repository = repository;
        this.fishRepository = fishRepository;
    }

    @Transactional
    public List<Lake> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Optional<Lake> find(Long id) {
        Optional<Lake> lake = repository.find(id);
        if(lake.isPresent()) {
            lake.get().setFishList(fishRepository.findAll().stream().filter(f -> f.getLake().getId() == id).collect(Collectors.toList()));
        }
        System.out.println(lake.get().getFishList().isEmpty());
        return lake;
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
        fishRepository.findAll().stream().filter(f -> f.getLake().getId() == id).forEach(f -> fishRepository.delete(f));
        repository.delete(find(id).orElseThrow());
    }



}
