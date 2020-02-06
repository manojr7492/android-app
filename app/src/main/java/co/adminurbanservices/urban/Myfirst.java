package co.adminurbanservices.urban;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Myfirst extends Fragment {

    private Context mCtx;
    //List<Product> productList;
    List<MyfirstProduct> productList = new ArrayList<>();
    RecyclerView rv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_myfirst, container, false);

        rv = rootView.findViewById(R.id.rv_recycler_view);
        rv.setHasFixedSize(true);
       // List<Product> productList = new ArrayList<>();
       // MyfirstAdapter adapter = new MyfirstAdapter(Myfirst.this, productList);
       // ProductsAdapter adapter = new ProductsAdapter(productList);
       //rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        return rootView;
    }
    private void loadProducts() {

        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                //adding the product to product list
                                productList.add(new MyfirstProduct(
                                        product.getInt("id"),
                                        product.getString("service"),
                                        product.getString("image")
                                ));
                             //   Toast.makeText(mCtx, "vlaue is "+i, Toast.LENGTH_LONG).show();
                            }

                            //creating adapter object and setting it to recyclerview
                            MyfirstAdapter adapter = new MyfirstAdapter(Myfirst.this, productList);
                            rv.setAdapter(adapter);
                           // adapter.setOnItemClickListener(ActivityOne.this);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(getContext()).add(stringRequest);
    }
}
