package com.example.thinh_android_day08.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.thinh_android_day08.R;
import com.example.thinh_android_day08.objects.Product;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private ArrayList<Product> mListProduct;
    private Context mContext;

    private IOnProductItemClickListener callback;
    public ProductAdapter(ArrayList<Product> listProduct) {
        this.mListProduct = listProduct;
    }

    public void setCallback(IOnProductItemClickListener callback) {
        this.callback = callback;
    }
    public void updateData(ArrayList<Product> listProduct){
        this.mListProduct.clear();
        this.mListProduct.addAll(listProduct);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        Product product = mListProduct.get(position);
        holder.tvProductName.setText(product.getTitle());
        Glide.with(mContext).load(product.getThumbnail()).into(holder.imgProduct);
    }

    @Override
    public int getItemCount() {
        return mListProduct != null ? mListProduct.size():0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgProduct, imgFavorite;
        TextView tvProductName;
        LinearLayout llShopNow;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.img_product);
            imgFavorite = itemView.findViewById(R.id.img_favorite);
            tvProductName = itemView.findViewById(R.id.tv_name);
            llShopNow = itemView.findViewById(R.id.ll_shop_now);

            llShopNow.setOnClickListener(this);
            imgFavorite.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (callback != null) {
                if (v.getId() == R.id.ll_shop_now) {
                    Product product = mListProduct.get(getAdapterPosition());
                    callback.onShopNowClick(product);
                } else if(v.getId() == R.id.img_favorite) {
                    callback.onFavorite(getAdapterPosition());
                }
            }
        }
    }
    public interface IOnProductItemClickListener{
        void onShopNowClick(Product product);
        void onFavorite(int position);
    }
}
