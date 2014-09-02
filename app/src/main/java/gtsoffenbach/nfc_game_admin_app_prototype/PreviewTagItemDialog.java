package gtsoffenbach.nfc_game_admin_app_prototype;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.nfc.NdefMessage;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


/**
 * Created by Marlon on 13.07.2014.
 * <p/>
 * Diese Klasse ist dazu da, ein Dialogfenster zu erstellen.
 * Dieser Dialog wird gebraucht wenn eine Vorschau von einem TagItem angezeigt werden soll
 * Diese Klasse bekommt das selectedTagItem und überprüft, welchen TagDataKind das TagItem hat(z.B. TagDataKind.Picture).
 * Ebenso überprüft die Klasse ob das SelectedTagItem ein Objekt der Klasse AssetTagItem oder der Klasse LinkTagItem ist.
 * Diese überprüfungen sind notwendig, um richtig mit dem SelectedTagItem agieren zu können.
 */
public class PreviewTagItemDialog  implements View.OnClickListener {

    private View view;
    private ImageView bildvorschau;
    private TagItem selectedTagItem;
    private LayoutInflater inflater = null;
    private Activity caller;
    private Button ButtonSchließen;

    public PreviewTagItemDialog(Activity caller, int THEME, final TagItem selectedTagItem) {

        this.caller = caller;
        this.selectedTagItem = selectedTagItem;
        this.inflater = (LayoutInflater) caller.getSystemService(caller.LAYOUT_INFLATER_SERVICE);
        this.scanSelectedTagItem();



        final AlertDialog.Builder builder = new AlertDialog.Builder(caller);
        builder.setTitle(selectedTagItem.getName());
        builder.setMessage("ID: " + selectedTagItem.getID() + System.getProperty("line.separator") + selectedTagItem.getBeschreibung());
        builder.setPositiveButton("Schreiben", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                if(selectedTagItem.getID()==1){
                    MainActivity.framework.createWriteNdef(new MainActivity().framework.NdefFromApp(((LinkTagItem) selectedTagItem).getAddress()));
                }else {
                    MainActivity.framework.createWriteNdef(new MainActivity().framework.NdefFromId(selectedTagItem.getID()));
                }
                MainActivity.framework.enableWrite();
            }
        });
        builder.setNegativeButton("Schließen", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
               dialog.dismiss();
            }
        });
        builder.show();
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

    }




}
