package co.adminurbanservices.urban;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyfirstAdapter extends RecyclerView.Adapter<MyfirstAdapter.MyViewHolder> {

    //  public BlankFragment blankFragment;
    private Myfirst mCtx;
    public List<MyfirstProduct> productList;
    //  private String[] mDataset;

    public MyfirstAdapter(Myfirst mCtx, List<MyfirstProduct> productList) {
        // this.blankFragment = blankFragment;
        this.productList = productList;
        this.mCtx = mCtx;

    }

  /*  public static class MyViewHolder extends RecyclerView.ViewHolder{
        public CardView mCardView;
        public TextView mTextView;
        public MyViewHolder(View v){
            super(v);

            mCardView = (CardView) v.findViewById(R.id.card_view);
            mTextView = (TextView) v.findViewById(R.id.tv_text);

        }

    }
*/


    @Override
    public MyfirstAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyfirstAdapter.MyViewHolder holder, int position) {
        MyfirstProduct product = productList.get(position);
        Glide.with(mCtx).load(product.getImage()).into(holder.imageView);
        holder.service.setText(product.getService());

    }

    @Override
    public int getItemCount() { return productList.size(); }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView service;
        ImageView imageView;

        MyViewHolder(View itemView){
            super(itemView);

            service = itemView.findViewById(R.id.iv_image);
            imageView = itemView.findViewById(R.id.tv_text);
        }
    }

}
