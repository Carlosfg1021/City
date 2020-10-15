package com.example.city.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.city.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EstudioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EstudioFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    View view; //esto servira para hacer acciones.

    Spinner sp_tiempo;
    Button btn_iniciar;
    TextView lbl_mostrar_tiempo;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EstudioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EstudioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EstudioFragment newInstance(String param1, String param2) {
        EstudioFragment fragment = new EstudioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_estudio, container, false);

        //dando los tiempos para el spinner
        sp_tiempo = view.findViewById(R.id.seleccionarTiempo);
        sp_tiempo = (Spinner) view.findViewById(R.id.seleccionarTiempo);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.tiempos_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sp_tiempo.setAdapter(adapter);


        //dando accion al boton
        btn_iniciar = (Button) view.findViewById(R.id.btnIniciar);
        lbl_mostrar_tiempo = (TextView) view.findViewById(R.id.mostrarTiempo);
        btn_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iniciarCuenta();

            }
        });






        return view;


    }

    private void iniciarCuenta() {
        int min= 1 *60*1000;
        int seg = 0*1000;

        long valor = min + seg;

        CountDownTimer cuenta = new CountDownTimer(valor,1000) {
            @Override
            public void onTick(long l) {

                long tiempo = l / 1000;
                int minutos = (int) (tiempo / 60);
                long segundo = tiempo % 60;

                String minutos_mostrar = String.format("%02d", minutos);
                String segundos_mostrar = String.format("%02d", segundo);

                lbl_mostrar_tiempo.setText(""+minutos_mostrar+":"+segundos_mostrar);
            }

            @Override
            public void onFinish() {
                Toast.makeText(getActivity(), "Finalizo", Toast.LENGTH_SHORT).show();
                lbl_mostrar_tiempo.setText("Se ha terminado");
            }
        }.start();


    }

}