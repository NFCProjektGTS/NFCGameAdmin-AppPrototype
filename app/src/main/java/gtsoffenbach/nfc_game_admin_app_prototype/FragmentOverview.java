package gtsoffenbach.nfc_game_admin_app_prototype;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Marlon on 08.07.2014.
 */
public class FragmentOverview extends Fragment implements View.OnClickListener, ListView.OnItemClickListener {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private TagItemMagazine tagItemMagazine;
    private ListView listViewTagItems;
    private Activity caller;
    private TagItem selectedTagItem = null;

    public FragmentOverview() {
    }


    public static FragmentOverview newInstance(int sectionNumber) {
        FragmentOverview fragment = new FragmentOverview();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_overview, container, false);

        listViewTagItems = (ListView) rootView.findViewById(R.id.listView_TagItems);
        listViewTagItems.setOnItemClickListener(this);
        this.fillTagItemListView();





        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);



        this.tagItemMagazine = ((MainActivity) activity).getTagItemMagazine();
    }

    @Override
    public void onClick(View view) {

            if(selectedTagItem!=null){

            }


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
       this.selectedTagItem = tagItemMagazine.getItemAt(position);

        caller = this.getActivity();
        PreviewTagItemDialog previewTagItemDialog = new PreviewTagItemDialog(caller, AlertDialog.THEME_HOLO_LIGHT,this.selectedTagItem);


       //textViewSelectTagItem.setText("Ausgewähltes TagItem: " + selectedTagItem.getName());

    }


    private void fillTagItemListView(){
        ArrayList tagNames = new ArrayList<String>();
        for(int i = 0; i<tagItemMagazine.getAllTagItems().size();i++){
            tagNames.add(i,"  "+tagItemMagazine.getAllTagItems().get(i).getID()+" "+tagItemMagazine.getAllTagItems().get(i).getName());
        }
        ArrayAdapter<TagItem> listAdapter = new ArrayAdapter<TagItem>(getActivity(), R.layout.tagitemlist_item, tagNames);
        this.listViewTagItems.setAdapter(listAdapter);
    }
}
