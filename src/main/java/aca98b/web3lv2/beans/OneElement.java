package aca98b.web3lv2.beans;

public final class OneElement {
    private float x;
    private float y;
    private float r;
    private String result;
    private String time;
    private String scriptTime;
    private String uid;
    private String utoken;

    public OneElement(float x, float y, float r, String result, String time, String scriptTime, String uid, String utoken){
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = result;
        this.time = time;
        this.scriptTime = scriptTime;
        this.uid = uid;
        this.utoken = utoken;
    }

    public OneElement(){
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

    public void setUid (String uid) {
        this.uid = uid;
    }

    public String getUtoken() {
        return utoken;
    }

    public void setUtoken(String utoken) {
        this.utoken = utoken;
    }

}
