package com.example.abdulsamadkhan.audiotest;

/**
 * Created by Abdul Samad Khan on 3/20/2017.
 */

public class PatientRequest {

    private String PatientName;
    private String PatientAge;
    private String PatientGender;
    private String PatientAddress;
    private String PatientContact;
    private String PatientCity;
    private String PatientBlood;
    private String PatientWeight;
    private String HyperTension;
    private String Smoking;
    private String Diabetic;
    private String HeartProblem;

    public PatientRequest() {

    }

    public PatientRequest(String PatientName, String PatientBlood, String PatientAddress, String PatientCity, String PatientAge, String PatientGender,  String PatientWeight, String PatientContact,String HyperTension, String Smoking, String Diabetic, String HeartProblem) {
        this.PatientName = PatientName;
        this.PatientAge = PatientAge;
        this.PatientGender=PatientGender;
        this.PatientAddress=PatientAddress;
        this.PatientContact=PatientContact;
        this.PatientCity=PatientCity;
        this.PatientBlood=PatientBlood;
        this.PatientWeight=PatientWeight;
        this.HyperTension = HyperTension;
        this.Smoking = Smoking;
        this.Diabetic = Diabetic;
        this.HeartProblem = HeartProblem;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public String getPatientAge() {
        return PatientAge;
    }

    public void setPatientAge(String patientAge) {
        PatientAge = patientAge;
    }

    public String getPatientGender() {
        return PatientGender;
    }

    public void setPatientGender(String patientGender) {
        PatientGender = patientGender;
    }

    public String getPatientAddress() {
        return PatientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        PatientAddress = patientAddress;
    }

    public String getPatientContact() {
        return PatientContact;
    }

    public void setPatientContact(String patientContact) {
        PatientContact = patientContact;
    }

    public String getPatientCity() {
        return PatientCity;
    }

    public void setPatientCity(String patientCity) {
        PatientCity = patientCity;
    }

    public String getPatientBlood() {
        return PatientBlood;
    }

    public void setPatientBlood(String patientBlood) {
        PatientBlood = patientBlood;
    }

    public String getPatientWeight() {
        return PatientWeight;
    }

    public void setPatientWeight(String patientWeight) {
        PatientWeight = patientWeight;
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
