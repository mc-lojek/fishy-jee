package pl.mclojek.fishy.view.lake;

import lombok.Getter;
import lombok.Setter;
import pl.mclojek.fishy.entity.Lake;
import pl.mclojek.fishy.model.FishModel;
import pl.mclojek.fishy.model.LakeModel;
import pl.mclojek.fishy.model.LakesModel;
import pl.mclojek.fishy.service.FishService;
import pl.mclojek.fishy.service.LakeService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

@ViewScoped
@Named
public class LakeView implements Serializable {

    private final LakeService lakeService;
    private final FishService fishService;

    @Setter
    @Getter
    private Long id;

    @Getter
    private LakeModel lake;

    @Inject
    public LakeView(LakeService lakeService, FishService fishService) {
        this.lakeService = lakeService;
        this.fishService = fishService;
    }

    public void init() throws IOException {
        Optional<Lake> lake = lakeService.find(id);
        if (lake.isPresent()) {
            this.lake = LakeModel.entityToModelMapper().apply(lake.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext()
                    .responseSendError(HttpServletResponse.SC_NOT_FOUND, "Character not found");
        }
    }

    public String deleteAction(FishModel fish, Long lakeId) {
        fishService.delete(fish.getId());
        return "lake_view?faces-redirect=true&includeViewParams=true";
    }
}
