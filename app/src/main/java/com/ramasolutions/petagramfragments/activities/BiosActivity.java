package com.ramasolutions.petagramfragments.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.ramasolutions.petagramfragments.R;
import com.ramasolutions.petagramfragments.pojo.InformacionDesarrollador;

public class BiosActivity extends AppCompatActivity {

    TextView tvNombreBio;
    TextView tvLugarNacimientoBio;
    TextView tvPrograma;
    TextView tvFechaDesarrolloBio;
    TextView tvVersionBio;
    TextView tvSobreMi;
    ImageView imgFotoBio;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bios_desarrollo);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Habilita el botón Subir
        ab.setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setIcon(R.drawable.ic_action_huella);

        tvNombreBio=findViewById(R.id.tvNombreBio);
        tvLugarNacimientoBio=findViewById(R.id.tvLugarNacimientoBio);
        tvPrograma=findViewById(R.id.tvPrograma);
        tvFechaDesarrolloBio=findViewById(R.id.tvFechaDesarrolloBio);
        tvVersionBio=findViewById(R.id.tvVersionBio);
        tvSobreMi=findViewById(R.id.tvSobreMi);
        imgFotoBio= findViewById(R.id.imgFotoBio);

        InformacionDesarrollador bioDesarrollador=getInformacionBIO();
        tvNombreBio.setText(bioDesarrollador.getNombre());
        tvLugarNacimientoBio.setText(bioDesarrollador.getLugarNacimiento());
        tvPrograma.setText(bioDesarrollador.getNombrePrograma());
        tvFechaDesarrolloBio.setText(bioDesarrollador.getFechaDesarrollo());
        tvVersionBio.setText(bioDesarrollador.getVersion());
        tvSobreMi.setText(bioDesarrollador.getSobreMi());
        imgFotoBio.setImageResource(bioDesarrollador.getFoto());

    }

    public  InformacionDesarrollador getInformacionBIO(){
        InformacionDesarrollador bioDesarrollador=new InformacionDesarrollador();
        bioDesarrollador.setNombre(getResources().getString(R.string.bio_mi_nombre));
        bioDesarrollador.setNombrePrograma(getResources().getString(R.string.app_name));
        bioDesarrollador.setVersion(getResources().getString(R.string.app_version));
        bioDesarrollador.setFechaDesarrollo(getResources().getString(R.string.app_fecha_desarrollo));
        bioDesarrollador.setFoto(R.drawable.androides);
        bioDesarrollador.setLugarNacimiento(getResources().getString(R.string.bio_mi_lugar_nac));
        bioDesarrollador.setSobreMi(getResources().getString(R.string.bio_mi_info));

        return bioDesarrollador;

    }
}