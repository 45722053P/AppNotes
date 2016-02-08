package com.example.appmappnotes;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetallesActivityFragment extends Fragment {


    TextView descripcion,longitude,latitude;


    public DetallesActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View detallesFragment = inflater.inflate(R.layout.fragment_detalles, container, false);




        String refNotaString = getActivity().getIntent().getStringExtra("notaref");

        Firebase refnota = new Firebase(refNotaString);

        refnota.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Nota nota = dataSnapshot.getValue(Nota.class);

                descripcion.setText(nota.getDescripcion());
                latitude.setText(String.valueOf(nota.getLatitude()));
                longitude.setText(String.valueOf(nota.getLongitude()));

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        return detallesFragment;
    }
}
