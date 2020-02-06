package co.adminurbanservices.urban;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class ApplianceFragment extends Fragment {

    public ApplianceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_appliance, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecyclerView mRecyclerview = Objects.requireNonNull(getActivity()).findViewById(R.id.appliance);

        ContactsAdapter contactsAdapter = new ContactsAdapter(generateData());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerview.setLayoutManager(layoutManager);
        mRecyclerview.setAdapter(contactsAdapter);
    }

    private ArrayList<ContactsModal> generateData(){
        ArrayList<ContactsModal> contactsModals = new ArrayList<>();

        contactsModals.add(new ContactsModal("Ac Service and Repair"));
        contactsModals.add(new ContactsModal("RO or Water Purifier Repair"));
        contactsModals.add(new ContactsModal("Washing Machine Repair"));
        contactsModals.add(new ContactsModal("Refrigerator Repair"));
        contactsModals.add(new ContactsModal("Microwave Repair"));
        contactsModals.add(new ContactsModal("Chimney and Hob Repair"));
        contactsModals.add(new ContactsModal("TV installation & Repair"));
        contactsModals.add(new ContactsModal("Geyser/Water Heater Repair"));
        contactsModals.add(new ContactsModal("iPhone,iPad,Mac Repair"));
        contactsModals.add(new ContactsModal("Laptop Repair"));
        contactsModals.add(new ContactsModal("Computer Repair"));


        return contactsModals;
    }
}
