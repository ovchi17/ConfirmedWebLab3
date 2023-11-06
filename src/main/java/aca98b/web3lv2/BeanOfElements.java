package aca98b.web3lv2;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AjaxBehaviorEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;

import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;


@Named
@ApplicationScoped
public class BeanOfElements implements Serializable {
    @Inject
    private XBean xBean;
    @Inject
    private YBean yBean;
    @Inject
    private RBean rBean;
    private OneElement element = new OneElement();;
    private List<OneElement> listOfElements;
    private AreaCheck areaCheck = new AreaCheck();
    private float[] arrayOfR = {1.0f, 1.5f, 2.0f, 2.5f, 3.0f};
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public BeanOfElements() {
        listOfElements = new ArrayList<>();
    }

    public void addNew(String xNew, String yNew, String rNew){
        try{
            float x = Float.parseFloat(xNew);
            float y = Float.parseFloat(yNew);
            float r = Float.parseFloat(rNew);
            System.out.println(x);
            System.out.println(y);
            System.out.println(r);
            long scriptStart = System.nanoTime();
            if (x >= -5f && x <= 5f && y >= -3f && y <= 5f && areaCheck.inArr(r, arrayOfR)){
                String res = areaCheck.checker(x, y, r);
                LocalTime currentTime = LocalTime.now();
                String curTime = currentTime.format(formatter);
                String scriptTime = String.format("%.2f", (double) (System.nanoTime() - scriptStart) * 0.0001);
                OneElement el = new OneElement(x, y, r, res, curTime, scriptTime);
                listOfElements.add(el);
            }
        } catch (Exception e) {
            FacesMessage message = new FacesMessage("Bad args for numbers!");
            throw new ValidatorException(message);
        }
    }

    public int getSize(){
        return listOfElements.size();
    }

    public void clear(){
        listOfElements.clear();
        System.out.println(listOfElements);
        System.out.println("NORM 4ISTENbKO");
    }

    public List<OneElement> getList(){
        return listOfElements;
    }

    public void addNewGraph(){
        Map<String, String> values = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        try {
            float x = Float.parseFloat(values.get("x"));
            float y = Float.parseFloat(values.get("y"));
            float r = Float.parseFloat(values.get("r"));
            System.out.println("GOT VALUES");
            System.out.println(x);
            System.out.println(y);
            System.out.println(r);
            long scriptStart = System.nanoTime();
            if (x >= -5f && x <= 5f && y >= -3f && y <= 5f && areaCheck.inArr(r, arrayOfR)) {
                String res = areaCheck.checker(x, y, r);
                LocalTime currentTime = LocalTime.now();
                String curTime = currentTime.format(formatter);
                String scriptTime = String.format("%.2f", (double) (System.nanoTime() - scriptStart) * 0.0001);
                OneElement el = new OneElement(x, y, r, res, curTime, scriptTime);
                listOfElements.add(el);
            }
        } catch (Exception e) {
            System.out.println("БЛЯЯЯЯЯЯЯЯЯЯЯ");
        }
    }

    public void sendAllPoint(){
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "[]";
        try {
            json = objectMapper.writeValueAsString(listOfElements);
            System.out.println(json);
            PrimeFaces.current().ajax().addCallbackParam("response", json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            PrimeFaces.current().ajax().addCallbackParam("response", "[]");
        }
    }

}