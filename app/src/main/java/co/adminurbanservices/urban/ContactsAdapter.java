package co.adminurbanservices.urban;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsHolder> {

    private ArrayList<ContactsModal> mContactsModals;


    ContactsAdapter(ArrayList<ContactsModal> contactsModals){
        mContactsModals = contactsModals;
    }

    @NonNull
    @Override
    public ContactsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.single_contact, parent, false);

        return new ContactsHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsHolder holder, int position) {
        ContactsModal contact = mContactsModals.get(position);


        holder.mContactsNameView.setText(contact.getmName());
    }

    @Override
    public int getItemCount() {
        return mContactsModals.size();
    }


    static class ContactsHolder extends RecyclerView.ViewHolder{

        TextView mContactsNameView;


        ContactsHolder(View itemView) {
            super(itemView);
            mContactsNameView = itemView.findViewById(R.id.service);

        }

    }
}

