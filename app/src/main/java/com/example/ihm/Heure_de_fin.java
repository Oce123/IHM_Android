package com.example.ihm;

public class Heure_de_fin {

    private int Heure_de_fin;
    private String Minute_de_fin;

    public Heure_de_fin(int heure_de_fin, String minute_de_fin) {
        Heure_de_fin = heure_de_fin;
        Minute_de_fin = minute_de_fin;
    }

    public int getHeure_de_fin() {
        return Heure_de_fin;
    }

    public void setHeure_de_fin(int Heure_de_fin) {
        this.Heure_de_fin = Heure_de_fin;
    }

    public String getMinute_de_fin() {
        return Minute_de_fin;
    }

    public void setMinute_de_fin(String Minute_de_fin) {
        this.Minute_de_fin = Minute_de_fin;
    }

    public String getFullHeure_de_fin()  {
        return this.Heure_de_fin + ":" + this.Minute_de_fin;
    }

    // Text show in Spinner
    @Override
    public String toString()  {
        return this.getFullHeure_de_fin();
    }

}
