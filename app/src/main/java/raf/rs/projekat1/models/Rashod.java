package raf.rs.projekat1.models;

public class Rashod {

    private int id;
    private int kolicina;
    private String naslov;
    private String opis;


    public Rashod(int id,int kolicina, String naslov, String opis) {
        this.id =id;
        this.kolicina = kolicina;
        this.naslov = naslov;
        this.opis = opis;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
