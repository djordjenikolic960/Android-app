package com.example.suzana.svetokonas;

import java.io.Serializable;

public class Korisnik implements Serializable{
    String ime;
    String prezime;
    int razred;
    String mail;
    String id;

    public Korisnik(String ime, String prezime, int razred,String mail, String id) {
        this.ime = ime;
      this.prezime=prezime;
      this.razred=razred;
      this.mail=mail;
      this.id=id;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Korisnik() {
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public int getRazred() {
        return razred;
    }

    public void setRazred(int razred) {
        this.razred = razred;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
