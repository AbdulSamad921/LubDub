package com.example.abdulsamadkhan.audiotest;

/**
 * Created by Abdul Samad Khan on 4/3/2017.
 */

public class visit1vital {

    private String Bpdystole;
    private String Bpsystole;
    private String Temperature;

    private String Heartbeat;

    public visit1vital(){

    }

    public visit1vital(String BPdystole, String BPsystole, String Temperature,String HeartBeat) {
        this.Bpdystole = BPdystole;
        this.Bpsystole = BPsystole;
        this.Temperature=Temperature;

        this.Heartbeat=HeartBeat;
    }

    public String getBpdystole() {
        return Bpdystole;
    }

    public void setBpdystole(String bpdystole) {
        this.Bpdystole = bpdystole;
    }

    public String getBpsystole() {
        return Bpsystole;
    }

    public void setBpsystole(String bpsystole) {
        Bpsystole = bpsystole;
    }

    public String getTemperature() {
        return Temperature;
    }

    public void setTemperature(String temperature) {
        Temperature = temperature;
    }


    public String getHeartbeat() {
        return Heartbeat;
    }

    public void setHeartbeat(String heartbeat) {
        Heartbeat = heartbeat;
    }
}
