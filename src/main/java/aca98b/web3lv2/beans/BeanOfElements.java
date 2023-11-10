package aca98b.web3lv2.beans;

import aca98b.web3lv2.AreaCheck;
import aca98b.web3lv2.HibernateElement;
import aca98b.web3lv2.HibernateUtil;
import aca98b.web3lv2.beans.OneElement;
import aca98b.web3lv2.beans.RBean;
import aca98b.web3lv2.beans.XBean;
import aca98b.web3lv2.beans.YBean;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
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
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaRoot;
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
    private List<OneElement> listOfElements = loadDB();
    private OneElement example = new OneElement(0f, 0f, 0f, "Example", "Example", "Example", "Example");
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
                OneElement el = new OneElement(x, y, r, res, curTime, scriptTime, "bruh");
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
                OneElement el = new OneElement(x, y, r, res, curTime, scriptTime, "bruh");
                listOfElements.add(el);
            }
        } catch (Exception e) {
            System.out.println("error");
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

    public List<OneElement> loadDB() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        JpaCriteriaQuery<HibernateElement> query = criteriaBuilder.createQuery(HibernateElement.class);
        JpaRoot<HibernateElement> root = query.from(HibernateElement.class);
        query.select(root);
        List<HibernateElement> userList = session.createQuery(query).getResultList();
        List<OneElement> resultList = new ArrayList<OneElement>();
        for (HibernateElement hibernateElement : userList) {
            OneElement oneElement = new OneElement(
                    hibernateElement.getX(),
                    hibernateElement.getY(),
                    hibernateElement.getR(),
                    hibernateElement.getResult(),
                    hibernateElement.getTime(),
                    hibernateElement.getScriptTime(),
                    hibernateElement.getUid());
            resultList.add(oneElement);
        }
        return resultList;
    }

    public void saveDB() {

    }

    public void clearDB() {

    }

}
