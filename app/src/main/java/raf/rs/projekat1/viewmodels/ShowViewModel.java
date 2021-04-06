package raf.rs.projekat1.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ShowViewModel extends ViewModel {

    private final MutableLiveData<String> titleLiveData = new MutableLiveData<>();

    public ShowViewModel() {
        titleLiveData.setValue("This is a placeholder!");
    }

    public LiveData<String> getTitle() {
        return titleLiveData;
    }

}
