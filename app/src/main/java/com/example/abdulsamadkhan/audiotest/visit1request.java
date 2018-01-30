package com.example.abdulsamadkhan.audiotest;

/**
 * Created by Abdul Samad Khan on 4/2/2017.
 */

public class visit1request {
    private String ChestPain;
    private String Nausea;
    private String NeckPain;
    private String Sweating;
    private String ShortofBreath;
    private String Cholestrol;


    public visit1request() {

    }

    public visit1request(String ChestPain, String Nausea, String ShortofBreath, String Cholestrol, String Sweating, String NeckPain) {
        this.ChestPain = ChestPain;
        this.Nausea = Nausea;
        this.NeckPain=NeckPain;
        this.Sweating=Sweating;
        this.ShortofBreath=ShortofBreath;
        this.Cholestrol=Cholestrol;
    }

    public String getChestPain() {
        return ChestPain;
    }

    public void setChestPain(String chestPain) {
        ChestPain = ChestPain;
    }

    public String getNausea() {
        return Nausea;
    }

    public void setNausea(String nausea) {
        Nausea = nausea;
    }

    public String getNeckPain() {
        return NeckPain;
    }

    public void setNeckPain(String neckPain) {
        NeckPain = neckPain;
    }

    public String getSweating() {
        return Sweating;
    }

    public void setSweating(String sweating) {
        Sweating = sweating;
    }

    public String getShortofBreath() {
        return ShortofBreath;
    }

    public void setShortofBreath(String shortofBreath) {
        ShortofBreath = shortofBreath;
    }

    public String getCholestrol() {
        return Cholestrol;
    }

    public void setCholestrol(String cholestrol) {
        Cholestrol = cholestrol;
    }


}
