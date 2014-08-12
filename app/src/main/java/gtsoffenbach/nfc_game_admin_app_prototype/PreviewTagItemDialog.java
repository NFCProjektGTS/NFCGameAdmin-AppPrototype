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
public class PreviewTagItemDialog extends AlertDialog implements View.OnClickListener {

    private View view;
    private ImageView bildvorschau;
    private TagItem selectedTagItem;
    private LayoutInflater inflater = null;
    private Activity caller;
    private Button ButtonSchließen;

    public PreviewTagItemDialog(Activity caller, int THEME, TagItem selectedTagItem) {
        super(caller, THEME);
        this.caller = caller;
        this.selectedTagItem = selectedTagItem;
        this.inflater = (LayoutInflater) caller.getSystemService(caller.LAYOUT_INFLATER_SERVICE);
        this.scanSelectedTagItem();


        setTitle(selectedTagItem.getName());
        setMessage("ID: "+selectedTagItem.getID()+System.getProperty("line.separator")+selectedTagItem.getBeschreibung());
        //setButton(R.id.);
        setView(view);

        setCancelable(true);
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






                //toDo: es muss noch eine Überprüfung geschrieben, für den Fall, dass es sich um ein LinkTagItem handelt und nicht um ein AssetTagItem.

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_VorschauSchließen:
               this.closePreviewDialog();
                break;

        }
    }




    public void closePreviewDialog() {

        this.dismiss();
    }
}
