package com.example.ihm;

public class Heure {

    private int Heure;
    private String Minute;


    public Heure(int Heure, String Minute) {
        this.Heure = Heure;
        this.Minute = Minute;
    }

    public int getHeure() {
        return Heure;
    }

    public void setHeure(int Heure) {
        this.Heure = Heure;
    }

    public String getMinute() {
        return Minute;
    }

    public void setMinute(String Minute) {
        this.Minute = Minute;
    }

    public String getFullHeure()  {
        return this.Heure + ":" + this.Minute;
    }

    // Text show in Spinner
    @Override
    public String toString()  {
        return this.getFullHeure();
    }


}
