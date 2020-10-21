package com.ramasolutions.petagramfragments.db;

import android.content.ContentValues;
import android.content.Context;

import com.ramasolutions.petagramfragments.R;
import com.ramasolutions.petagramfragments.pojo.Mascota;

import java.util.ArrayList;

public class ConstructorMascotas {
    private static final int LIKE = 1;
    private Context context;

    public ConstructorMascotas (Context context){
        this.context=context;
    }

    public ArrayList<Mascota> obtenerDatosIniciales(){
        ArrayList<Mascota> mascotas = new ArrayList<Mascota>();
        mascotas.add(creaMascota(R.drawable.perro_uno,"Nube",'H',551));
        mascotas.add(creaMascota(R.drawable.perro_dos,"Fido",'M',552));
        mascotas.add(creaMascota(R.drawable.perro_tres,"Patan",'H',553));
        mascotas.add(creaMascota(R.drawable.perro_cuatro,"Leo",'M',554));
        mascotas.add(creaMascota(R.drawable.perro_cinco,"Tom",'M',555));
        mascotas.add(creaMascota(R.drawable.perro_seis,"Linda",'H',556));
        mascotas.add(creaMascota(R.drawable.perro_siete,"Superman",'M',557));
        mascotas.add(creaMascota(R.drawable.perro_ocho,"Pulgas",'M',558));
        mascotas.add(creaMascota(R.drawable.perro_nueve,"Estrella",'H',559));

        return mascotas;
    }

    public Mascota creaMascota(int foto, String nombre, char genero, int id){
        BaseDatos db = new BaseDatos(context);
        Mascota mascotabd = db.obtenerMascotaPorNombre(nombre);
        if(mascotabd.getId()==0)
            return new Mascota(foto,nombre,genero,id);
        else
            return mascotabd;
    }

    public ArrayList<Mascota> obtenerMascotasFavoritasDummy(){
        ArrayList<Mascota> mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota(R.drawable.perro_tres,"Patan",'H',4,1));
        mascotas.add(new Mascota(R.drawable.perro_cuatro,"Leo",'M',3,2));
        mascotas.add(new Mascota(R.drawable.perro_cinco,"Tom",'M',5,3));
        mascotas.add(new Mascota(R.drawable.perro_seis,"Linda",'H',4,4));
        mascotas.add(new Mascota(R.drawable.perro_siete,"Superman",'M',2,5));

        return mascotas;
    }

    public ArrayList<Mascota> obtenerMascotasFavoritas(){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerTodasLasMascotas();
    }

    public long insertarMascota(String nombre, String genero, int foto, int rate){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE,nombre);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_GENERO,genero);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO,foto);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_RATE,rate);

        long id = db.insertarMascota(contentValues);
        return id;
    }

    public long insertMascota(String nombre, String genero, int foto, int rate){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE,nombre);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_GENERO,genero);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO,foto);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_RATE,rate);
        return db.insertMascota(contentValues);
    }

    public void darLikeMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        int likes = obtenerLikesMascota(mascota) + LIKE;
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_RATE,likes);
        String whereClause=ConstantesBaseDatos.TABLE_MASCOTA_ID+" = "+mascota.getId();
        db.insertarLikeMascota(contentValues,whereClause);
    }

    public int obtenerLikesMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesMascota(mascota.getId());
    }
}
