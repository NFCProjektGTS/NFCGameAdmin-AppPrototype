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
 */
public class PreviewTagItemDialog extends AlertDialog {

    private View view;
    private ImageView bildvorschau;
    private TagItem selectedTagItem;
    private LayoutInflater inflater = null;
    private Activity caller;
    InputStream ims = null;

    public PreviewTagItemDialog(Activity caller, int THEME, TagItem selectedTagItem){
        super(caller, THEME);
        this.caller = caller;
        this.selectedTagItem = selectedTagItem;
        this.inflater = (LayoutInflater) caller.getSystemService(caller.LAYOUT_INFLATER_SERVICE);

        this.scanSelectedTagItem();



    }

    private void scanSelectedTagItem(){
        if(this.selectedTagItem == null){

        }
        else {
            if (this.selectedTagItem.getDataKind() == TagDataKind.Picture) {

                view = inflater.inflate(R.layout.preview_picture_tagitem, null);
                this.bildvorschau = (ImageView) view.findViewById(R.id.imageView_BildVorschau);

                try {
                    this.ims = caller.getAssets().open("pictures/"+ ((AssetTagItem) selectedTagItem).getAssetName());
                }catch (IOException e){
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

            }
        }
    }

    public void closePreviewDialog(){
        bildvorschau.clearFocus();
        this.dismiss();
    }
}
