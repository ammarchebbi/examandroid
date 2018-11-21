package exam.esprit.tn.examenandroid;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    View view;
    ListView listFilms;
    List<Film> list;
    public static Film dataModel;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_list, container, false);
        listFilms = view.findViewById(R.id.listViewFilms);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        final DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        list = databaseHelper.getAllFilms();
        CustomAdapter customAdapter = new CustomAdapter(list, getContext());
        customAdapter.notifyDataSetChanged();
        listFilms.setAdapter(customAdapter);
        listFilms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListFragment.dataModel = list.get(position);
                startActivity(new Intent(getContext(), DetailActivity.class));
            }
        });
    }
}
