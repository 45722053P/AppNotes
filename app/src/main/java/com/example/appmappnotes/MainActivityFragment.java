package com.example.appmappnotes;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;

public class MainActivityFragment extends Fragment implements LocationListener{

    Firebase ref;
    ListView listaUsers;
    TextView titulo,descripcion,longitude,latitude;
    EditText ettitulo,etdescripcion;
    FirebaseListAdapter adapter;
    Location localizacion = null;

    public MainActivityFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragment =  inflater.inflate(R.layout.fragment_main, container, false);

        ettitulo = (EditText)fragment.findViewById(R.id.etTitulo);
        etdescripcion = (EditText)fragment.findViewById(R.id.etTodo);


        Firebase.setAndroidContext(getContext());

        ref = new Firebase("https://mapmultimedia.firebaseio.com/");

        ref.child("Mensaje").setValue("Lo hemos conseguido!!");


        FloatingActionButton fab = (FloatingActionButton) fragment.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                Firebase nota = ref.child("Notas").push();

                Nota notas = new Nota();

                notas.setTitulo(titulo.getText().toString());
                notas.setDescripcion(descripcion.getText().toString());

                notas.setLatitude(localizacion.getLatitude());
                notas.setLongitude(localizacion.getLongitude());

                nota.setValue(notas);


            }
        });


       listaUsers = (ListView)fragment.findViewById(R.id.lvTodos);

        listaUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getContext(),DetallesActivity.class);
                Firebase ref  = adapter.getRef(position);
                intent.putExtra("notaref",ref.toString());
                startActivity(intent);
            }
        });

        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        // Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) getActivity());

        adapter = new FirebaseListAdapter<Nota>(
                getActivity(),
                Nota.class,
                R.layout.user_row,
                ref.child("Notes")){

            @Override
            protected void populateView(View v, Nota model, int position) {
                super.populateView(v, model, position);

                titulo = (TextView)v.findViewById(R.id.titulo);
               /*descripcion = (TextView)v.findViewById(R.id.descripcion);

                latitude = (TextView)v.findViewById(R.id.latitude);
                longitude = (TextView)v.findViewById(R.id.longitude);*/

                latitude.setText(String.valueOf(model.getLatitude()));
                longitude.setText(String.valueOf(model.getLongitude()));

            }
        };

        listaUsers.setAdapter(adapter);




    return fragment;
    }

    @Override
    public void onLocationChanged(Location location) {

        localizacion = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
