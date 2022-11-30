package pl.mclojek.fishy.configuration;

import lombok.SneakyThrows;
import pl.mclojek.fishy.common.util.Sha256Utility;
import pl.mclojek.fishy.entity.Fish;
import pl.mclojek.fishy.entity.Lake;
import pl.mclojek.fishy.entity.User;
import pl.mclojek.fishy.enums.FishSpecies;
import pl.mclojek.fishy.service.FishService;
import pl.mclojek.fishy.service.LakeService;
import pl.mclojek.fishy.service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.context.control.RequestContextController;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;

@ApplicationScoped
public class Initializer {

    private final UserService userService;
    private final LakeService lakeService;
    private final FishService fishService;
    private final RequestContextController requestContextController;

    @Inject
    public Initializer(UserService userService, LakeService lakeService, FishService fishService, RequestContextController rcc) {
        this.userService = userService;
        this.lakeService = lakeService;
        this.fishService = fishService;
        this.requestContextController = rcc;
    }

    public void contextInitialized(@Observes @Initialized(ApplicationScoped.class) Object init) {
        init();
    }

    private synchronized void init() {

        requestContextController.activate();

        User u1 = User.builder()
                //.id(1)
                .username("CrazyAngler")
                .email("crazy@angler.com")
                .password(Sha256Utility.hash("fishing123"))
                .createdAt(LocalDateTime.now(ZoneId.systemDefault()))
                .experience(3)
                .avatar(getResourceAsByteArray("avatar/1.png"))
                .build();

        User u2 = User.builder()
                //.id(2)
                .username("Casual")
                .email("casual1234@fish.pl")
                .password(Sha256Utility.hash("password"))
                .createdAt(LocalDateTime.now(ZoneId.systemDefault()))
                .experience(1)
                .avatar(getResourceAsByteArray("avatar/2.png"))
                .build();

        User u3 = User.builder()
                //.id(4)
                .username("randomName")
                .email("random@gmail.com")
                .password(Sha256Utility.hash("randomPassword"))
                .createdAt(LocalDateTime.now(ZoneId.systemDefault()))
                .experience(8)
                .avatar(getResourceAsByteArray("avatar/3.png"))
                .build();

        User admin = User.builder()
                //.id(3)
                .username("admin")
                .email("admin@admin.com")
                .password(Sha256Utility.hash("admin"))
                .createdAt(LocalDateTime.now(ZoneId.systemDefault()))
                .experience(10)
                .avatar(getResourceAsByteArray("avatar/4.png"))
                .build();


        userService.create(u1);
        userService.create(u2);
        userService.create(u3);
        userService.create(admin);

        Lake lake1 = Lake.builder()
                //.id(1)
                .name("Jezioro Miłoszewskie")
                .area(26.4f)
                .isPublic(false)
                .latitude(54.445604)
                .longitude(18.042325)
                .build();

        Lake lake2 = Lake.builder()
                //.id(2)
                .name("Jezioro Jeleń")
                .area(106.33f)
                .isPublic(true)
                .latitude(54.200112)
                .longitude(17.528322)
                .build();

        lakeService.create(lake1);
        lakeService.create(lake2);

        Fish f1 = Fish.builder()
                //.id(1)
                .species(FishSpecies.COMMON_CARP)
                .catchDate(LocalDateTime.now())
                .length(123)
                .weight(12.3f)
                .lake(lakeService.find(5L).orElseThrow())
                .build();

        Fish f2 = Fish.builder()
                //.id(2)
                .species(FishSpecies.CATFISH)
                .catchDate(LocalDateTime.now())
                .length(1234)
                .weight(1.666f)
                .lake(lake1)
                .build();

        fishService.create(f1);
        fishService.create(f2);


        requestContextController.deactivate();
    }

    @SneakyThrows
    private byte[] getResourceAsByteArray(String name) {
        try (InputStream is = this.getClass().getResourceAsStream(name)) {
            return is.readAllBytes();
        }
    }
}
