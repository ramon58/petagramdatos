package com.ramasolutions.petagramfragments.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ramasolutions.petagramfragments.R;
import com.ramasolutions.petagramfragments.adapter.FotoAdapter;
import com.ramasolutions.petagramfragments.pojo.Foto;
import com.ramasolutions.petagramfragments.presentador.IRecyclerViewFragmentPresenter;
import com.ramasolutions.petagramfragments.presentador.RecyclerViewProfileFragmentPresenter;

import java.util.ArrayList;


public class ProfileFragment extends Fragment implements IRecyclerViewProfileFragmentView {

    private ArrayList<Foto> fotos;
    private RecyclerView rvFotos;
    private IRecyclerViewFragmentPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this vista.fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        rvFotos = (RecyclerView) v.findViewById(R.id.rvImgProfile);
        presenter = new RecyclerViewProfileFragmentPresenter(this,getContext());
        return v;
    }

    @Override
    public void generarLayout() {
        GridLayoutManager glm = new GridLayoutManager(getActivity(),3);
        rvFotos.setLayoutManager(glm);
    }

    @Override
    public FotoAdapter crearAdaptador(ArrayList<Foto> fotos) {
        FotoAdapter adaptador = new FotoAdapter(fotos, getActivity());
        return adaptador;
    }

    @Override
    public void inicilizarAdaptadorRV(FotoAdapter adaptador) {
        rvFotos.setAdapter(adaptador);
    }

}