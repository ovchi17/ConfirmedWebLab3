package aca98b.web3lv2;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Objects;

@Named
@ApplicationScoped
public class YBean implements Serializable {

    private Float value;
    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public void yChecker(FacesContext fC, UIComponent uC, Object val) {
        if (val == null) {
            System.out.println("soon");
        }
    }
}