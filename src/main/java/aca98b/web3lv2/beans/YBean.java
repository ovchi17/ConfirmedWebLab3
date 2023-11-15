package aca98b.web3lv2.beans;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Objects;

@Named
@SessionScoped
public class YBean implements Serializable {

    private String value;
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void yChecker(FacesContext fC, UIComponent uC, Object val) {
        if (val == null) {
            System.out.println("err");
        }
    }
}