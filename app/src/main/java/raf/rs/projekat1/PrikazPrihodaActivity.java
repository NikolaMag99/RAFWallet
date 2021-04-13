package raf.rs.projekat1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import raf.rs.projekat1.fragments.Prihodi;
import raf.rs.projekat1.fragments.Unos;
import raf.rs.projekat1.models.Prihod;
import timber.log.Timber;

public class PrikazPrihodaActivity extends AppCompatActivity {

    private TextView naslov;
    private TextView kolicina;
    private TextView opis;
    private ImageView play;
    private ImageView pause;


    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener;
    private AudioFocusRequest audioFocusRequest;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prikaz_prihoda_rashoda);
        init();
    }

    private void init() {
        initView();
        initPlayer();
        initListener();
    }

    private void initView() {
        play = findViewById(R.id.playBtn);
        pause = findViewById(R.id.pauseBtn);
        naslov = findViewById(R.id.naslovPodaciTekst);
        kolicina = findViewById(R.id.kolicinaPodaciTekst);
        opis = findViewById(R.id.opisPodaciTekst);
    }

    private void initPlayer() {
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            Prihod prihod = (Prihod) intent.getExtras().getSerializable("prihod");

            if (prihod.getFile() != null) {
                play.setVisibility(View.VISIBLE);
                opis.setVisibility(View.GONE);
                mediaPlayer = MediaPlayer.create(this, Uri.fromFile(prihod.getFile()));
                audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            } else {
                play.setVisibility(View.GONE);
                opis.setVisibility(View.VISIBLE);

            }
        }
    }


    private void initListener() {
        Intent intent = getIntent();
        Prihod prihod = (Prihod) intent.getExtras().getSerializable("prihod");
        play.setOnClickListener(v -> play());

        pause.setOnClickListener(v -> pause());
        naslov.setText(prihod.getNaslov());
        kolicina.setText(Integer.toString(prihod.getKolicina()));
        opis.setText(prihod.getOpis());

        // We have to handle focus changes
        onAudioFocusChangeListener = focusChange -> {
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                case AudioManager.AUDIOFOCUS_LOSS: {
                    // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a short amount of time.
                    // The AUDIOFOCUS_LOSS case means we've lost audio focus
                    Timber.e("AUDIOFOCUS_LOSS_TRANSIENT or AUDIOFOCUS_LOSS");
                    pause();
                } break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK: {
                    // The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                    // our app is allowed to continue playing sound but at a lower volume.
                    Timber.e("AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
                    playerDuck(true);
                } break;
                case AudioManager.AUDIOFOCUS_GAIN: {
                    // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                    Timber.e("AUDIOFOCUS_GAIN");
                    playerDuck(false);
                    play();
                } break;
            }
        };

        if (prihod.getFile() != null) {
            mediaPlayer.setOnCompletionListener(mp -> {
                // Hide pause button
                pause.setVisibility(View.GONE);
                // Show play button
                play.setVisibility(View.VISIBLE);
                // Set media player to initial position
                mediaPlayer.seekTo(0);
            });
        }

        // Description of the audioFocusRequest
        audioFocusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
                .setAudioAttributes(new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build())
                .setAcceptsDelayedFocusGain(true)
                .setWillPauseWhenDucked(true)
                .setOnAudioFocusChangeListener(onAudioFocusChangeListener)
                .build();
    }


    public synchronized void playerDuck(boolean duck) {
        if (mediaPlayer != null) {
            // Reduce the volume when ducking - otherwise play at full volume.
            mediaPlayer.setVolume(duck ? 0.2f : 1.0f, duck ? 0.2f : 1.0f);
        }
    }

    private void pause() {
        // Hide pause button
        pause.setVisibility(View.GONE);
        // Show play button
        play.setVisibility(View.VISIBLE);
        // Pause media player
        mediaPlayer.pause();
    }

    private void play() {
        // Request audio focus
        int result = audioManager.requestAudioFocus(audioFocusRequest);
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            Timber.e("AUDIOFOCUS_REQUEST_GRANTED");
            // Hide play button
            play.setVisibility(View.GONE);
            // Show pause button
            pause.setVisibility(View.VISIBLE);
            // Start media player
            mediaPlayer.start();
            // Set max on seek bar
        }
    }

}