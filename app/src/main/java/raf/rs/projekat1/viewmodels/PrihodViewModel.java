package raf.rs.projekat1.viewmodels;

import android.media.MediaRecorder;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import raf.rs.projekat1.models.Prihod;

public class PrihodViewModel extends ViewModel {


    public static int counter = 101;

    private final MutableLiveData<List<Prihod>> prihodi = new MutableLiveData<>();
    private final MutableLiveData<Integer> novac = new MutableLiveData<>();
    private final ArrayList<Prihod> prihodiLista = new ArrayList<>();
    Prihod prihod;


    public PrihodViewModel() {
        for(int i=0; i<=3; i++) {
            Prihod prihod = new Prihod(i, 1500,"Prihod", "Nesto");
            prihodiLista.add(prihod);
        }

        ArrayList<Prihod> listToSubmit = new ArrayList<>(prihodiLista);
        prihodi.setValue(listToSubmit);
    }

    public LiveData<List<Prihod>> getPrihodi() {
        return prihodi;
    }

    
    public int getKolicina () {
        int kolicina = prihod.getKolicina();
        return kolicina;
    }


    public void addPrihod(Prihod prihod1) {
        prihodiLista.add(prihod1);
        ArrayList<Prihod> listToSubmit = new ArrayList<>(prihodiLista);
        prihodi.setValue(listToSubmit);
    }

    public void removePrihod(Prihod prihod) {
        prihodiLista.remove(prihod);
        ArrayList<Prihod> listToSubmit = new ArrayList<>(prihodiLista);
        prihodi.setValue(listToSubmit);
    }

    public void editPrihod(Prihod old, Prihod newPrihod) {
        for (Prihod p: prihodiLista){
            if(p.getId() == old.getId()) {
                p.setKolicina(newPrihod.getKolicina());
                p.setNaslov(newPrihod.getNaslov());
                if(newPrihod.getFile() != null) {
                    p.setFile(newPrihod.getFile());
                }else {
                    p.setOpis(newPrihod.getOpis());
                }
            }

        }
        ArrayList<Prihod> listToSubmit = new ArrayList<>(prihodiLista);
        prihodi.setValue(listToSubmit);
    }


}
