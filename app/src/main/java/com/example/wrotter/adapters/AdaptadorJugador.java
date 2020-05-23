package com.example.wrotter.adapters;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wrotter.R;
import com.example.wrotter.clases.Utilidades;
import com.example.wrotter.clases.vo.AvatarVo;
import com.example.wrotter.clases.vo.JugadorVo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorJugador extends RecyclerView.Adapter<AdaptadorJugador.ViewHolderJugador> {
    List<JugadorVo> listaJugadores;
    View vista;
    int posi;

    public AdaptadorJugador(List<JugadorVo> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    @NonNull
    @Override
    public ViewHolderJugador onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        vista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_ranking,null);


        return new ViewHolderJugador(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderJugador viewHolderJugador, int i) {
        viewHolderJugador.imgAvatar.setImageResource(Utilidades.listaAvatars.get(listaJugadores.get(i).getAvatar()-1).getAvatarId());

        viewHolderJugador.tvName.setText(listaJugadores.get(i).getNombre());

        viewHolderJugador.tvPuntaje.setText(Integer.toString(listaJugadores.get(i).getPuntaje()));
        posi=i+1;
        viewHolderJugador.tvPosicion.setText(Integer.toString(posi)+".");

        if (i==0){
            viewHolderJugador.tvPosicion.setTextColor(vista.getResources().getColor(R.color.colorOro));
            viewHolderJugador.tvPosicion.setTypeface(Typeface.DEFAULT_BOLD);
        }else if (i == 1){
            viewHolderJugador.tvPosicion.setTextColor(vista.getResources().getColor(R.color.colorPlata));
            viewHolderJugador.tvPosicion.setTypeface(Typeface.DEFAULT_BOLD);
        }else if(i == 2){
            viewHolderJugador.tvPosicion.setTextColor(vista.getResources().getColor(R.color.colorBronze));
            viewHolderJugador.tvPosicion.setTypeface(Typeface.DEFAULT_BOLD);

        }
    }

    @Override
    public int getItemCount() {
        return listaJugadores.size();
    }

    public class ViewHolderJugador extends RecyclerView.ViewHolder {


        ImageView imgAvatar;
        TextView tvName,tvPuntaje,tvPosicion;
        public ViewHolderJugador(@NonNull View itemView) {
            super(itemView);
            imgAvatar=itemView.findViewById(R.id.idAvatar);
            tvName = itemView.findViewById(R.id.tvNickname);
            tvPuntaje= itemView.findViewById(R.id.tvPuntaje);
            tvPosicion= itemView.findViewById(R.id.tvPosicion);
        }
    }
}
