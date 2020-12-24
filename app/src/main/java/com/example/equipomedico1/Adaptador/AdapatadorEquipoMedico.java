package com.example.equipomedico1.Adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.equipomedico1.Modelo.EquipoMedico;
import com.example.equipomedico1.R;

import java.util.List;

public class AdapatadorEquipoMedico extends RecyclerView.Adapter<AdapatadorEquipoMedico.MyViewHolder> {

    List<EquipoMedico> lista_equipo_medico;

    public AdapatadorEquipoMedico(List<EquipoMedico> lista_equipo_medico) {
        this.lista_equipo_medico = lista_equipo_medico;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.equipo_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.serie.setText(lista_equipo_medico.get(position).getSerie());
        holder.nombre.setText(lista_equipo_medico.get(position).getNombre());
        holder.descripcion.setText(lista_equipo_medico.get(position).getDescripcion());
    }

    @Override
    public int getItemCount() {
        return lista_equipo_medico.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        CardView root_view;
        TextView serie, nombre, descripcion;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            root_view = (CardView)itemView.findViewById(R.id.root_view);

            serie = (TextView)itemView.findViewById(R.id.txt_serie);
            nombre = (TextView)itemView.findViewById(R.id.txt_nombre);
            descripcion = (TextView)itemView.findViewById(R.id.txt_descripcion);
        }
    }
}
