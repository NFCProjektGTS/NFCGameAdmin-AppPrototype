package gtsoffenbach.nfc_game_admin_app_prototype;

import android.content.Context;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Marlon on 05.07.2014.
 *
 * Diese Klasse verwaltet alle zur Verfügung stehenden tagItems
 * Es darf nur ein Objekt dieser Klasse geben
 */


public class TagItemMagazine {

    ArrayList<TagItem> tagItems;

    public TagItemMagazine(){
        tagItems = new ArrayList<TagItem>();

        AssetTagItem tagItemCleCle = new AssetTagItem("clecle.jpg",TagDataKind.Picture);
        tagItems.add(tagItemCleCle);

        AssetTagItem tagItemPotter = new AssetTagItem("potter.jpg",TagDataKind.Picture);
        tagItems.add(tagItemPotter);

        AssetTagItem tagItemSunset = new AssetTagItem("sunset.jpg",TagDataKind.Picture);
        tagItems.add(tagItemSunset);

        AssetTagItem tagItemWaterlilies = new AssetTagItem("waterlilies.jpg",TagDataKind.Picture);
        tagItems.add(tagItemWaterlilies);

        AssetTagItem tagItemCan = new AssetTagItem("can.mp3",TagDataKind.Sound);
        tagItems.add(tagItemCan);

        AssetTagItem tagItemDailup = new AssetTagItem("dailup.mp3",TagDataKind.Sound);
        tagItems.add(tagItemDailup);

        AssetTagItem tagItemDoor = new AssetTagItem("door.mp3",TagDataKind.Sound);
        tagItems.add(tagItemDoor);

        AssetTagItem tagItemDrops = new AssetTagItem("drops.mp3",TagDataKind.Sound);
        tagItems.add(tagItemDrops);

        AssetTagItem tagItemFrezzer = new AssetTagItem("frezzer.mp3",TagDataKind.Sound);
        tagItems.add(tagItemFrezzer);

        AssetTagItem tagItemMatch = new AssetTagItem("Match.mp3",TagDataKind.Sound);
        tagItems.add(tagItemMatch);

    }




    public void addTagItem(TagItem tagItem){
        tagItems.add(tagItem);

    }
    public TagItem[] getAllTagItems(){
        TagItem[] allTagItems = new TagItem[tagItems.size()];
        for(int i =0;i<(tagItems.size());i++){
            allTagItems[i] = tagItems.get(i);
        }
        return allTagItems;
    }
}
