package raf.rs.projekat1.models;

import android.media.MediaRecorder;

import java.io.File;
import java.io.Serializable;

public class Prihod implements Serializable {

    private int id;
    private int kolicina;
    private String naslov;
    private String opis;
    private File file;
    private int vrednost;


    public Prihod(int id, int kolicina, String naslov, String opis) {
        this.id = id;
        this.kolicina = kolicina;
        this.naslov = naslov;
        this.opis = opis;
    }


    public Prihod(int id, int kolicina, String naslov, File file) {
        this.id = id;
        this.kolicina = kolicina;
        this.naslov = naslov;
        this.file = file;
    }


    public int getVrednost() {
        return vrednost;
    }

    public void setVrednost(int vrednost) {
        this.vrednost = vrednost;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
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
