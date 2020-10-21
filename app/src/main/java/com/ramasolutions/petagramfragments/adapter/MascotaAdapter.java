package com.ramasolutions.petagramfragments.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ramasolutions.petagramfragments.R;
import com.ramasolutions.petagramfragments.db.ConstructorMascotas;
import com.ramasolutions.petagramfragments.pojo.Mascota;

import java.util.ArrayList;

public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder> {

    ArrayList<Mascota> mascotas;
    Activity activity;

    public MascotaAdapter(ArrayList<Mascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota,parent,false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MascotaViewHolder holder, int position) {
        final Mascota mascota = mascotas.get(position);
        holder.imgFoto.setImageResource(mascota.getFoto());
        holder.tvNombre.setText(mascota.getNombre());
        holder.tvRate.setText(String.valueOf(mascota.getRate()));

        if(Character.toUpperCase(mascota.getGenero()) == 'H' )
        {
            holder.rl_imgprofile.setBackgroundColor(this.activity.getResources().getColor(R.color.colorHembra));
        }
        else {
            holder.rl_imgprofile.setBackgroundColor(this.activity.getResources().getColor(R.color.colorMacho));
        }

        holder.btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mascota.addRate();
                holder.tvRate.setText(String.valueOf(mascota.getRate()));

                ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
                long id = constructorMascotas.insertarMascota(mascota.getNombre(), String.valueOf(mascota.getGenero()),mascota.getFoto(),mascota.getRate());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFoto;
        private TextView tvNombre;
        private TextView tvRate;
        private ImageButton btnRate;
        private ImageButton btnRating;
        private RelativeLayout rl_imgprofile;

        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoto   = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvNombre  = (TextView) itemView.findViewById(R.id.tvNombre);
            tvRate    = (TextView) itemView.findViewById(R.id.tvRate);
            btnRate   = (ImageButton) itemView.findViewById(R.id.btnRate);
            btnRating = (ImageButton) itemView.findViewById(R.id.btnRating);
            rl_imgprofile = (RelativeLayout) itemView.findViewById(R.id.rl_imgprofile);
        }
    }
}
