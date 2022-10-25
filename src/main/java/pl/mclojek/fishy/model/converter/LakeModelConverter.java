package pl.mclojek.fishy.model.converter;

import pl.mclojek.fishy.entity.Lake;
import pl.mclojek.fishy.model.LakeModel;
import pl.mclojek.fishy.service.LakeService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import java.util.Optional;

@FacesConverter(forClass = LakeModel.class, managed = true)
public class LakeModelConverter implements Converter<LakeModel> {

    private LakeService service;

    @Inject
    public LakeModelConverter(LakeService service) {
        this.service = service;
    }

    @Override
    public LakeModel getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println(value);
        if (value == null || value.isBlank()) {
            return null;
        }
        Optional<Lake> lake = service.find(Long.parseLong(value));
        System.out.println(lake);
        return lake.isEmpty() ? null : LakeModel.entityToModelMapper().apply(lake.get());
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, LakeModel value) {
        System.out.println(value);
        return value == null ? "" : Long.toString(value.getId());
    }

}
