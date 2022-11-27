package com.example.ihm;

public class Salle_disponible {
    int id;
    String numero;
    double heure;
    double heure2;

    public Salle_disponible(int id, String numero, double heure, double heure2) {
        this.id = id;
        this.numero = numero;
        this.heure = heure;
        this.heure2 = heure2;
    }

    public Salle_disponible(String numero, double heure, double heure2) {
        this.numero = numero;
        this.heure = heure;
        this.heure2 = heure2;

    }

    public Salle_disponible() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getHeure() {
        return heure;
    }

    public void setHeure(double heure) {
        this.heure = heure;
    }

    public double getHeure2() {
        return heure2;
    }

    public void setHeure2(double heure2) {
        this.heure2 = heure2;
    }
}
