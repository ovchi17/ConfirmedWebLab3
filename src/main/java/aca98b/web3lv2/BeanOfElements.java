package aca98b.web3lv2;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.event.AjaxBehaviorEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.inject.Named;


@Named
@ApplicationScoped
public class BeanOfElements implements Serializable {
    private OneElement element = new OneElement();;
    private List<OneElement> listOfElements = new ArrayList<OneElement>();;

    public void clear(){
        listOfElements.clear();
    }

}
