package gtsoffenbach.nfc_game_admin_app_prototype;

import android.nfc.NdefRecord;

import java.nio.charset.Charset;

/**
 * Created by Marlon on 05.07.2014.
 */
public class StartTagItem extends TagItem {
    private String address;
    private short type = 1;

    public StartTagItem(String name, String beschreibung,String address) {
        super(name, beschreibung);
        this.address = address;
    }
    public String getAddress(){
        return address;
    }
    @Override
    public short getType(){
        return type;
    }

}
