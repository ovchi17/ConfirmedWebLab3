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
public class XBean implements Serializable {

    private String value = "0";
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void xChecker(FacesContext fC, UIComponent uC, Object val) {
        if (val == null) {
            FacesMessage message = new FacesMessage("write X value!");
            throw new ValidatorException(message);
        }

        String strVal = val.toString().trim();
        if (!strVal.matches("-?\\d+(\\.\\d+)?")) {
            FacesMessage message = new FacesMessage("X is not a valid number!");
            throw new ValidatorException(message);
        }

        double x = Double.parseDouble(strVal);
        if (x < -5 || x > 5) {
            FacesMessage message = new FacesMessage("X must be from -5 to 5!");
            throw new ValidatorException(message);
        }
    }



}
