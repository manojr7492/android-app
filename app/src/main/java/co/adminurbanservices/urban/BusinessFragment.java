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
public class BusinessFragment extends Fragment {

    public BusinessFragment() {
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
        return inflater.inflate(R.layout.fragment_business, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecyclerView mRecyclerview = Objects.requireNonNull(getActivity()).findViewById(R.id.business);

        ContactsAdapter contactsAdapter = new ContactsAdapter(generateData());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerview.setLayoutManager(layoutManager);
        mRecyclerview.setAdapter(contactsAdapter);
    }

    private ArrayList<ContactsModal> generateData(){
        ArrayList<ContactsModal> contactsModals = new ArrayList<>();

        contactsModals.add(new ContactsModal("Aswin Vayiravan"));
        contactsModals.add(new ContactsModal("Muthu Alagappan M"));
        contactsModals.add(new ContactsModal("@string/business1"));
        contactsModals.add(new ContactsModal("Aswin Vayiravan"));
        contactsModals.add(new ContactsModal("Muthu Alagappan M"));
        contactsModals.add(new ContactsModal("SriramaMoorthy S"));
        contactsModals.add(new ContactsModal("Puviyarasu V"));
        contactsModals.add(new ContactsModal("Arun Kumar K R"));
        contactsModals.add(new ContactsModal("Venkat Raman"));

        return contactsModals;
    }
}
