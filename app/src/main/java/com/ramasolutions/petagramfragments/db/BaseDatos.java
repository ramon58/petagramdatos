package com.ramasolutions.petagramfragments.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.ramasolutions.petagramfragments.pojo.Mascota;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {

    private Context context;
    public BaseDatos(@Nullable Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascota = "CREATE TABLE "+ConstantesBaseDatos.TABLE_MASCOTA + "(" +
                ConstantesBaseDatos.TABLE_MASCOTA_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE+" VARCHAR(200) NOT NULL, "+
                ConstantesBaseDatos.TABLE_MASCOTA_GENERO+" VARCHAR(1) NOT NULL, "+
                ConstantesBaseDatos.TABLE_MASCOTA_FOTO+" INTEGER, "+
                ConstantesBaseDatos.TABLE_MASCOTA_RATE+" INTEGER "+
                ")";

        db.execSQL(queryCrearTablaMascota);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ ConstantesBaseDatos.TABLE_MASCOTA);
        onCreate(db);
    }

    public ArrayList<Mascota> obtenerTodasLasMascotas(){
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query,null);

        while (registros.moveToNext()){
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setGenero(registros.getString(2).charAt(0));
            mascotaActual.setFoto(registros.getInt(3));
            mascotaActual.setRate(registros.getInt(4));

            mascotas.add(mascotaActual);
        }
        db.close();
        return mascotas;
    }

    public Mascota obtenerMascotaPorNombre(String nombre){
        Mascota mascotaActual = new Mascota();
        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTA +
                " WHERE " +ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE + " = '" + nombre +"'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query,null);

        if (registros.moveToNext()) {
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setGenero(registros.getString(2).charAt(0));
            mascotaActual.setFoto(registros.getInt(3));
            mascotaActual.setRate(registros.getInt(4));
        }
        db.close();
        return mascotaActual;
    }

    public long insertarMascota(ContentValues contentValues){
        long id = -1;

        //int n = obtenerNumeroRegistros();
        //Toast.makeText(context, "la BD tiene = "+n+" registros", Toast.LENGTH_SHORT).show();

        SQLiteDatabase db = this.getWritableDatabase();
        String nombre = (String) contentValues.get(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE);
        String query_existeMascota = "SELECT "+ConstantesBaseDatos.TABLE_MASCOTA_ID+" FROM " +
                ConstantesBaseDatos.TABLE_MASCOTA + " WHERE "
                + ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE + " = '" + nombre +"'";

        Log.i("QUERY existeMascota: ",query_existeMascota);


        Cursor registros = db.rawQuery(query_existeMascota ,null);

        if (registros.moveToNext()) {
            int id_mascota = registros.getInt(0);
            Log.i("Existe ID "+id_mascota+" : ","actualizamos RATE");
            Toast.makeText(context, "Ya existe el registro con id = "+id_mascota, Toast.LENGTH_SHORT).show();

            int likes = obtenerLikesMascota(id_mascota) + 1;
            ContentValues values = new ContentValues();
            values.put(ConstantesBaseDatos.TABLE_MASCOTA_RATE,likes);
            String whereClause=ConstantesBaseDatos.TABLE_MASCOTA_ID+" = "+id_mascota;
            insertarLikeMascota(values,whereClause);

        }else{

            int n = obtenerNumeroRegistros();
            if(n < 5){
                Toast.makeText(context, "Hay menos de 5 registros en la BD", Toast.LENGTH_SHORT).show();
                Log.i("MENOS DE 5 : ","insertamos la mascota");

                id = insertMascota(contentValues);
            }else{

                db = this.getWritableDatabase();
                Toast.makeText(context, "Más de 5 registros: Se elimina el primer registro antiguo", Toast.LENGTH_SHORT).show();
                Log.i("MAS DE 5 : ","Eliminada la primera se inserta la mascota nueva");
                String query_primer_id = "SELECT "+ConstantesBaseDatos.TABLE_MASCOTA_ID+" FROM " +
                        ConstantesBaseDatos.TABLE_MASCOTA + " ORDER BY  "
                        + ConstantesBaseDatos.TABLE_MASCOTA_ID + " LIMIT 1";
                registros = db.rawQuery(query_primer_id ,null);
                if (registros.moveToNext()) {
                    int id_mascota = registros.getInt(0);
                    String whereClause=ConstantesBaseDatos.TABLE_MASCOTA_ID+" = "+id_mascota;
                    db.delete(ConstantesBaseDatos.TABLE_MASCOTA,whereClause,null);
                }
                id = insertMascota(contentValues);
            }
        }
        db.close();
        return id;
    }

    public long insertMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        long id = -1;
        id = db.insert(ConstantesBaseDatos.TABLE_MASCOTA,null,contentValues);
        Log.i("Insertamos ","id mascota = "+id);
        String nombre = (String) contentValues.get(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE);
        Toast.makeText(context, "Insertamos a "+nombre+" con id = "+id, Toast.LENGTH_SHORT).show();
        db.close();
        return id;
    }

    public void insertarLikeMascota(ContentValues contentValues, String whereClause){
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(ConstantesBaseDatos.TABLE_MASCOTA,contentValues,whereClause,null);
        db.close();
    }

    public int obtenerLikesMascota(int id){
        int likes = 0;
        String query = "SELECT "+ConstantesBaseDatos.TABLE_MASCOTA_RATE+" FROM " +
                ConstantesBaseDatos.TABLE_MASCOTA + " WHERE "
                + ConstantesBaseDatos.TABLE_MASCOTA_ID + "=" + id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query,null);

        if (registros.moveToNext()){
            likes = registros.getInt(0);
        }
        db.close();
        return likes;
    }

    public int obtenerNumeroRegistros(){
        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query,null);
        int n = registros.getCount();
        db.close();
        return n;
    }
}
