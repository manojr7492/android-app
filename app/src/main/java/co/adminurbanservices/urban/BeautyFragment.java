package co.adminurbanservices.urban;


import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BeautyFragment extends Fragment{

    private RecyclerView mRecyclerview;

    public BeautyFragment() {
        // Required empty public constructor
    }

    public static BeautyFragment newInstance() {
        BeautyFragment fragment = new BeautyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_beauty, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerview = (RecyclerView) getActivity().findViewById(R.id.beauty);

        ContactsAdapter contactsAdapter = new ContactsAdapter(generateData());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerview.setLayoutManager(layoutManager);
        mRecyclerview.setAdapter(contactsAdapter);
    }

    private ArrayList<ContactsModal> generateData(){
        ArrayList<ContactsModal> contactsModals = new ArrayList<>();

        contactsModals.add(new ContactsModal("Salon at Home"));
        contactsModals.add(new ContactsModal("Party Makeup Artist"));
        contactsModals.add(new ContactsModal("Bridal Makeup Artist"));
        contactsModals.add(new ContactsModal("Mehendi Artists"));
        contactsModals.add(new ContactsModal("Massage for Women"));
        contactsModals.add(new ContactsModal("Massage for Man"));
        contactsModals.add(new ContactsModal("Pre Bridal Beauty Packages"));
        Log.e("size",contactsModals.size()+"");
        return contactsModals;
    }
}

