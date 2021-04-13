package raf.rs.projekat1.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import raf.rs.projekat1.models.Prihod;
import raf.rs.projekat1.models.Rashod;


public class RashodViewModel extends ViewModel {


    public static int counter = 101;

    private final MutableLiveData<List<Rashod>> rashodi = new MutableLiveData<>();
    private final MutableLiveData<Integer> novac = new MutableLiveData<>();
    private final ArrayList<Rashod> rashodiLista = new ArrayList<>();
    Rashod rashod;

    public RashodViewModel() {
        for(int i=0; i<=2; i++) {
            Rashod rashod1 = new Rashod(i, 1000,"Rashod", "Nesto");
            rashodiLista.add(rashod1);
        }
//        // ovo radimo zato sto cars.setValue u pozadini prvo proverava da li je pokazivac na objekat isti i ako jeste nece uraditi notifyAll
//        // kreiranjem nove liste dobijamo novi pokazivac svaki put
        ArrayList<Rashod> listToSubmit = new ArrayList<>(rashodiLista);
        rashodi.setValue(listToSubmit);
    }

    public LiveData<List<Rashod>> getRashodi() {
        return rashodi;
    }

    public LiveData<Integer> getNovac() {
        return novac;
    }

    public int getKolicina () {
        int kolicina = rashod.getKolicina();
        return kolicina;
    }


    public void addRashod(Rashod rashod) {
        rashodiLista.add(rashod);
        ArrayList<Rashod> listToSubmit = new ArrayList<>(rashodiLista);
        rashodi.setValue(listToSubmit);
//        counter++;
    }

    public void removeRashod(Rashod rashod) {
        rashodiLista.remove(rashod);
        ArrayList<Rashod> listToSubmit = new ArrayList<>(rashodiLista);
        rashodi.setValue(listToSubmit);
    }

    public void editRashod(Rashod old, Rashod newRashod) {
        for (Rashod p: rashodiLista){
            if(p.getId() == old.getId()) {
                p.setKolicina(newRashod.getKolicina());
                p.setNaslov(newRashod.getNaslov());
                if(newRashod.getFile() != null) {
                    p.setFile(newRashod.getFile());
                }else {
                    p.setOpis(newRashod.getOpis());
                }
            }

        }
        ArrayList<Rashod> listToSubmit = new ArrayList<>(rashodiLista);
        rashodi.setValue(listToSubmit);
    }

}
