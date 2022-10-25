package pl.mclojek.fishy.view.fish;

import lombok.Getter;
import lombok.Setter;
import pl.mclojek.fishy.entity.Fish;
import pl.mclojek.fishy.model.FishEditModel;
import pl.mclojek.fishy.service.FishService;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

@ViewScoped
@Named
public class FishEdit implements Serializable {

    private final FishService service;

    @Setter
    @Getter
    private Long id;

    @Getter
    private FishEditModel fish;

    @Getter
    @Setter
    private Part portrait;

    @Inject
    public FishEdit(FishService service) {
        this.service = service;
    }

    public void init() throws IOException {
        Optional<Fish> fish = service.find(id);
        if (fish.isPresent()) {
            this.fish = FishEditModel.entityToModelMapper().apply(fish.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext()
                    .responseSendError(HttpServletResponse.SC_NOT_FOUND, "Fish not found");
        }
    }

    public String saveAction() {
        service.update(FishEditModel.modelToEntityUpdater().apply(service.find(id).orElseThrow(), fish));
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true&includeViewParams=true";
    }
}
