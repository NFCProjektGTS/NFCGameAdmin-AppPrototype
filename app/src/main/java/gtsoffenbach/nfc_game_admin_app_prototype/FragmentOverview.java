package gtsoffenbach.nfc_game_admin_app_prototype;

import android.app.Activity;
import android.app.AlertDialog;
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

/**
 * Created by Marlon on 08.07.2014.
 */
public class FragmentOverview extends Fragment implements View.OnClickListener, ListView.OnItemClickListener {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private TagItemMagazine tagItemMagazine;
    private ListView listViewTagItems;
    private TextView textViewSelectTagItem;
    private Button ButtonPreview;
    private Button ButtonVorschauSchließen;
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

        textViewSelectTagItem = (TextView) rootView.findViewById(R.id.textView_SelectTagItem);
        ButtonPreview = (Button) rootView.findViewById(R.id.button_Vorschau);
        ButtonPreview.setOnClickListener(this);
        ButtonVorschauSchließen = (Button) rootView.findViewById((R.id.button_VorschauSchließen));



        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));

        this.tagItemMagazine = ((MainActivity) activity).getTagItemMagazine();
    }

    @Override
    public void onClick(View view) {
            if(selectedTagItem!=null){
                caller = this.getActivity();
                PreviewTagItemDialog previewTagItemDialog = new PreviewTagItemDialog(caller, AlertDialog.THEME_HOLO_LIGHT,this.selectedTagItem);
            }


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
       this.selectedTagItem = ((TagItem) listViewTagItems.getItemAtPosition(position));
       String tagItemName ="";
       tagItemName = selectedTagItem.toString();

      //char[] text= tagItemName.toCharArray();

       textViewSelectTagItem.setText("Ausgewähltes TagItem: " + tagItemName);

    }


    private void fillTagItemListView(){
        ArrayAdapter<TagItem> listAdapter = new ArrayAdapter<TagItem>(getActivity(), R.layout.tagitemlist_item, this.tagItemMagazine.getAllTagItems());
        this.listViewTagItems.setAdapter(listAdapter);
    }
}
