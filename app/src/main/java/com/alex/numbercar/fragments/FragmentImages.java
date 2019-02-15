package com.alex.numbercar.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alex.numbercar.R;
import com.alex.numbercar.SlideshowDialogFragment;
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

public class FragmentImages extends Fragment {

    private String number;
    private ProgressDialog pDialog;
    private GalleryAdapter mAdapter;
    private List<Url> urlImg;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    private EndPoint mEndPoint;
    Context context;

    public FragmentImages() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (getArguments() != null) {
            number = bundle.getString("number");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_images, container, false);

        Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        //toolbar.setPadding(0,ScreenUtil.getStatusBarHeight(this.getActivity()),0,0);
        toolbar.setTitle(number);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mEndPoint = APIUtils.getFileServise();

        pDialog = new ProgressDialog(getContext());
        urlImg = new ArrayList<>();

        recyclerView.addOnItemTouchListener(new GalleryAdapter.RecyclerTouchListener(getContext(), recyclerView, new GalleryAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("images", (Serializable) urlImg);
                bundle.putInt("position", position);

               FragmentManager fragmentManager = getFragmentManager();
                //FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                SlideshowDialogFragment newFragment = SlideshowDialogFragment.newInstance();
                newFragment.setArguments(bundle);
                newFragment.show(ft, "slideshow");
            }

            /*@Override
            public void onLongClick(View view, int position) {

            }*/
        }));

        fetchImages();

        return view;
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
                    mAdapter = new GalleryAdapter(urlImg, getContext());
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
