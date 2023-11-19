package aca98b.web3lv2.beans;

import aca98b.web3lv2.AreaCheck;
import aca98b.web3lv2.HibernateElement;
import aca98b.web3lv2.HibernateUtil;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import jakarta.inject.Inject;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.primefaces.PrimeFaces;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;


@Named
@SessionScoped
public class BeanOfElements implements Serializable {
    @Inject
    private XBean xBean;
    @Inject
    private YBean yBean;
    @Inject
    private RBean rBean;
    private OneElement element = new OneElement();;
    private List<OneElement> listOfElements = new ArrayList<>();
    private AreaCheck areaCheck = new AreaCheck();
    private float[] arrayOfR = {1.0f, 1.5f, 2.0f, 2.5f, 3.0f};
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private HibernateUtil hibernateUtil = new HibernateUtil();
    private String sessionId = "";
    private String noToken = "noToken";
    private Float minX = -5f;
    private Float maxX = 5f;
    private Float minY = -3f;
    private Float maxY = 5f;


    public BeanOfElements() {
        listOfElements = loadDB();
        sessionId = FacesContext.getCurrentInstance().getExternalContext().getSessionId(true);
    }

    private void addElement(String xNew, String yNew, String rNew){
        try {
            float x = Float.parseFloat(xNew);
            float y = Float.parseFloat(yNew);
            float r = Float.parseFloat(rNew);
            long scriptStart = System.nanoTime();
            if (x >= minX && x <= maxX && y >= minY && y <= maxY && areaCheck.inArr(r, arrayOfR)) {
                String res = areaCheck.checker(x, y, r);
                LocalTime currentTime = LocalTime.now();
                String curTime = currentTime.format(formatter);
                String scriptTime = String.format("%.2f", (double) (System.nanoTime() - scriptStart) * 0.0001);
                OneElement el = new OneElement(x, y, r, res, curTime, scriptTime, sessionId, generateToken());
                safetyAdd(el);
            }
        } catch (NumberFormatException e) {
            FacesMessage message = new FacesMessage("Bad args for numbers!");
            throw new ValidatorException(message);
        }
    }

    public void addNew(String x, String y, String r){ addElement(x, y, r); }

    public void addNewGraph(){
        Map<String, String> values = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        addNew(values.get("x"), values.get("y"), values.get("r"));
    }

    public int getSize(){
        return listOfElements.size();
    }

    public void clear(){
        listOfElements.clear();
        clearDB();
        System.out.println(listOfElements);
        System.out.println("NORM 4ISTENbKO");
    }

    public List<OneElement> getList(){
        return listOfElements;
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
        Session session = hibernateUtil.getSessionFactory().openSession();
        Query<HibernateElement> query = session.createQuery("FROM HibernateElement", HibernateElement.class);
        List<HibernateElement> userList = query.getResultList();
        session.close();
        return userList.stream()
                .filter(hibernateElement -> hibernateElement.getUid().equals(sessionId))
                .map(hibernateElement -> new OneElement(
                        hibernateElement.getX(),
                        hibernateElement.getY(),
                        hibernateElement.getR(),
                        hibernateElement.getResult(),
                        hibernateElement.getTime(),
                        hibernateElement.getScriptTime(),
                        hibernateElement.getUid(),
                        noToken
                ))
                .collect(Collectors.toList());
    }

    public void saveDB(OneElement el) {
        Session session = hibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        HibernateElement resultEl = new HibernateElement();
        resultEl.setX(el.getX());
        resultEl.setY(el.getY());
        resultEl.setR(el.getR());
        resultEl.setResult(el.getResult());
        resultEl.setTime(el.getTime());
        resultEl.setScriptTime(el.getScriptTime());
        resultEl.setUid(el.getUid());
        session.save(resultEl);
        session.getTransaction().commit();
    }

    public void clearDB() {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "DELETE FROM HibernateElement t WHERE t.uid = :value";
            Query query = session.createQuery(hql);
            query.setParameter("value", sessionId);
            query.executeUpdate();

            session.getTransaction().commit();
        }
    }

    public void safetyAdd(OneElement el) {
        try {
            String token = el.getUtoken();
            listOfElements.add(el);
            try {
                saveDB(el);
            } catch (HibernateException e) { // Используем HibernateException вместо HibernateError
                listOfElements.removeIf(element -> Objects.equals(element.getUtoken(), token));
                FacesMessage message = new FacesMessage("Ошибка при сохранении в БД");
                throw new ValidatorException(message);
            }
        } catch (Exception e) {
            FacesMessage message = new FacesMessage("Ошибка ввода-вывода");
            throw new ValidatorException(message);
        }
    }

    public String generateToken(){
        String resultToken = "";
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(512);
            KeyPair keyPair = keyGen.generateKeyPair();
            byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
            byte[] shortPrivateKey = new byte[50];
            System.arraycopy(privateKeyBytes, 0, shortPrivateKey, 0, 50);
            resultToken = Base64.getEncoder().encodeToString(shortPrivateKey);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return resultToken;
    }

}

