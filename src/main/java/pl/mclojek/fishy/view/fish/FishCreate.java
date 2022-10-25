package pl.mclojek.fishy.view.fish;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import pl.mclojek.fishy.model.FishCreateModel;
import pl.mclojek.fishy.model.LakeModel;
import pl.mclojek.fishy.model.LakesModel;
import pl.mclojek.fishy.service.FishService;
import pl.mclojek.fishy.service.LakeService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@ViewScoped
@Named
public class FishCreate implements Serializable {

    private FishService service;

    private LakeService lakeService;

    @Setter
    @Getter
    private Long id;

    @Getter
    private FishCreateModel fish;

    @Getter
    private List<LakeModel> lakes;

    @Inject
    public FishCreate(FishService service, LakeService lakeService) {
        this.service = service;
        this.lakeService = lakeService;
    }

    @PostConstruct
    public void init() {
        lakes = lakeService.findAll().stream()
                .map(LakeModel.entityToModelMapper())
                .collect(Collectors.toList());

        fish = FishCreateModel.builder().build();
    }

    public String cancelAction() {
        return "/lake/lake_list.xhtml?faces-redirect=true";
    }

    public String saveAction() {
        System.out.println(fish.getLake().getId());
        service.create(FishCreateModel.modelToEntityMapper(
                lakeId -> lakeService.find(lakeId).orElseThrow()).apply(fish));
        return "/lake/lake_list.xhtml?faces-redirect=true";
    }
}
