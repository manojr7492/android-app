package co.adminurbanservices.urban;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;

/**
 * Created by haerul on 17/03/18.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Contact> contacts;
    private Context context;
    private OnItemClickListener secListner;


    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    void setOnItemClickListener(OnItemClickListener listener){
        secListner = listener;
    }

    Adapter(List<Contact> contacts, Context context) {
        this.contacts = contacts;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.searchitem, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Contact contact = contacts.get(position);
      //  holder.name.setText(contacts.get(position).getName());
      // holder.email.setText(contacts.get(position).getEmail());
        Glide.with(context)
                .load(contact.getEmail())
                .into(holder.email);

        holder.name.setText(contact.getServicename());



    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView email;
        MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.Name);
            email = itemView.findViewById(R.id.Email);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(secListner != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            secListner.onItemClick(position);
                        }
                    }
                }
            });
        }
    }


}
