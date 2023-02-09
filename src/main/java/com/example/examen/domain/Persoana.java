package com.example.examen.domain;

public class Persoana extends Entity<Long>{
    private String username;
    private String nume;
    private String prenume;
    private String parola;
    private Oras oras;
    private String strada;
    private String nrStrada;
    private String telefon;

    public Persoana(long id, String nume, String prenume, String username, String parola, Oras oras, String strada, String nrStrada, String telefon) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.username = username;
        this.parola = parola;
        this.oras = oras;
        this.strada = strada;
        this.nrStrada = nrStrada;
        this.telefon = telefon;
    }

    public String getUsername() {
        return username;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getParola() {
        return parola;
    }

    public Oras getOras() {
        return oras;
    }

    public String getStrada() {
        return strada;
    }

    public String getNrStrada() {
        return nrStrada;
    }

    public String getTelefon() {
        return telefon;
    }

    @Override
    public String toString() {
        return "Persoana{" +
                "username='" + username + '\'' +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", parola='" + parola + '\'' +
                ", oras=" + oras +
                ", strada='" + strada + '\'' +
                ", nrStrada='" + nrStrada + '\'' +
                ", telefon='" + telefon + '\'' +
                '}';
    }
}
