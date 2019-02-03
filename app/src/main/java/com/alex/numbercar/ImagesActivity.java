package com.alex.numbercar;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.alex.numbercar.adapter.GalleryAdapter;
import com.alex.numbercar.common.APIUtils;
import com.alex.numbercar.interfc.EndPoint;
import com.alex.numbercar.model.Images;
import com.alex.numbercar.model.Url;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImagesActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    private GalleryAdapter mAdapter;
    private List<Url> urlImg;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    private EndPoint mEndPoint;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mEndPoint = APIUtils.getFileServise();

        pDialog = new ProgressDialog(this);
        urlImg = new ArrayList<>();

        recyclerView.addOnItemTouchListener(new GalleryAdapter.RecyclerTouchListener(getApplicationContext(), recyclerView, new GalleryAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("images", (Serializable) urlImg);
                bundle.putInt("position", position);

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                SlideshowDialogFragment newFragment = SlideshowDialogFragment.newInstance();
                newFragment.setArguments(bundle);
                newFragment.show(ft, "slideshow");
            }

            /*@Override
            public void onLongClick(View view, int position) {

            }*/
        }));

        fetchImages();
    }

    private void fetchImages() {

        pDialog.setMessage("Загрузка...");
        pDialog.show();

        mEndPoint.getJson("json").enqueue(new Callback<List<Images>>() {
            @Override
            public void onResponse(Call<List<Images>> call, Response<List<Images>> response) {
                if (response.isSuccessful()) {
                    List<Images> repos = response.body();
                    pDialog.hide();
                    Url urlList = null;

                    for (int i = 0; i < repos.size(); i++) {
                        urlList = new Url();
                        String sm = repos.get(i).getUrl().getSmall();
                        String med = repos.get(i).getUrl().getMedium();
                        String lg = repos.get(i).getUrl().getLarge();

                        urlList.setSmall(sm);
                        urlList.setMedium(med);
                        urlList.setLarge(lg);

                        urlImg.add(urlList);
                    }
                    mAdapter = new GalleryAdapter(urlImg, getBaseContext());
                    mAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(mAdapter);
                } else {
                    Toast.makeText(context, "no", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Images>> call, Throwable t) {

            }
        });

    }
}
