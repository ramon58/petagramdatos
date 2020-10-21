package com.ramasolutions.petagramfragments.db;

import android.content.Context;

import com.ramasolutions.petagramfragments.R;
import com.ramasolutions.petagramfragments.pojo.Foto;

import java.util.ArrayList;

public class ConstructorFotos {

    private Context context;

    public ConstructorFotos (Context context){
        this.context=context;
    }

    public ArrayList<Foto> obtenerDatosIniciales(){
        ArrayList<Foto> fotos = new ArrayList<Foto>();

        fotos.add(new Foto(R.drawable.perro_uno,3));
        fotos.add(new Foto(R.drawable.perro_uno,5));
        fotos.add(new Foto(R.drawable.perro_uno,8));
        fotos.add(new Foto(R.drawable.perro_uno,2));
        fotos.add(new Foto(R.drawable.perro_uno,1));
        fotos.add(new Foto(R.drawable.perro_uno,9));
        fotos.add(new Foto(R.drawable.perro_uno,4));
        fotos.add(new Foto(R.drawable.perro_uno,9));
        fotos.add(new Foto(R.drawable.perro_uno,6));

        return fotos;
    }
}
