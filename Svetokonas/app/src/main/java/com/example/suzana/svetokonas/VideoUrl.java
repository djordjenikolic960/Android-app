package com.example.suzana.svetokonas;

import java.io.Serializable;

public class VideoUrl implements Serializable{
    public int br;
    public String id;
    public String url;
    public String ime;
    public VideoUrl(){}
    public VideoUrl(String id,String url,String ime, int br)
    {
        this.br=br;
        this.id=id;
        this.url=url;
        this.ime=ime;
    }

    public int getBr() {
        return br;
    }

    public void setBr(int br) {
        this.br = br;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }
}

