package pdm.ifpb.com.projeto_pdm;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.List;

import pdm.ifpb.com.projeto_pdm.controller.SolicitacaoController;
import pdm.ifpb.com.projeto_pdm.model.Trabalho;
import pdm.ifpb.com.projeto_pdm.model.Usuario;


public class TelaTrabalho extends Fragment {

    private Gson gson = new Gson();
    private Button btSolicitar;
    private Trabalho atual;
    private SolicitacaoController controller;

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

        atual = gson.fromJson(argumento.getString("atual"),
                Trabalho.class);
        controller = new SolicitacaoController(getContext());

        verificarAnterior(argumento.getString("tela"));

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


        btSolicitar = getActivity().findViewById(R.id.btSolicitar);
        btSolicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btSolicitar.getText().toString().equals("CANCELAR")) {
                    controller.removerSolicitacao(atual.getCodigo(),((menu)getActivity())
                            .getAtual().getEmail());

                    atualizaTela();

                } else {
                    JSONObject json = new JSONObject();
                    try {
                        json.put("trabalho", atual.getCodigo());
                        json.put("email", ((menu) getActivity())
                                .getAtual().getEmail());

                        controller.solicitarTrabalho(json.toString());

                        atualizaTela();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    public void atualizaTela(){

        Fragment fragment = new TelaTrabalho();

        Bundle bundle = new Bundle();
        bundle.putString("atual",gson.toJson(atual));
        bundle.putString("tela","busca");

        fragment.setArguments(bundle);

        getFragmentManager().beginTransaction()
                .replace(R.id.frame_container,fragment)
                .commit();
    }

    public void verificarAnterior(String tela){

        btSolicitar = getActivity().findViewById(R.id.btSolicitar);
        List<Usuario> lista = controller.buscarSolicitacoes(atual.getCodigo());

        switch(tela){
            case "meusTrabalhos":{

                btSolicitar.setVisibility(View.GONE);
                ListView listView = getActivity().findViewById(R.id.listaSolicitantes);
                SolicitacaoAdapter adapter = new SolicitacaoAdapter(lista);
                listView.setAdapter(adapter);
                break;
            }
            case "busca":{
                for(Usuario u: lista){
                    if(u.getEmail().equals(((menu)getActivity())
                            .getAtual().getEmail())){
                        btSolicitar.setText("CANCELAR");
                        btSolicitar.setBackgroundColor(Color.RED);
                        break;
                    }
                }

            }
        }
    }

    private class SolicitacaoAdapter extends BaseAdapter{

        private List<Usuario> solicitantes;

        public SolicitacaoAdapter(List<Usuario> solicitantes) {
            this.solicitantes = solicitantes;
        }

        @Override
        public int getCount() {
            return solicitantes.size();
        }

        @Override
        public Object getItem(int position) {
            return solicitantes.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = convertView;
            LayoutInflater inflater = getActivity().getLayoutInflater();
            view = inflater.inflate(R.layout.solicitacoes, null);

            TextView tv = view.findViewById(R.id.nomeSolicitante);
            tv.setText(solicitantes.get(position).getNome());

            return view;
        }
    }
}
