package gtsoffenbach.nfc_game_admin_app_prototype;

/**
 * Created by Marlon on 05.07.2014.
 *
 * Die Klasse soll die Daten, die auf einen NFC Tag geschrieben werden, representieren
   TagItem sollen die Objekte sein die an die Klasse übergeben werden, welche die NFC Tags liest und beschreibt.
 */


public abstract class TagItem {

  private TagDataKind dataKind;

    public void setDataKind(TagDataKind dataKind){
        this.dataKind = dataKind;
    }

    public TagDataKind getDataKind(){
        return this.dataKind;
    }

    @Override
    public String toString() {
        return (this.getDataKind().name()+"/"+ getName());
    }

    public abstract String getName();

}
