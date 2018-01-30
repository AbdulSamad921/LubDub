package com.example.abdulsamadkhan.audiotest;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Abdul Samad Khan on 3/20/2017.
 */

public class history {


    private String HyperTension;
    private String Smoking;
    private String Diabetic;
    private String HeartProblem;

    public history() {

    }

    public history(String HyperTension, String Smoking, String Diabetic, String HeartProblem) {
        this.HyperTension = HyperTension;
        this.Smoking = Smoking;
        this.Diabetic = Diabetic;
        this.HeartProblem = HeartProblem;


    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Hypertension",HyperTension);
        result.put("Smoking", Smoking);
        result.put("Dybatic", Diabetic);
        result.put("HeartProblem", HeartProblem);

        return result;
    }

    public String getHyperTension() {
        return HyperTension;
    }

    public void setHyperTension(String hyperTension) {
        HyperTension = hyperTension;
    }

    public String getSmoking() {
        return Smoking;
    }

    public void setSmoking(String smoking) {
        Smoking = smoking;
    }

    public String getDiabetic() {
        return Diabetic;
    }

    public void setDiabetic(String diabetic) {
        Diabetic = diabetic;
    }

    public String getHeartProblem() {
        return HeartProblem;
    }

    public void setHeartProblem(String heartProblem) {
        HeartProblem = heartProblem;
    }
}
