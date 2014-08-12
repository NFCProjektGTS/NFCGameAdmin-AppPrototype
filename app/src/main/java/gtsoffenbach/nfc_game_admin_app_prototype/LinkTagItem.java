package gtsoffenbach.nfc_game_admin_app_prototype;

/**
 * Created by Marlon on 05.07.2014.
 */
public class LinkTagItem extends TagItem {
    private String address;

    public LinkTagItem(String name, String beschreibung, String address) {
        super(name, beschreibung);
        this.address = address;
    }


    public String getAddress() {
        return address;
    }

}
