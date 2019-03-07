package com.example.suzana.svetokonas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Test implements Serializable{
    private String ime;
    private String id;
    private List<Pitanje> test;

    public Test()
    {
        test=new ArrayList<Pitanje>();
    }
    public Test(String id, String ime)

    {
        this.ime=ime;
        this.id=id;
        test=new ArrayList<Pitanje>(5);
    }

    public void dodajPitanje(Pitanje p)
    {
        if(test.size()<5) {
            test.add(p);
        }
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public Pitanje vratiPitanje(int i)
    {
        return test.get(i);
    }
    public List<Pitanje> getTest() {
        return test;
    }

    public void setTest(List<Pitanje> test) {
        this.test = test;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
