package pl.mclojek.fishy.common.datastore;

import lombok.extern.java.Log;
import pl.mclojek.fishy.common.util.CloningUtility;
import pl.mclojek.fishy.entity.Fish;
import pl.mclojek.fishy.entity.Lake;
import pl.mclojek.fishy.entity.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;
import java.util.stream.Collectors;

@Log
@ApplicationScoped
public class DataStore {

    private Set<User> users = new HashSet<>();
    private Set<Lake> lakes = new HashSet<>();
    private Set<Fish> fishes = new HashSet<>();

    //region user

    public synchronized Optional<User> findUser(long id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().map(CloningUtility::clone);
    }

    public synchronized List<User> findAllUsers() {
        return users.stream().map(CloningUtility::clone).collect(Collectors.toList());
    }

    public synchronized void createUser(User user) throws IllegalArgumentException {
        user.setId(findAllUsers().stream().mapToLong(User::getId).max().orElse(0) + 1);
        users.add(CloningUtility.clone(user));
    }

    public synchronized void updateUser(User user) throws IllegalArgumentException {
        findUser(user.getId()).ifPresentOrElse(original -> {
            users.remove(original);
            users.add(CloningUtility.clone(user));
        }, () -> {
            throw new IllegalArgumentException(String.format("No user with id \"%d\" found!", user.getId()));
        });
    }

    public synchronized void removeUser(Long id) throws IllegalArgumentException {
        findUser(id).ifPresentOrElse(original -> {
            users.remove(original);
        }, () -> {
            throw new IllegalArgumentException(String.format("No user with id \"%d\" found!", id));
        });
    }

    //endregion

    //region lake

    public synchronized List<Lake> findAllLakes() {
        return lakes.stream().map(CloningUtility::clone).collect(Collectors.toList());
    }

    public synchronized Optional<Lake> findLake(Long id) {
        return lakes.stream().filter(l -> l.getId() == id).findFirst().map(CloningUtility::clone);
    }

    public synchronized Optional<Lake> findLakeWithFishes(Long id) {
        List<Fish> lakeFishes = findFishesForLake(id);
        Optional<Lake> lake = lakes.stream().filter(l -> l.getId() == id).findFirst().map(CloningUtility::clone);
        lake.ifPresent(l -> l.setFishList((ArrayList<Fish>) lakeFishes));
        return lake;
    }

    public synchronized void createLake(Lake lake) {
        lake.setId(findAllLakes().stream().mapToLong(Lake::getId).max().orElse(0) + 1);
        lakes.add(CloningUtility.clone(lake));
    }

    public synchronized void updateLake(Lake lake) throws IllegalArgumentException {
        findLake(lake.getId()).ifPresentOrElse(original -> {
            lakes.remove(original);
            lakes.add(CloningUtility.clone(lake));
        }, () -> {
            throw new IllegalArgumentException(String.format("Lake with id \"%d\" not exists!", lake.getId()));
        });
    }

    public synchronized void removeLake(Long id) throws IllegalArgumentException {
        findLake(id).ifPresentOrElse(original -> {
            lakes.remove(original);

        }, () -> {
            throw new IllegalArgumentException(String.format("Lake with id \"%d\" not exists!", id));
        });
    }

    //endregion

    //region fish

    public synchronized List<Fish> findAllFishes() {
        return fishes.stream().map(CloningUtility::clone).collect(Collectors.toList());
    }

    public synchronized Optional<Fish> findFish(Long id) {
        return fishes.stream().filter(l -> l.getId() == id).findFirst().map(CloningUtility::clone);
    }

    public synchronized List<Fish> findFishesForLake(Long lakeId) {
        return fishes.stream().filter(f -> lakeId == f.getLake().getId()).map(CloningUtility::clone).collect(Collectors.toList());
    }

    public synchronized void createFish(Fish fish) {
        fish.setId(findAllFishes().stream().mapToLong(Fish::getId).max().orElse(0) + 1);
        fishes.add(CloningUtility.clone(fish));
    }

    public synchronized void updateFish(Fish fish) throws IllegalArgumentException {
        findFish(fish.getId()).ifPresentOrElse(original -> {
            fishes.remove(original);
            fishes.add(CloningUtility.clone(fish));
        }, () -> {
            throw new IllegalArgumentException(String.format("Fish with id \"%d\" not exists!", fish.getId()));
        });
    }

    public synchronized void removeFish(Long id) throws IllegalArgumentException {
        findFish(id).ifPresentOrElse(original -> {
            fishes.remove(original);
        }, () -> {
            throw new IllegalArgumentException(String.format("Fish with id \"%d\" not exists!", id));
        });
    }

    //endregion
}
