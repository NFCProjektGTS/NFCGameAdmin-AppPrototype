package gtsoffenbach.nfc_game_admin_app_prototype;

import android.nfc.NdefRecord;

import java.nio.charset.Charset;

/**
 * Created by Marlon on 05.07.2014.
 */
public class StartTagItem extends TagItem {
    private String address;

    public StartTagItem(String name, String beschreibung) {
        super(name, beschreibung);

    }




    @Override
    public short getNfcTnf() {
        return NdefRecord.TNF_MIME_MEDIA;
    }

    @Override
    public byte[] getNfcType() {
        return "application/gtsoffenbach.nfcgamespieler_appprototype".getBytes(Charset.forName("US-ASCII"));
    }

    @Override
    public byte[] getNfcPayload() {
        return "Android is cool".getBytes(Charset.forName("US-ASCII"));
    }
}
