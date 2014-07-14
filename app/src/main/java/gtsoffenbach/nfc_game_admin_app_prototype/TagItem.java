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



    /*
    diese überschriebene Methode toString wird in der Klasse FragmentOverview aufgerufen.
    Die Methode soll den Text erstellen, welcher anschließend im TextView_SelectTagItem angezeigt wird.
    Der Name setzt sich zusammen aus dem datKind und dem Namen des TagItem.
    Um den Namen des TagItem zu bekommen, wird die Methode getName aufgerufen.
     */
    @Override
    public String toString() {
        return (this.getDataKind().name()+"/"+ getName());
    }

    public abstract String getName();

}
