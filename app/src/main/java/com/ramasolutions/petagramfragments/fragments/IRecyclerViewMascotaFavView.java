package com.ramasolutions.petagramfragments.fragments;

import com.ramasolutions.petagramfragments.adapter.MascotaFavoritaAdapter;
import com.ramasolutions.petagramfragments.pojo.Mascota;

import java.util.ArrayList;

public interface IRecyclerViewMascotaFavView {

    public void generarLayout();

    public MascotaFavoritaAdapter crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicilizarAdaptadorRV(MascotaFavoritaAdapter adaptador);
}
