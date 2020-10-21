package com.ramasolutions.petagramfragments.fragments;

import com.ramasolutions.petagramfragments.adapter.MascotaAdapter;
import com.ramasolutions.petagramfragments.pojo.Mascota;

import java.util.ArrayList;

public interface IRecyclerViewFragmentView {

    public void generarLayout();

    public MascotaAdapter crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicilizarAdaptadorRV(MascotaAdapter adaptador);

}
