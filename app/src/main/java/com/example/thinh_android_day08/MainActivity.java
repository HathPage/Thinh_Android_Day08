package com.example.thinh_android_day08;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thinh_android_day08.adapters.ProductAdapter;
import com.example.thinh_android_day08.api.ApiUtils;
import com.example.thinh_android_day08.interfaces.IHomeView;
import com.example.thinh_android_day08.objects.AllProductResponse;
import com.example.thinh_android_day08.objects.Product;
import com.example.thinh_android_day08.presenter.HomePresenter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements IHomeView, ProductAdapter.IOnProductItemClickListener{
    private HomePresenter mHomePresenter;
    private RecyclerView rvProduct;
    private ArrayList<Product> mListProduct;
    private ProductAdapter mProductAdapter;
    private RelativeLayout searchIsland;
    private EditText edtSearch;
    private ImageButton btnExitSearch, btnSearch;
    private String searchQuery="";
    private Button btnSearchItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        getAllProduct();
    }

    private void initData() {
        mHomePresenter = new HomePresenter(this);
        mListProduct = new ArrayList<>();
        mProductAdapter = new ProductAdapter(mListProduct);
        mProductAdapter.setCallback(this);
        rvProduct.setAdapter(mProductAdapter);
    }
    public void getAllProduct(){
        mHomePresenter.getAllProduct();
    }
    private void initView() {
        rvProduct = findViewById(R.id.rv_product);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        rvProduct.setLayoutManager(layoutManager);
        searchIsland = findViewById(R.id.search_island);
        btnSearch = findViewById(R.id.btn_search);
        edtSearch = findViewById(R.id.edt_search);
        btnExitSearch = findViewById(R.id.btn_exit_search);
        btnSearchItem = findViewById(R.id.btn_search_item);

        edtSearch.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSearchIsland();
            }
        });
        btnExitSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSearchIsland();
            }
        });
        btnSearchItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchQuery = edtSearch.getText().toString().trim();
                searchProduct(searchQuery);
                hideSearchIsland();
            }
        });
    }
    private void searchProduct(String query) {
        mHomePresenter.searchProduct(query);
    }
    public void searchSuccess(AllProductResponse response) {
        mListProduct.clear();
        mProductAdapter.updateData((ArrayList<Product>) response.getProducts());
        mProductAdapter.notifyDataSetChanged();
    }

    @Override
    public void searchError(String error) {

    }

    private void showSearchIsland() {
        searchIsland.setVisibility(View.VISIBLE);
    }

    private void hideSearchIsland() {
        searchIsland.setVisibility(View.GONE);
    }

    @Override
    public void getAllProductSuccess(AllProductResponse response) {
        mListProduct.clear();
        mProductAdapter.updateData((ArrayList<Product>) response.getProducts());
        mProductAdapter.notifyDataSetChanged();
    }

    @Override
    public void getAllProductError(String error) {
        Log.d("MainActivity", "Fail: ");
    }

    @Override
    public void onShopNowClick(Product product) {
        Toast.makeText(this, product.getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFavorite(int position) {
        Product product = mListProduct.get(position);
        product.setFavorite(!product.isFavorite());
        mListProduct.set(position, product);
        mProductAdapter.notifyItemChanged(position);
    }
}