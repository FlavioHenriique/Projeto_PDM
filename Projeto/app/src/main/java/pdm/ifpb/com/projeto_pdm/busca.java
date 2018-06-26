package pdm.ifpb.com.projeto_pdm;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import pdm.ifpb.com.projeto_pdm.adapter.MyAdapter;
import pdm.ifpb.com.projeto_pdm.controller.TrabalhoController;
import pdm.ifpb.com.projeto_pdm.model.Trabalho;


public class busca extends Fragment {


    public busca() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_busca, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button bt = getActivity().findViewById(R.id.btBuscaCidade);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText busca = getActivity().findViewById(R.id.cidadeBuscada);
                ListView listView = getActivity().findViewById(R.id.busca_cidade);

                TrabalhoController controller = new TrabalhoController(getContext());
                List<Trabalho> lista= controller.buscarTrabalhos("cidade",
                        busca.getText().toString(),
                        ((menu)getActivity()).getAtual().getEmail());

                MyAdapter adapter = new MyAdapter(getContext(),lista);
                adapter.setManager(getFragmentManager());
                listView.setAdapter(adapter);
            }
        });
    }
}
