package raf.rs.projekat1.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

import raf.rs.projekat1.R;

public class Unos extends Fragment  implements AdapterView.OnItemSelectedListener {

    Spinner aSpinner;
    private MediaRecorder mediaRecorder;
    private final int PERMISSION_ALL = 1;
    private final String[] PERMISSIONS = {
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    public static File file;
    private EditText naslov;
    private EditText kolicina;
    private EditText opis;
    private Button dodaj;
    private CheckBox checkBox;
    private ImageView mic;
    private ImageView micRecord;

    public Unos() {
        super(R.layout.unos);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(hasPermissions(getActivity(), PERMISSIONS)) {
            init(view);
            create();
        }else {
            // Ukoliko nije, trazimo ih
            requestPermissions(PERMISSIONS, PERMISSION_ALL);
        }

    }

    private boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String @NotNull [] permissionsList, int @NotNull [] grantResults) {
        // Ovde dobijamo odgovor na requestPermissions
        if (requestCode == PERMISSION_ALL) {
            if (grantResults.length > 0) {
                StringBuilder permissionsDenied = new StringBuilder();
                for(int i=0;i<grantResults.length;i++){
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        permissionsDenied.append("\n").append(permissionsList[i]);
                    }
                }
                if (permissionsDenied.toString().length() == 0) {
                    // Ukoliko nema odbijenih dozvola, nastavljamo dalje
                    create();

                }else {
                    Toast.makeText(getActivity(), "Missing permissions! " + permissionsDenied.toString(), Toast.LENGTH_LONG).show();
                    // Ukoliko ima odbijenih dozvola ispisujemo poruku i zatvaramo activity
                    getActivity().finish();
                }
            }
        }
    }
    private void create() {
        File folder = new File(getActivity().getFilesDir(), "sounds");
        if(!folder.exists()) folder.mkdir();
        file = new File(folder, "record.3gp");
    }

    private void init(View view) {
        initView(view);
        initListener();
//        File folder = new File(getActivity().getFilesDir(), "sounds");
//        if(!folder.exists()) folder.mkdir();
//        file = new File(folder, "record.3gp");
//        initListener();

    }

    private void initMediaRecorder(File file) {
        mediaRecorder = new MediaRecorder();
        // Postavljanje parametara za mediaRecorder
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(file);
    }

    private void initView(View view) {
        aSpinner = view.findViewById(R.id.aSpinner);
        naslov = view.findViewById(R.id.naslov);
        kolicina = view.findViewById(R.id.kolicina);
        opis = view.findViewById(R.id.opis);
        checkBox = view.findViewById(R.id.audioCheck);
        dodaj = view.findViewById(R.id.dodajUListuBtn);
        mic = view.findViewById(R.id.btnMic);
        micRecord = view.findViewById(R.id.btnRecording);
    }

    private void initListener() {
        aSpinner.setOnItemSelectedListener(this);
        checkBox.setOnClickListener(v -> {
            if(checkBox.isChecked()){
                opis.setVisibility(View.GONE);
                mic.setVisibility(View.VISIBLE);

            }else {
                opis.setVisibility(View.VISIBLE);
                mic.setVisibility(View.GONE);
            }
        });
        mic.setOnClickListener(v -> {
            try{
                mic.setVisibility(View.GONE);
                micRecord.setVisibility(View.VISIBLE);
                initMediaRecorder(file);
                 // Pokrecemo snimanje
                mediaRecorder.prepare();
                mediaRecorder.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        micRecord.setOnClickListener(v  -> {
                mic.setVisibility(View.VISIBLE);
                micRecord.setVisibility(View.GONE);
                // Zaustavljamo snimanje i oslobadjamo resurse
                // Metodom stop() se snimljeni resurs cuva u fajlu koji smo prosledili pri inicijalizaciji mediaRecorder-a
                mediaRecorder.stop();
                mediaRecorder.release();
                mediaRecorder = null;
        });
    }


    public void kliknuo() {
        dodaj.setOnClickListener(v -> {

        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
