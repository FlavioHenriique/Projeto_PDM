package pdm.ifpb.com.projeto_pdm;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import pdm.ifpb.com.projeto_pdm.adapter.MyAdapter;
import pdm.ifpb.com.projeto_pdm.controller.SolicitacaoController;
import pdm.ifpb.com.projeto_pdm.model.Trabalho;


public class MinhasSolicitacoes extends Fragment {


    public MinhasSolicitacoes() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_minhas_solicitacoes,
                container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String email = ((menu)getActivity()).getAtual().getEmail();

        SolicitacaoController controller = new SolicitacaoController(getContext());
        List<Trabalho> trabalhos = controller.minhasSolicitacoes(email);

        if(!trabalhos.isEmpty()) {
            TextView textView = getActivity().findViewById(R.id.nenhumaSolicitacao);
            textView.setVisibility(View.GONE);

            ListView list = view.findViewById(R.id.lista_minhas_solicitacoes);
            MyAdapter adapter = new MyAdapter(getContext(), trabalhos, "busca",
                    email);
            adapter.setManager(getFragmentManager());
            list.setAdapter(adapter);
        }

    }
}
