package pdm.ifpb.com.projeto_pdm;


import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pdm.ifpb.com.projeto_pdm.adapter.MyAdapter;
import pdm.ifpb.com.projeto_pdm.controller.SolicitacaoController;
import pdm.ifpb.com.projeto_pdm.controller.TrabalhoController;
import pdm.ifpb.com.projeto_pdm.model.Notificacao;
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

        String cidade = ((menu) getActivity()).getAtual().getCidade();
        email = ((menu) getActivity()).getAtual().getEmail();

        TrabalhoController controller = new TrabalhoController(getContext());
        List<Trabalho> lista = controller.buscarTrabalhos("cidade", cidade, email);

        TextView textView = getActivity().findViewById(R.id.nenhum_trabalho);

        if (!lista.isEmpty()){

            textView.setVisibility(View.GONE);

            ListView listView = getView().findViewById(R.id.trabalhosCidade);
            MyAdapter adapter = new MyAdapter(getContext(), lista, "busca", email);
            adapter.setManager(getFragmentManager());
            listView.setAdapter(adapter);
        }

        SolicitacaoController solicitacaoController = new SolicitacaoController(getContext());
        List<Notificacao> notificacoes = solicitacaoController.minhasNotificacoes(email);

        for (int k = 0; k < notificacoes.size(); k ++){
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(getContext(),"default")
                            .setSmallIcon(R.drawable.ic_add)
                            .setContentTitle("Projeto PDM")
                            .setContentText(notificacoes.get(k).getMensagem());

            NotificationManager manager = (NotificationManager)
                    getContext().getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(k +1, mBuilder.build());
        }
    }
}
