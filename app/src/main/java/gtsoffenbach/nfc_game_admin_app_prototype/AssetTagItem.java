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

    public String getAssetName(){
        return this.assetName;
    }

}
