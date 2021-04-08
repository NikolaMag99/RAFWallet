package raf.rs.projekat1.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import raf.rs.projekat1.models.Prihod;

public class PrihodViewModel extends ViewModel {


    public static int counter = 10;

    private final MutableLiveData<List<Prihod>> prihodi = new MutableLiveData<>();
    private final MutableLiveData<Integer> novac = new MutableLiveData<>();
    private final ArrayList<Prihod> prihodiLista = new ArrayList<>();
    Prihod prihod;


    public PrihodViewModel() {
        for(int i=0; i<3; i++) {
            Prihod prihod = new Prihod(i, 1500,"Prihod", "Nesto");
            prihodiLista.add(prihod);
        }
//        // ovo radimo zato sto cars.setValue u pozadini prvo proverava da li je pokazivac na objekat isti i ako jeste nece uraditi notifyAll
//        // kreiranjem nove liste dobijamo novi pokazivac svaki put
        ArrayList<Prihod> listToSubmit = new ArrayList<>(prihodiLista);
        prihodi.setValue(listToSubmit);
    }

    public LiveData<List<Prihod>> getPrihodi() {
        return prihodi;
    }

//    public LiveData<Integer> getNovac() {
//        for(Prihod p: prihodi.getValue()){
//            novac.setValue(novac.getValue() + p.getKolicina());
//        }
//        return novac;
//    }

    public int getKolicina () {
        int kolicina = prihod.getKolicina();
        return kolicina;
    }


    public void addPrihod(Integer kolicina, String naslov, String opis) {
        Prihod prihod = new Prihod(counter++, kolicina, naslov, opis);
        prihodiLista.add(prihod);
        ArrayList<Prihod> listToSubmit = new ArrayList<>(prihodiLista);
        prihodi.setValue(listToSubmit);
    }

    public void removePrihod(Prihod prihod) {
        prihodiLista.remove(prihod);
        ArrayList<Prihod> listToSubmit = new ArrayList<>(prihodiLista);
        prihodi.setValue(listToSubmit);
    }

}
