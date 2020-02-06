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

public class ProductsOneDetailDetailDetailAdapter extends RecyclerView.Adapter<ProductsOneDetailDetailDetailAdapter.ProductViewHolder> {


    private Context mCtxbb;
    private List<ProductOneDetailDetailDetail> productOneDetailList;
  //  private OnItemClickListener secListner;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }



    ProductsOneDetailDetailDetailAdapter(Context mCtxbb, List<ProductOneDetailDetailDetail> productOneDetailList) {
        this.mCtxbb = mCtxbb;
        this.productOneDetailList = productOneDetailList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtxbb);
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.product_one_detail_detail_detail_list,null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductOneDetailDetailDetail ProductOneDetailDetailDetail = productOneDetailList.get(position);

        //loading the image
        holder.servicename.setText(ProductOneDetailDetailDetail.getServicename());
        holder.abc.setText(ProductOneDetailDetailDetail.getRate());
    }

    @Override
    public int getItemCount() {
        return productOneDetailList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView servicename,abc;
       // ImageView imageView;

        ProductViewHolder(View itemView) {
            super(itemView);


            servicename = itemView.findViewById(R.id.textView6);
            abc = itemView.findViewById(R.id.textView8);


        }
    }
}
