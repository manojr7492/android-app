package co.adminurbanservices.urban;


import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ProductsAdaptertwo extends RecyclerView.Adapter<ProductsAdaptertwo.ProductViewHolder> {


    private Context mCtxb;
    private List<Producttwo> productListfour;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }


    public ProductsAdaptertwo(Context mCtxb, List<Producttwo> productListfour) {
        this.mCtxb = mCtxb;
        this.productListfour = productListfour;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtxb);
        View view = inflater.inflate(R.layout.product_list_two,null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Producttwo productfour = productListfour.get(position);

        //loading the image

        holder.id.setText(String.valueOf(productfour.getId()));
        holder.email.setText((productfour.getServicename()));
        holder.start_time.setText(productfour.getStart_time());
        holder.end_time.setText(productfour.getEnd_time());
        holder.date.setText(productfour.getDate());

    }

    @Override
    public int getItemCount() {
        return productListfour.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView email, start_time, end_time, date, id;




        public ProductViewHolder(View itemView) {
            super(itemView);


            id = itemView.findViewById(R.id.orderid);
            email = itemView.findViewById(R.id.servicename);
            start_time = itemView.findViewById(R.id.starttime);
            end_time = itemView.findViewById(R.id.endtime);
            date = itemView.findViewById(R.id.date);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });


        }
    }
}
