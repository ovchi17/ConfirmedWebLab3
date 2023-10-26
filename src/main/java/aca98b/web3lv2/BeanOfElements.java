package aca98b.web3lv2;

import jakarta.faces.bean.ApplicationScoped;
import jakarta.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

@ManagedBean
@ApplicationScoped
public class BeanOfElements implements Serializable {
    private OneElement element;
    private List<OneElement> listOfElements;

    @PostConstruct
    public void postConstruct() {
        element = new OneElement();
        listOfElements = new ArrayList<OneElement>();
    }

    public void clear(){
        listOfElements.clear();
    }

}
