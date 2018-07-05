package pdm.ifpb.com.projeto_pdm;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import pdm.ifpb.com.projeto_pdm.controller.SolicitacaoController;
import pdm.ifpb.com.projeto_pdm.model.Trabalho;
import pdm.ifpb.com.projeto_pdm.model.Usuario;


public class TelaTrabalho extends Fragment {

    private Gson gson = new Gson();
    private Trabalho atual;

    public TelaTrabalho() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_tela_trabalho,
                container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle argumento = getArguments();

        verificarAnterior(argumento.getString("tela"));

        atual = gson.fromJson(argumento.getString("atual"),
                Trabalho.class);

        TextView nome = getActivity().findViewById(R.id.nomeTrab);
        TextView descricao = getActivity().findViewById(R.id.descricaoTrab);
        TextView localizacao = getActivity().findViewById(R.id.localizacaoTrab);
        TextView horario = getActivity().findViewById(R.id.horariodata);
        TextView valor = getActivity().findViewById(R.id.valortrab);

        nome.setText(atual.getTitulo().toString());
        descricao.setText(atual.getDescricao().toString());
        localizacao.setText("Localização: "+atual.getCidade().toString() +
                " - " + atual.getEstado().toString());
        horario.setText(atual.getHorario().toString() + "h - " + atual.getData());
        valor.setText(atual.getValor()+  " R$");

        Button btSolicitar = getActivity().findViewById(R.id.btSolicitar);
        btSolicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONObject json = new JSONObject();
                try {
                    json.put("trabalho",atual.getCodigo());
                    json.put("email",((menu)getActivity())
                            .getAtual().getEmail());

                    SolicitacaoController controller = new
                            SolicitacaoController(getContext());
                    controller.solicitarTrabalho(json.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void verificarAnterior(String tela){

        Button bt = getActivity().findViewById(R.id.btSolicitar);

        switch(tela){
            case "meusTrabalhos":{

                bt.setVisibility(View.GONE);
                break;
            }
            case "busca":{
                break;
            }
        }
    }
}
