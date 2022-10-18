package pl.mclojek.fishy.configuration;

import pl.mclojek.fishy.common.util.Sha256Utility;
import pl.mclojek.fishy.entity.User;
import pl.mclojek.fishy.service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.ZoneId;

@ApplicationScoped
public class Initializer {

    private final UserService userService;

    @Inject
    public Initializer(UserService userService) {
        this.userService = userService;
    }

    public void contextInitialized(@Observes @Initialized(ApplicationScoped.class) Object init) {
        init();
    }

    private synchronized void init() {
        User u1 = User.builder()
                .id(1)
                .username("CrazyAngler")
                .email("crazy@angler.com")
                .password(Sha256Utility.hash("fishing123"))
                .createdAt(LocalDateTime.now(ZoneId.systemDefault()))
                .experience(3)
                .build();

        User u2 = User.builder()
                .id(2)
                .username("Casual")
                .email("casual1234@fish.pl")
                .password(Sha256Utility.hash("password"))
                .createdAt(LocalDateTime.now(ZoneId.systemDefault()))
                .experience(1)
                .build();

        User admin = User.builder()
                .id(3)
                .username("admin")
                .email("admin@admin.com")
                .password(Sha256Utility.hash("admin"))
                .createdAt(LocalDateTime.now(ZoneId.systemDefault()))
                .experience(10)
                .build();

        User u3 = User.builder()
                .id(4)
                .username("randomName")
                .email("random@gmail.com")
                .password(Sha256Utility.hash("randomPassword"))
                .createdAt(LocalDateTime.now(ZoneId.systemDefault()))
                .experience(8)
                .build();


        userService.create(u1);
        userService.create(u2);
        userService.create(u3);
        userService.create(admin);

    }
}
