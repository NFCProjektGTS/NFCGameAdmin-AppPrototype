package gtsoffenbach.nfc_game_admin_app_prototype;

import android.nfc.NdefRecord;

import java.math.BigInteger;

/**
 * Created by Marlon on 05.07.2014.
 */
public class TagItem  {

    public TagItem(String name,String beschreibung){
        this.name = name;
        this.beschreibung = beschreibung;
        counter+=1;
        this.ID = counter;
    }

    private String name;
    private int ID;
    private String beschreibung;
    static int counter = 0;





    public String getName(){
        return this.name;
    }

    public int getID() {
        return  this.ID;
    }

    public String getBeschreibung(){
        return  this.beschreibung;
    }


    public short getNfcTnf(){
        return NdefRecord.TNF_WELL_KNOWN;
    }

    public byte[] getNfcType(){
        final BigInteger bi = BigInteger.valueOf(this.ID);
        return bi.toByteArray();
    }

    public byte[] getNfcPayload(){
        return null;
    }


    @Override
    public String toString() {
        return "  "+getID()+" | "+getName();
    }
}
