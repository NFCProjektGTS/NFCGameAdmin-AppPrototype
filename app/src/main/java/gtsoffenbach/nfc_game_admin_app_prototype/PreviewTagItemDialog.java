package gtsoffenbach.nfc_game_admin_app_prototype;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Marlon on 13.07.2014.
 * <p/>
 * Diese Klasse ist dazu da, ein Dialogfenster zu erstellen.
 * Dieser Dialog wird gebraucht wenn eine Vorschau von einem TagItem angezeigt werden soll
 * Diese Klasse bekommt das selectedTagItem und überprüft, welchen TagDataKind das TagItem hat(z.B. TagDataKind.Picture).
 * Ebenso überprüft die Klasse ob das SelectedTagItem ein Objekt der Klasse AssetTagItem oder der Klasse LinkTagItem ist.
 * Diese überprüfungen sind notwendig, um richtig mit dem SelectedTagItem agieren zu können.
 */
public class PreviewTagItemDialog extends AlertDialog implements View.OnClickListener, MediaPlayer.OnCompletionListener {

    private View view;
    private ImageView bildvorschau;
    private TagItem selectedTagItem;
    private LayoutInflater inflater = null;
    private Activity caller;
    private Button ButtonSchließen;
    private Button buttonPlay;
    private ImageButton imgButtonPlay;
    private MediaPlayer soundPlayer;
    private SeekBar seekBar;
    InputStream ims = null;

    public PreviewTagItemDialog(Activity caller, int THEME, TagItem selectedTagItem) {
        super(caller, THEME);
        this.caller = caller;
        this.selectedTagItem = selectedTagItem;
        this.inflater = (LayoutInflater) caller.getSystemService(caller.LAYOUT_INFLATER_SERVICE);
        this.scanSelectedTagItem();

        this.ButtonSchließen = (Button) view.findViewById(R.id.button_VorschauSchließen);
        this.ButtonSchließen.setOnClickListener(this);

        setTitle(selectedTagItem.toString());
        setView(view);
        setCancelable(false);
        show();


    }

    /*
    -diese Methode ist dazu da, zu überprüfen,
     ob dass selectedTagItem vom Typ TagDataKind.Picture, oder vom Typen TagDataKind.Sound,(etc.) ist.
    -Nach dieser Überprüfung, wird das passende Layout geladen.
     */
    private void scanSelectedTagItem() {
        if (this.selectedTagItem == null) {

        } else {
            if (this.selectedTagItem.getDataKind() == TagDataKind.Picture) {
                //toDo: es muss noch eine Überprüfung geschrieben, für den Fall, dass es sich um ein LinkTagItem handelt und nicht um ein AssetTagItem.

                view = inflater.inflate(R.layout.preview_picture_tagitem, null);
                this.bildvorschau = (ImageView) view.findViewById(R.id.imageView_BildVorschau);

                try
                {
                    this.ims = caller.getAssets().open(((AssetTagItem) selectedTagItem).getAssetPath());
                    Drawable d = Drawable.createFromStream(ims, null);
                    this.bildvorschau.setImageDrawable(d);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if (this.selectedTagItem.getDataKind() == TagDataKind.Sound) {
                view = inflater.inflate(R.layout.preview_sound_tagitem, null);

                //buttonPlay = (Button) view.findViewById(R.id.button_play);
                imgButtonPlay = (ImageButton) view.findViewById(R.id.imageButton_play);
               // Button buttonReset = (Button) view.findViewById(R.id.button_reset);
                ImageButton imgButtonReset = (ImageButton) view.findViewById(R.id.imageButton_reset);
                imgButtonPlay.setOnClickListener(this);
                imgButtonReset.setOnClickListener(this);
                seekBar = (SeekBar) view.findViewById(R.id.seekBar);
                soundPlayer = new MediaPlayer();
                soundPlayer.setOnCompletionListener(this);


                try {
                    AssetFileDescriptor afd = caller.getAssets().openFd(((AssetTagItem) selectedTagItem).getAssetPath());

                    soundPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());

                    soundPlayer.prepare();

                    /*
                    es muss einmall soundPlayer.start() aufgerufen werden, da sich sonst das Programm aufhängt,
                    wenn die seekBar bewegt wird. Anschließend wird soundPlayer.pause() aufgerufen, damit der Sound nicht losläuft
                     */
                    soundPlayer.start();
                    soundPlayer.pause();
                    seekthread.start();

                    seekBar.setMax(soundPlayer.getDuration());

                    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                        boolean FlagWasPlaying = false;

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                            if(FlagWasPlaying == true){
                                soundPlayer.start();
                            }

                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {
                            FlagWasPlaying=soundPlayer.isPlaying();
                            soundPlayer.pause();

                        }

                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress,
                                                      boolean fromUser) {
                            if (!fromUser) return;
                            soundPlayer.seekTo(progress);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }





                //toDo: es muss noch eine Überprüfung geschrieben, für den Fall, dass es sich um ein LinkTagItem handelt und nicht um ein AssetTagItem.
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageButton_play:
                this.toggleSoundPlayerModus();
                break;
            case R.id.imageButton_reset:
                soundPlayer.seekTo(1);
                break;
            case R.id.button_VorschauSchließen:
               this.closePreviewDialog();
                break;

        }
    }


    @Override
    public void onCompletion(MediaPlayer mp){
        soundPlayer.seekTo(1);
        gotoPauseModus();
    }

    private Thread seekthread = new Thread(new Runnable() {
        public void run() {
            seekbarupdate();
        }
    });

    private void seekbarupdate() {
        while (soundPlayer != null && soundPlayer.getCurrentPosition() < soundPlayer.getDuration()) {
            seekBar.setProgress(soundPlayer.getCurrentPosition());

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void toggleSoundPlayerModus(){
        if(soundPlayer!=null){
            if(soundPlayer.isPlaying()){
                gotoPauseModus();
            }
            else{
                gotoPlayModus();
            }
        }
    }

    private void gotoPlayModus() {
        soundPlayer.start();
        //buttonPlay.setText("pause");
        imgButtonPlay.setImageResource(R.drawable.pause_icon_klein);
        //imgButtonPlay.setImageBitmap(caller.getAssets()..open("Pictures/pause_icon_klein"));
    }

    private void gotoPauseModus() {
        soundPlayer.pause();
        //buttonPlay.setText("play");
        imgButtonPlay.setImageResource(R.drawable.play_icon_klein);
        //imgButtonPlay.setImageBitmap(caller.getAssets().open("Pictures/play_icon_klein"));
    }

    public void closePreviewDialog() {
        if(bildvorschau!=null){
            bildvorschau.clearFocus();
        }
        if(soundPlayer!=null){
            soundPlayer.stop();
            soundPlayer = null;
        }

        this.dismiss();
    }
}
