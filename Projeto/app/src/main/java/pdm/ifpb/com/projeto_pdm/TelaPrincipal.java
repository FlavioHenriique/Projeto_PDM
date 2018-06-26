package pdm.ifpb.com.projeto_pdm;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import pdm.ifpb.com.projeto_pdm.adapter.MyAdapter;
import pdm.ifpb.com.projeto_pdm.controller.TrabalhoController;
import pdm.ifpb.com.projeto_pdm.model.Trabalho;

public class TelaPrincipal extends Fragment {

    private ArrayList<String> palavras;
    private String email;

    public TelaPrincipal() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_tela_principal,
                container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String cidade = ((menu)getActivity()).getAtual().getCidade();
        email = ((menu)getActivity()).getAtual().getEmail();

        TrabalhoController controller = new TrabalhoController(getContext());
        List<Trabalho> lista = controller.buscarTrabalhos("cidade",cidade,email);

        ListView listView = getView().findViewById(R.id.trabalhosCidade);
        MyAdapter adapter = new MyAdapter(getContext(),lista);
        listView.setAdapter(adapter);
    }

}
