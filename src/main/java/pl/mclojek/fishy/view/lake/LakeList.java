package pl.mclojek.fishy.view.lake;

import pl.mclojek.fishy.model.LakesModel;
import pl.mclojek.fishy.service.LakeService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@RequestScoped
@Named
public class LakeList implements Serializable {

    private final LakeService service;

    private LakesModel lakes;

    @Inject
    public LakeList(LakeService service) {
        this.service = service;
    }

    public LakesModel getLakes() {
        if(lakes == null) {
            lakes = LakesModel.entityToModelMapper().apply(service.findAll());
        }
        return lakes;
    }

    public String deleteAction(LakesModel.Lake lake) {
        service.delete(lake.getId());
        return "lake_list?faces-redirect=true";
    }
}
