package gtsoffenbach.nfc_game_admin_app_prototype;

/**
 * Created by Marlon on 05.07.2014.
 */
public class AssetTagItem extends TagItem {

    public AssetTagItem(String assetName, TagDataKind dataKind){
        this.assetName = assetName;
        this.setDataKind(dataKind);
    }

    private String assetName;

    public void setAssetName(String assetName){
        this.assetName = assetName;
    }

    /*
    Dateiname, der im Assetordner steht, wird in dieser Methode zurückgegeben
     */
    public String getAssetName(){
        return this.assetName;
    }


    /*
    Dateiname wird ohne Dateiendung zurückgegeben
     */
    @Override
    public String getName() {
        int position = this.getAssetName().indexOf("."); // die Position, bei der ein Punkt im String gefunden wurde
        String substring ="";
       if(position!=-1){
          substring = this.getAssetName().substring(0,position);
       }
       else{
           substring = this.getAssetName();
       }
       return substring;
    }


    /*
    liefert den Pfad relativ zum Assetordner
     */
    public String getAssetPath(){
        if(this.getDataKind()==TagDataKind.Picture){
            return ("pictures/"+this.getAssetName());
        }
        if(this.getDataKind()==TagDataKind.Sound){
            return ( "sounds/"+this.getAssetName());
        }
        else{
            return "";
        }
    }
}
