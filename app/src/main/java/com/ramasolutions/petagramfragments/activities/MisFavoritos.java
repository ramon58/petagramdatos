package com.ramasolutions.petagramfragments.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ramasolutions.petagramfragments.R;
import com.ramasolutions.petagramfragments.adapter.MascotaFavoritaAdapter;
import com.ramasolutions.petagramfragments.pojo.Mascota;
import com.ramasolutions.petagramfragments.presentador.IRecyclerViewFragmentPresenter;
import com.ramasolutions.petagramfragments.presentador.RecyclerViewMisFavoritasPresenter;
import com.ramasolutions.petagramfragments.fragments.IRecyclerViewMascotaFavView;

import java.util.ArrayList;

public class MisFavoritos extends AppCompatActivity implements IRecyclerViewMascotaFavView {

    private ArrayList<Mascota> mascotas;
    private RecyclerView rvMascotas;
    private IRecyclerViewFragmentPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(miActionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Get a support ActionBar corresponding to this toolbar

        getSupportActionBar().setIcon(R.drawable.ic_action_huella);

        rvMascotas = (RecyclerView) findViewById(R.id.rvFavoritos);
        presenter = new RecyclerViewMisFavoritasPresenter(this,this);

    }

    @Override
    public void generarLayout() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMascotas.setLayoutManager(llm);
    }

    @Override
    public MascotaFavoritaAdapter crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaFavoritaAdapter adaptador = new MascotaFavoritaAdapter(mascotas,this);
        return adaptador;
    }

    @Override
    public void inicilizarAdaptadorRV(MascotaFavoritaAdapter adaptador) {
        rvMascotas.setAdapter(adaptador);
    }
}