package com.example.suzana.svetokonas;

import java.io.Serializable;

public class Pitanje implements Serializable{

    private String tekstPitanja;
    private String odgovor1;
    private String odgovor2;
    private String odgovor3;
    private String odgovor4;
    private String tacanOdgovor;

    public Pitanje(){}

    public Pitanje(String tekstPitanja, String odgovor1, String odgovor2, String odgovor3, String odgovor4, String tacanOdgovor) {
        this.tekstPitanja = tekstPitanja;
        this.odgovor1 = odgovor1;
        this.odgovor2 = odgovor2;
        this.odgovor3 = odgovor3;
        this.odgovor4 = odgovor4;
        this.tacanOdgovor=tacanOdgovor;
    }

    public String getTekstPitanja() {
        return tekstPitanja;
    }

    public String getOdgovor1() {
        return odgovor1;
    }

    public String getOdgovor2() {
        return odgovor2;
    }

    public String getOdgovor3() {
        return odgovor3;
    }

    public String getOdgovor4() {
        return odgovor4;
    }

    public String getTacanOdgovor() {
        return tacanOdgovor;
    }

    public void setTekstPitanja(String tekstPitanja) {
        this.tekstPitanja = tekstPitanja;
    }

    public void setOdgovor1(String odgovor1) {
        this.odgovor1 = odgovor1;
    }

    public void setOdgovor2(String odgovor2) {
        this.odgovor2 = odgovor2;
    }

    public void setOdgovor3(String odgovor3) {
        this.odgovor3 = odgovor3;
    }

    public void setOdgovor4(String odgovor4) {
        this.odgovor4 = odgovor4;
    }

    public void setTacanOdgovor(String tacanOdgovor) {
        this.tacanOdgovor = tacanOdgovor;
    }
}
