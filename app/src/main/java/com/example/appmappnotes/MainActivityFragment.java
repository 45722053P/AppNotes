package com.example.appmappnotes;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;

public class MainActivityFragment extends Fragment {

    ListView listaUsers;
    EditText introducido,localizacion;
    FirebaseListAdapter adapter;

    public MainActivityFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragment =  inflater.inflate(R.layout.fragment_main, container, false);




        Firebase.setAndroidContext(getContext());

        Firebase ref = new Firebase("https://mapmultimedia.firebaseio.com/");

        ref.child("Mensaje").setValue("Lo hemos conseguido!!");


        Firebase Notas = ref.child("Notas");

        Firebase refnota = Notas.push();
        Nota nota = new Nota();
        refnota.setValue(nota);



       listaUsers = (ListView)fragment.findViewById(R.id.lvTodos);

        adapter = new FirebaseListAdapter<Nota>(
                getActivity(),
                Nota.class,
                R.layout.user_row,
                Notas){

            @Override
            protected void populateView(View v, Nota model, int position) {
                super.populateView(v, model, position);

                introducido = (EditText)v.findViewById(R.id.intriducido);
                localizacion = (EditText)v.findViewById(R.id.localizacion);




            }
        };


        listaUsers.setAdapter(adapter);

    return fragment;
    }
}
