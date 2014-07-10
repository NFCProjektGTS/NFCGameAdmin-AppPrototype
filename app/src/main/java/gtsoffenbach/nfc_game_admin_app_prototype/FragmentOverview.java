package gtsoffenbach.nfc_game_admin_app_prototype;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Marlon on 08.07.2014.
 */
public class FragmentOverview extends Fragment implements View.OnClickListener, ListView.OnItemClickListener {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private TagItemMagazine tagItemMagazine;
    private ListView listViewTagItems;

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
        this.fillTagItemListView();
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

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    private void fillTagItemListView(){
        ArrayAdapter<TagItem> listAdapter = new ArrayAdapter<TagItem>(getActivity(), R.layout.tagitemlist_item, this.tagItemMagazine.getAllTagItems());
        this.listViewTagItems.setAdapter(listAdapter);
    }
}
