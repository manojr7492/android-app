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

public class ProductsOneDetailDetailAdapterfoura extends RecyclerView.Adapter<ProductsOneDetailDetailAdapterfoura.ProductViewHolder> {


    private Context mCtxb;
    private List<ProductOneDetailDetaila> productListfour;

    ProductsOneDetailDetailAdapterfoura(Context mCtxb, List<ProductOneDetailDetaila> productListfour) {
        this.mCtxb = mCtxb;
        //for (ProductOneDetailDetaila productOneDetailDetaila : this.productListfour = productListfour) {

        //}

        this.productListfour = productListfour;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtxb);
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.product_list_one_detail_detail_a,null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductOneDetailDetaila productfour = productListfour.get(position);

        //loading the image

        holder.servicename.setText(productfour.getServicedetail());


    }

    @Override
    public int getItemCount() {
        return productListfour.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView servicename;



        ProductViewHolder(View itemView) {
            super(itemView);


            servicename = itemView.findViewById(R.id.textView);




        }
    }
}
