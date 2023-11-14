package aca98b.web3lv2;

import jakarta.persistence.*;


import java.io.Serializable;
import java.util.Date;
import java.util.Locale;


// class represents database table
@Entity
@Table(name = "aca98b")
public class HibernateElement implements Serializable {
    public HibernateElement(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private float x;
    @Column(nullable = false)
    private float y;
    @Column(nullable = false)
    private float r;
    @Column(nullable = false)
    private String result;
    @Column(nullable = false)
    private String time;
    @Column(nullable = false)
    private String scriptTime;
    @Column(nullable = false)
    private String uid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getScriptTime() {
        return scriptTime;
    }

    public void setScriptTime(String scriptTime) {
        this.scriptTime = scriptTime;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
