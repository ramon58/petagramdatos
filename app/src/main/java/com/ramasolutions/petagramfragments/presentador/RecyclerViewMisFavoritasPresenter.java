package com.ramasolutions.petagramfragments.presentador;

import android.content.Context;

import com.ramasolutions.petagramfragments.db.ConstructorMascotas;
import com.ramasolutions.petagramfragments.pojo.Mascota;
import com.ramasolutions.petagramfragments.fragments.IRecyclerViewMascotaFavView;

import java.util.ArrayList;

public class RecyclerViewMisFavoritasPresenter  implements IRecyclerViewFragmentPresenter{

    private IRecyclerViewMascotaFavView iRecyclerViewMascotaFavView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public RecyclerViewMisFavoritasPresenter (IRecyclerViewMascotaFavView iRecyclerViewMascotaFavView, Context context){
        this.iRecyclerViewMascotaFavView = iRecyclerViewMascotaFavView;
        this.context = context;
        obtenerItems();
    }

    @Override
    public void obtenerItems() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerMascotasFavoritas();
        mostrarItemsRV();
    }

    @Override
    public void mostrarItemsRV() {
        iRecyclerViewMascotaFavView.inicilizarAdaptadorRV(iRecyclerViewMascotaFavView.crearAdaptador((mascotas)));
        iRecyclerViewMascotaFavView.generarLayout();
    }
}
