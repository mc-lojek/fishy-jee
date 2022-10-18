package pl.mclojek.fishy.common.datastore;

import lombok.extern.java.Log;
import pl.mclojek.fishy.common.util.CloningUtility;
import pl.mclojek.fishy.entity.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Log
@ApplicationScoped
public class DataStore {

    private Set<User> users = new HashSet<>();

    public synchronized Optional<User> findUser(long id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized List<User> findAllUsers() {
        return users.stream()
                .map(CloningUtility::clone)
                .collect(Collectors.toList());
    }

    public synchronized void createUser(User user) throws IllegalArgumentException {
        user.setId(findAllUsers().stream().mapToLong(User::getId).max().orElse(0) + 1);
        users.add(CloningUtility.clone(user));
    }

}
