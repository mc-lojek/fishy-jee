package pl.mclojek.fishy.service;

import lombok.NoArgsConstructor;
import pl.mclojek.fishy.entity.User;
import pl.mclojek.fishy.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@NoArgsConstructor
public class UserService {

    private UserRepository repository;

    @Inject
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<User> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Optional<User> find(Long id) {
        return repository.find(id);
    }

    @Transactional
    public void create(User user) {
        repository.create(user);
    }

    @Transactional
    public void updateAvatar(Long id, InputStream is) {
        repository.find(id).ifPresent(user -> {
            try {
                user.setAvatar(is.readAllBytes());
                repository.update(user);
            } catch (IOException ex) {
                throw new IllegalStateException(ex);
            }
        });
    }

    @Transactional
    public void deleteAvatar(Long id) {
        repository.find(id).ifPresent(user -> {
            user.setAvatar(null);
            repository.update(user);
        });
    }
}
