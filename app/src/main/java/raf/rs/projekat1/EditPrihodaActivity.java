package raf.rs.projekat1;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

import raf.rs.projekat1.models.Prihod;
import raf.rs.projekat1.models.Rashod;
import timber.log.Timber;

public class EditPrihodaActivity extends AppCompatActivity {


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
    private Button izmeni;
    private Button nazad;
    private ImageView mic;
    private ImageView micRecord;
    public static int counter = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_prihoda_rashoda);
        init();
    }

    private void init() {
        initView();
        initListener();
    }

    private void create() {
        File folder = new File(this.getFilesDir(), "sounds");
        if(!folder.exists()) folder.mkdir();
        file = new File(folder, counter + ".3gp");
    }

    private void initMediaRecorder(File file) {
        mediaRecorder = new MediaRecorder();
        // Postavljanje parametara za mediaRecorder
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(file);
    }

    private void initView() {
        naslov = findViewById(R.id.naslovEdit);
        kolicina = findViewById(R.id.kolicinaEdit);
        opis = findViewById(R.id.opisEdit);
        izmeni = findViewById(R.id.izmeniBtnEdit);
        nazad = findViewById(R.id.odustaniEdit);
        mic = findViewById(R.id.btnMicEdit);
        micRecord = findViewById(R.id.btnRecordingEdit);
    }
    private void initPlayer() {
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            Prihod prihod = (Prihod) intent.getExtras().getSerializable("prihodEdit");
            if (prihod.getFile() != null) {
                Intent intent2 = new Intent();
                String oldK = Integer.toString(prihod.getKolicina());
                String oldNaslov = prihod.getNaslov();
                Prihod oldPrihod = new Prihod(prihod.getId(), Integer.parseInt(oldK), oldNaslov, prihod.getFile());
                intent2.putExtra("OLD", oldPrihod);
                String naslovEdit = naslov.getText().toString();
                String kolicinaEdit = kolicina.getText().toString();
                Prihod editPrihod = new Prihod(prihod.getId(), Integer.parseInt(kolicinaEdit), naslovEdit, file);
                intent2.putExtra("NEW", editPrihod);
                setResult(Activity.RESULT_OK, intent2);
                finish();
            }
        }
    }

    public void initOpis() {
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            Intent intent2 = new Intent();
            Prihod prihod = (Prihod) intent.getExtras().getSerializable("prihodEdit");
            String oldK = Integer.toString(prihod.getKolicina());
            String oldNaslov = prihod.getNaslov();
            String oldOpis = prihod.getOpis();
            Prihod oldPrihod = new Prihod(prihod.getId(), Integer.parseInt(oldK), oldNaslov, oldOpis);
            intent2.putExtra("OLD", oldPrihod);
            String naslovEdit = naslov.getText().toString();
            String kolicinaEdit = kolicina.getText().toString();
            String opisEdit = opis.getText().toString();
            Prihod editPrihod = new Prihod(prihod.getId(), Integer.parseInt(kolicinaEdit), naslovEdit, opisEdit);
            intent2.putExtra("NEW", editPrihod);
            setResult(Activity.RESULT_OK, intent2);
            finish();
        }

    }

    private void initListener() {
        nazad.setOnClickListener( v -> {
            setResult(Activity.RESULT_CANCELED, null);
            finish();
        });
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            Prihod prihod = (Prihod) intent.getExtras().getSerializable("prihodEdit");
            if (prihod.getFile() != null) {
                mic.setVisibility(View.VISIBLE);
                opis.setVisibility(View.GONE);
                create();
                mic.setOnClickListener(m -> {
                    try {
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
                micRecord.setOnClickListener(r -> {
                    mic.setVisibility(View.VISIBLE);
                    micRecord.setVisibility(View.GONE);
                    mediaRecorder.stop();
                    mediaRecorder.release();
                    mediaRecorder = null;
                });

                    izmeni.setOnClickListener(v -> {
                        if (naslov.getText().toString().isEmpty() || kolicina.getText().toString().isEmpty()){
                            Toast.makeText(this, "Popuni sva polja!", Toast.LENGTH_SHORT).show();
                        }  else {
                            initPlayer();
                        }
                    });

            }
            else {
                mic.setVisibility(View.GONE);
                opis.setVisibility(View.VISIBLE);
                    izmeni.setOnClickListener(v -> {
                        if (naslov.getText().toString().isEmpty() || kolicina.getText().toString().isEmpty()){
                            Toast.makeText(this, "Popuni sva polja!", Toast.LENGTH_SHORT).show();
                        }  else {
                            initOpis();
                        }
                    });
            }
        }

    }
    
}