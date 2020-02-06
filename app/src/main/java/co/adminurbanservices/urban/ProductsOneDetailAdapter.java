package co.adminurbanservices.urban;

import android.annotation.SuppressLint;
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

public class ProductsOneDetailAdapter extends RecyclerView.Adapter<ProductsOneDetailAdapter.ProductViewHolder> {


    private Context mCtxbb;
    private List<ProductOneDetail> productOneDetailList;
    private OnItemClickListener secListner;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    void setOnItemClickListener(ActivityOneDetail listener){
        secListner = listener;
    }

    ProductsOneDetailAdapter(Context mCtxbb, List<ProductOneDetail> productOneDetailList) {
        this.mCtxbb = mCtxbb;
        this.productOneDetailList = productOneDetailList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtxbb);
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.product_one_detail_list,null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductOneDetail ProductOneDetail = productOneDetailList.get(position);

        //loading the image
        Glide.with(mCtxbb)
                .load(ProductOneDetail.getImage())
                .into(holder.imageView);

        holder.servicename.setText(ProductOneDetail.getServicename());
    }

    @Override
    public int getItemCount() {
        return productOneDetailList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView servicename;
        ImageView imageView;

        ProductViewHolder(View itemView) {
            super(itemView);


            servicename = itemView.findViewById(R.id.ttextViewShortDesc);
            imageView = itemView.findViewById(R.id.Email);


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
