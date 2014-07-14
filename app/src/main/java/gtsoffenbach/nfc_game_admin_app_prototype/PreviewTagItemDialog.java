package gtsoffenbach.nfc_game_admin_app_prototype;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

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
public class PreviewTagItemDialog extends AlertDialog {

    private View view;
    private ImageView bildvorschau;
    private TagItem selectedTagItem;
    private LayoutInflater inflater = null;
    private Activity caller;
    InputStream ims = null;

    public PreviewTagItemDialog(Activity caller, int THEME, TagItem selectedTagItem) {
        super(caller, THEME);
        this.caller = caller;
        this.selectedTagItem = selectedTagItem;
        this.inflater = (LayoutInflater) caller.getSystemService(caller.LAYOUT_INFLATER_SERVICE);
        this.scanSelectedTagItem();


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

                try {
                    this.ims = caller.getAssets().open(((AssetTagItem) selectedTagItem).getAssetPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Drawable d = Drawable.createFromStream(ims, null);
                this.bildvorschau.setImageDrawable(d);


                setTitle("AusgewähltesTagItem/Vorschau");
                setView(view);
                setCancelable(true);
                show();

            }
            if (this.selectedTagItem.getDataKind() == TagDataKind.Sound) {
                //toDo: hier muss noch der Code geschrieben werden, wenn das selectierte TagItem ein Sound ist.
                //toDo: es muss noch eine Überprüfung geschrieben, für den Fall, dass es sich um ein LinkTagItem handelt und nicht um ein AssetTagItem.
            }
        }
    }

    public void closePreviewDialog() {
        bildvorschau.clearFocus();
        this.dismiss();
    }
}
