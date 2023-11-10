package aca98b.web3lv2;

import jakarta.persistence.*;


import java.io.Serializable;
import java.util.Date;
import java.util.Locale;


// class represents database table
@Entity
@Table(name = "aca98b")
public class HibernateElement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @Column(nullable = false)
    public float x;
    @Column(nullable = false)
    public float y;
    @Column(nullable = false)
    public float r;
    @Column(nullable = false)
    public String result;
    @Column(nullable = false)
    public String time;
    @Column(nullable = false)
    public String scriptTime;
    @Column(nullable = false)
    public String uid;

    public HibernateElement(){}

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

//    public String toJson() {
//        return String.format(Locale.US, "{\"x\": %.2f, \"y\": %.2f, \"r\": %.2f, \"result\": %b}", x, y, r, result);
//    }
}
