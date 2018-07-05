package pdm.ifpb.com.projeto_pdm;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import pdm.ifpb.com.projeto_pdm.controller.UsuarioController;
import pdm.ifpb.com.projeto_pdm.model.Trabalho;
import pdm.ifpb.com.projeto_pdm.model.Usuario;

import static pdm.ifpb.com.projeto_pdm.R.layout.support_simple_spinner_dropdown_item;


public class MeuPerfil extends Fragment {


    private Usuario atual;

    public MeuPerfil() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_meu_perfil, container,
                false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        atual = ((menu)this.getActivity()).getAtual();
        final Spinner spinner = getActivity().findViewById(R.id.atEstado);

        String[] arrayEstados = {"PB","PE","RJ","SP","SC"};

        ArrayAdapter adapter = new ArrayAdapter(this.getContext(),
                R.layout.support_simple_spinner_dropdown_item,
                arrayEstados);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final EditText atNome = getActivity().findViewById(R.id.atNome);
        final EditText atCidade = getActivity().findViewById(R.id.atCidade);
        final EditText atSenha = getActivity().findViewById(R.id.atSenha);

        atNome.setText(atual.getNome());
        atCidade.setText(atual.getCidade());

        Button btAtualizar = getActivity().findViewById(R.id.btAtualizar);
        btAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario u = new Usuario();
                u.setCidade(atCidade.getText().toString());
                u.setEstado(spinner.getSelectedItem().toString());
                u.setNome(atNome.getText().toString());
                u.setSenha(atSenha.getText().toString());
                u.setEmail(atual.getEmail());

                UsuarioController controller = new UsuarioController(getContext());
                ((menu) getActivity()).setAtual(controller.atualizar(u));
            }
        });
    }
}
