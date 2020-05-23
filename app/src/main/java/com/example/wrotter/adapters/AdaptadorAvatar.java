package com.example.wrotter.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wrotter.R;
import com.example.wrotter.clases.Utilidades;
import com.example.wrotter.clases.vo.AvatarVo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorAvatar extends RecyclerView.Adapter<AdaptadorAvatar.ViewHolderAvatar> {
    List<AvatarVo> listaAvatars;
    View vista;
    int posicion = 0;

    public AdaptadorAvatar(List<AvatarVo> listaAvatars) {
        this.listaAvatars = listaAvatars;
    }

    @NonNull
    @Override
    public ViewHolderAvatar onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        vista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_grid_avatar,null);


        return new ViewHolderAvatar(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAvatar viewHolderAvatar, int i) {
        viewHolderAvatar.imgAvatar.setImageResource(listaAvatars.get(i).getAvatarId());

        final int pos =i;

        viewHolderAvatar.cardAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posicion=pos;
                Utilidades.avatarSeleccion=listaAvatars.get(pos);
                Utilidades.avatarIdSeleccion=pos+1;
                notifyDataSetChanged();
            }
        });

        if (posicion == i){
            viewHolderAvatar.barraSeleccion.setBackgroundColor(vista.getResources().getColor(R.color.colorBtont));
        }else{
            viewHolderAvatar.barraSeleccion.setBackgroundColor(vista.getResources().getColor(R.color.colorSplash));
        }
    }

    @Override
    public int getItemCount() {
        return listaAvatars.size();
    }

    public class ViewHolderAvatar extends RecyclerView.ViewHolder {

        CardView cardAvatar;
        ImageView imgAvatar;
        TextView barraSeleccion;
        public ViewHolderAvatar(@NonNull View itemView) {
            super(itemView);
            cardAvatar=itemView.findViewById(R.id.cardAvatar);
            imgAvatar=itemView.findViewById(R.id.idAvatar);
            barraSeleccion=itemView.findViewById(R.id.barraSeleccionId);
        }
    }
}
