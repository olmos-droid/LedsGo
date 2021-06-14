package com.example.myapplication30;

import java.util.ArrayList;

public class Groups {
    private String idgroup;
ArrayList<Tira>tiras;

    public Groups(String idgroup, ArrayList<Tira> tiras) {
        this.idgroup = idgroup;
        this.tiras = tiras;
    }

    public String getIdgroup() {
        return idgroup;
    }

    public void setIdgroup(String idgroup) {
        this.idgroup = idgroup;
    }

    public ArrayList<Tira> getTiras() {
        return tiras;
    }

    public void setTiras(ArrayList<Tira> tiras) {
        this.tiras = tiras;
    }
//
//    public Groups(String idgroup, String idstrip, String cantleds) {
//        this.idgroup = idgroup;
//        this.idstrip = idstrip;
//        this.cantleds = cantleds;
//    }
//
//    public String getIdgroup() {
//        return idgroup;
//    }
//
//    public void setIdgroup(String idgroup) {
//        this.idgroup = idgroup;
//    }
//
//    public String getIdstrip() {
//        return idstrip;
//    }
//
//    public void setIdstrip(String idstrip) {
//        this.idstrip = idstrip;
//    }
//
//    public String getCantleds() {
//        return cantleds;
//    }
//
//    public void setCantleds(String cantleds) {
//        this.cantleds = cantleds;
//    }
}
