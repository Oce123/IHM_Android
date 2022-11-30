package com.example.ihm;

public class Heure_12 {

    private int Heure_12;
    private String Minute_12;
    private String Format;


    public Heure_12(int Heure_12, String Minute_12, String Format) {
        this.Heure_12 = Heure_12;
        this.Minute_12 = Minute_12;
        this.Format = Format;
    }

    public int getHeure_12() {
        return Heure_12;
    }

    public void setHeure_12(int Heure_12) {
        this.Heure_12 = Heure_12;
    }

    public String getMinute_12() { return Minute_12; }

    public void setMinute_12(String Minute_12) {
        this.Minute_12 = Minute_12;
    }

    public String Format() { return Format; }

    public void Format(String Format) {
        this.Format = Format;
    }

    public String getFullHeure_12()  {
        return this.Heure_12 + ":" + this.Minute_12 + "    " + this.Format;
    }

    // Text show in Spinner
    @Override
    public String toString()  {
        return this.getFullHeure_12();
    }

}
