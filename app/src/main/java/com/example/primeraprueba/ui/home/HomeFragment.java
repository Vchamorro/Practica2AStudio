package com.example.primeraprueba.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Button;
import java.util.List;
import java.util.ArrayList;

import androidx.fragment.app.Fragment;

import com.example.primeraprueba.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private EditText txtConsole;
    private EditText txtDescription;
    private ListView listView;

    private List<String> consoleList = new ArrayList<String>();
    private List<String> descriptionList = new ArrayList<>();
    private FragmentHomeBinding binding;

    private ArrayAdapter<String> adapter;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FragmentHomeBinding binding = FragmentHomeBinding.inflate(inflater, container, false);

        txtConsole = binding.textConsole;
        txtDescription = binding.textDescription;
        listView = binding.listView;

        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, consoleList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String console = consoleList.get(position);
                String description = descriptionList.get(position);
                Toast.makeText(requireContext(),console+": "+description, Toast.LENGTH_SHORT).show();
            }
        });

        Button addButton = binding.btnAddElement;
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarElemento(v);
            }
        });

        return binding.getRoot();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void agregarElemento(View view) {
        String texto1 = txtConsole.getText().toString();
        String texto2 = txtDescription.getText().toString();

        if (!texto1.isEmpty() && !texto2.isEmpty()) {
            consoleList.add(texto1);
            descriptionList.add(texto2);
            adapter.notifyDataSetChanged();
            txtConsole.setText("");
            txtDescription.setText("");
            Toast.makeText(requireContext(), "Consola agregada.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(requireContext(), "Todos los campos son obligatorios.", Toast.LENGTH_SHORT).show();
        }
    }
}