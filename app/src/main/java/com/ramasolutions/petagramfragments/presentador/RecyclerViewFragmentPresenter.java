package com.ramasolutions.petagramfragments.presentador;

import android.content.Context;

import com.ramasolutions.petagramfragments.db.ConstructorMascotas;
import com.ramasolutions.petagramfragments.pojo.Mascota;
import com.ramasolutions.petagramfragments.fragments.IRecyclerViewFragmentView;

import java.util.ArrayList;

public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter{

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public RecyclerViewFragmentPresenter (IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context){
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        obtenerItems();
    }

    @Override
    public void obtenerItems() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerDatosIniciales(); //datos de un ArrayList
        mostrarItemsRV();
    }

    @Override
    public void mostrarItemsRV() {
        iRecyclerViewFragmentView.inicilizarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador((mascotas)));
        iRecyclerViewFragmentView.generarLayout();
    }
}
