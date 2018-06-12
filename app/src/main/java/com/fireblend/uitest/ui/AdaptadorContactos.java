package com.fireblend.uitest.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.fireblend.uitest.R;
import com.fireblend.uitest.entities.Contact;

import java.util.ArrayList;
import java.util.List;


/*
El ViewHolder es importante en RecyclerView porque nos ayuda tomar todos los elementos que más adelante serán personalizados,
ósea un contenerdor vacío que nos servirá para no tener que estar creando nuevas vistars cada vez que cambia un elemento, sino que
reutilizará una vista. Con esto se consigue mejorar el procesamiento de las listas, con más fluidez y mejor experiencia para el usuario.


 */
public class AdaptadorContactos extends RecyclerView.Adapter<AdaptadorContactos.ContactosViewHolder> implements View.OnClickListener{
    private ArrayList<Contact> datos;
    private View.OnClickListener listener;

    public AdaptadorContactos(ArrayList<Contact> datos) {
        this.datos = datos;
    }

    @Override
    public AdaptadorContactos.ContactosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_item, parent, false);
        itemView.setOnClickListener(this);

        ContactosViewHolder tvh = new ContactosViewHolder(itemView);

        return tvh;

    }
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }


    public class ContactosViewHolder extends RecyclerView.ViewHolder {
        TextView name, age, phone, email, provincia;

        public ContactosViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            age = (TextView) itemView.findViewById(R.id.age);
            phone = (TextView) itemView.findViewById(R.id.phone);
            email = (TextView) itemView.findViewById(R.id.email);
            provincia = (TextView) itemView.findViewById(R.id.provincia);
        }
        public void bindContact(Contact c) {
            name.setText(c.getName());
            age.setText( String.valueOf(c.getAge()));
            phone.setText(c.getPhone());
            email.setText(c.getEmail());
            provincia.setText(c.getProvincia());
        }
    }

    @Override
    public void onBindViewHolder(AdaptadorContactos.ContactosViewHolder holder, int position) {
        Contact item = datos.get(position);

        holder.bindContact(item);

    }

    @Override
    public int getItemCount() {
        return datos.size();
    }
}
