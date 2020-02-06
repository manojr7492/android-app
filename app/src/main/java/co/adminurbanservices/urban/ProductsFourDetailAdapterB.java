package co.adminurbanservices.urban;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ProductsFourDetailAdapterB extends RecyclerView.Adapter<ProductsFourDetailAdapterB.ProductViewHolder> {


    private Context mCtxbbaa;
    private List<ProductFourDetailB> productFourDetailList;
    private OnItemClickListener asecListner;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    void setOnItemClickListener(OnItemClickListener listener){
        asecListner = listener;
    }

    ProductsFourDetailAdapterB(Context mCtxbbaa, List<ProductFourDetailB> productFourDetailList) {
        this.mCtxbbaa = mCtxbbaa;
        this.productFourDetailList = productFourDetailList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtxbbaa);
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.product_four_detail_list_b,null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductFourDetailB ProductFourDetailB = productFourDetailList.get(position);

        //loading the image
     /*   Glide.with(mCtxbbaa)
                .load(ProductOneDetail.getImage())
                .into(holder.imageView);
*/
        holder.servicename.setText(ProductFourDetailB.getServicename());
    }

    @Override
    public int getItemCount() {
        return productFourDetailList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView servicename;
        //  ImageView imageView;

        ProductViewHolder(View itemView) {
            super(itemView);


            servicename = itemView.findViewById(R.id.ttextViewShortDesc);
            //  imageView = itemView.findViewById(R.id.Email);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(asecListner != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            asecListner.onItemClick(position);
                        }
                    }
                }
            });



        }
    }
}
