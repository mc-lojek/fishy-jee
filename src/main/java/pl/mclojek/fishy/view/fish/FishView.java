package pl.mclojek.fishy.view.fish;

import lombok.Getter;
import lombok.Setter;
import pl.mclojek.fishy.entity.Fish;
import pl.mclojek.fishy.entity.Lake;
import pl.mclojek.fishy.model.FishModel;
import pl.mclojek.fishy.model.LakeModel;
import pl.mclojek.fishy.service.FishService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RequestScoped
@Named
public class FishView {

    private final FishService service;

    @Getter
    @Setter
    private Long id;

    @Getter
    private FishModel fish;

    @Inject
    public FishView(FishService service) {
        this.service = service;
    }

    public void init() throws IOException {
        Optional<Fish> fish = service.find(id);
        if (fish.isPresent()) {
            this.fish = FishModel.entityToModelMapper().apply(fish.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext()
                    .responseSendError(HttpServletResponse.SC_NOT_FOUND, "Character not found");
        }
    }

}
