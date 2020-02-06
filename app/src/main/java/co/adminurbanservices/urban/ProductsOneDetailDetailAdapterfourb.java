package co.adminurbanservices.urban;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductsOneDetailDetailAdapterfourb extends RecyclerView.Adapter<ProductsOneDetailDetailAdapterfourb.ProductViewHolder> {


    private Context mCtxbb;
    private List<ProductOneDetailDetailb> productListfourb;

    public ProductsOneDetailDetailAdapterfourb(Context mCtxbb, List<ProductOneDetailDetailb> productListfourb) {
        this.mCtxbb = mCtxbb;
        this.productListfourb = productListfourb;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtxbb);
        View view = inflater.inflate(R.layout.product_list_one_detail_detail_b,null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        ProductOneDetailDetailb productfourb = productListfourb.get(position);

        //loading the image

        Glide.with(mCtxbb)
                .load(productfourb.getImage())
                .into(holder.imageView);



        holder.servicedetail.setText(productfourb.getServicedetail());
        holder.username.setText(productfourb.getUsername());
        holder.rate.setText(productfourb.getRate());
        holder.date.setText(productfourb.getDate());
    }

    @Override
    public int getItemCount() {
        return productListfourb.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView servicedetail,username,rate,date;
        ImageView imageView;



        public ProductViewHolder(View itemView) {
            super(itemView);


            servicedetail = itemView.findViewById(R.id.massage);
            rate = itemView.findViewById(R.id.rate);
            date = itemView.findViewById(R.id.date);
            username = itemView.findViewById(R.id.username);
            imageView = itemView.findViewById(R.id.imageView4);




        }
    }
}
