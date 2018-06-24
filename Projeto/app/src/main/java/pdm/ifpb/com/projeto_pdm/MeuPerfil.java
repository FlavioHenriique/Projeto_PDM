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
import pdm.ifpb.com.projeto_pdm.model.Usuario;

import static pdm.ifpb.com.projeto_pdm.R.layout.support_simple_spinner_dropdown_item;


public class MeuPerfil extends Fragment {


    private Usuario atual;

    public MeuPerfil() {
        // Required empty public constructor
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
        atSenha.setText(atual.getSenha());

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
                System.out.println(u.toString());
                Gson gson = new Gson();

                String url = "http://10.0.3.2:8080/pdm-api/pdm/usuario";
                OkHttpClient client = new OkHttpClient();

                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(JSON, gson.toJson(u));

                Request request = new Request.Builder().url(url).put(body).build();

                try {
                    Response response = client.newCall(request).execute();
                    Toast.makeText(getContext(), "Usuário atualizado",
                            Toast.LENGTH_SHORT).show();

                    ((menu) getActivity()).setAtual(gson.fromJson(response.body()
                            .string(),Usuario.class));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
