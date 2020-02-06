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

public class ProductsFourDetailAdapter extends RecyclerView.Adapter<ProductsFourDetailAdapter.ProductViewHolder> {


    private Context mCtxbbaa;
    private List<ProductFourDetail> productFourDetailList;
    private OnItemClickListener asecListner;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    void setOnItemClickListener(OnItemClickListener listener){
        asecListner = listener;
    }

    ProductsFourDetailAdapter(Context mCtxbbaa, List<ProductFourDetail> productFourDetailList) {
        this.mCtxbbaa = mCtxbbaa;
        this.productFourDetailList = productFourDetailList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtxbbaa);
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.product_four_detail_list,null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductFourDetail ProductFourDetail = productFourDetailList.get(position);

        //loading the image
     /*   Glide.with(mCtxbbaa)
                .load(ProductOneDetail.getImage())
                .into(holder.imageView);
*/
        holder.servicename.setText(ProductFourDetail.getServicename());
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
