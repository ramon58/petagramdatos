package com.ramasolutions.petagramfragments.fragments;

import com.ramasolutions.petagramfragments.adapter.FotoAdapter;
import com.ramasolutions.petagramfragments.pojo.Foto;

import java.util.ArrayList;

public interface IRecyclerViewProfileFragmentView {
    public void generarLayout();

    public FotoAdapter crearAdaptador(ArrayList<Foto> fotos);

    public void inicilizarAdaptadorRV(FotoAdapter adaptador);
}
