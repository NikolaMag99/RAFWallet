package raf.rs.projekat1.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {

    private final MutableLiveData<String> userInputLiveData = new MutableLiveData<>();

    public LiveData<String> getUserInput() {
        return userInputLiveData;
    }

    public void storeUserInput(String userInput) {
        userInputLiveData.setValue(userInput);
    }

}
