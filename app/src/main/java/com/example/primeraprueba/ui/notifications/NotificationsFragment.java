package com.example.primeraprueba.ui.notifications;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.primeraprueba.databinding.FragmentNotificationsBinding;


public class NotificationsFragment extends Fragment {
    private EditText nameText;
    private EditText rutText;
    private EditText ageText;
    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);

        nameText = binding.nameTxt;
        rutText = binding.rutTxt;
        ageText = binding.ageTxt;

        Context context = requireContext();

        // Utilizar el contexto para obtener las preferencias compartidas
        SharedPreferences sharedPreferences = context.getSharedPreferences("preferencia", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        String dato1 = sharedPreferences.getString("datoNombre", "");
        String dato2 = sharedPreferences.getString("datoRUT", "");
        String dato3 = sharedPreferences.getString("datoEdad", "");
        nameText.setText(dato1);
        rutText.setText(dato2);
        ageText.setText(dato3);

        Button saveButton = binding.btnSaveData;
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarDatos(v);
            }
        });

        return binding.getRoot();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public void guardarDatos(View view) {
        Context context = requireContext();

        // Utilizar el contexto para obtener las preferencias compartidas
        SharedPreferences sharedPreferences = context.getSharedPreferences("preferencia", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        String texto1 = nameText.getText().toString();
        String texto2 = rutText.getText().toString();
        String texto3 = ageText.getText().toString();

        editor.putString("datoNombre", texto1);
        editor.putString("datoRUT", texto2);
        editor.putString("datoEdad", texto3);

        editor.apply();

        if (!texto1.isEmpty() && !texto2.isEmpty() && !texto3.isEmpty()) {

            nameText.setText("");
            rutText.setText("");
            ageText.setText("");
            Toast.makeText(requireContext(), "Consola agregada.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(requireContext(), "Todos los campos son obligatorios.", Toast.LENGTH_SHORT).show();
        }
    }
}