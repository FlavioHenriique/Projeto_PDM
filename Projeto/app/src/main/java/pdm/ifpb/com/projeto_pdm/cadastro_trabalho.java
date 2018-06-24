package pdm.ifpb.com.projeto_pdm;


import android.os.Bundle;
import android.support.annotation.NonNull;
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
import pdm.ifpb.com.projeto_pdm.model.Trabalho;


public class cadastro_trabalho extends Fragment {


    public cadastro_trabalho() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_cadastro_trabalho, container,
                false);

        }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] categorias = {"Construção","Informática","Mecânica"};
        String[] arrayEstados = {"PB","PE","RJ","SP","SC"};

        final Spinner estados = getActivity().findViewById(R.id.estadoTrabalho);
        final Spinner categoriasSpinner = getActivity().findViewById(R.id.categoriaTrabalho);

        ArrayAdapter adapterEstado = new ArrayAdapter(this.getContext(),
                R.layout.support_simple_spinner_dropdown_item,
                arrayEstados);
        adapterEstado.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        estados.setAdapter(adapterEstado);

        ArrayAdapter adapterCategoria = new ArrayAdapter(this.getContext(),R.layout
                .support_simple_spinner_dropdown_item, categorias);
        adapterCategoria.setDropDownViewResource(R.layout
                .support_simple_spinner_dropdown_item);
        categoriasSpinner.setAdapter(adapterCategoria);

        Button bt = getActivity().findViewById(R.id.btCadastroTrabalho);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText titulo = getActivity().findViewById(R.id.tituloTrabalho);

                EditText cidade = getActivity().findViewById(R.id.cidadeTrabalho);
                EditText valor = getActivity().findViewById(R.id.valorTrabalho);
                EditText horario = getActivity().findViewById(R.id.horarioTrabalho);
                EditText data = getActivity().findViewById(R.id.dataTrabalho);
                EditText descricao = getActivity().findViewById(R.id.descricao);

                Trabalho trabalho = new Trabalho();
                trabalho.setCategoria(categoriasSpinner.getSelectedItem().toString());
                trabalho.setCidade(cidade.getText().toString());
                trabalho.setContratante(((menu) getActivity()).getAtual());
                trabalho.setData(data.getText().toString());
                trabalho.setDescricao(descricao.getText().toString());
                trabalho.setHorario(horario.getText().toString());
                trabalho.setTitulo(titulo.getText().toString());
                trabalho.setEstado(estados.getSelectedItem().toString());
                trabalho.setValor(Float.parseFloat(valor.getText().toString()));


                Gson gson = new Gson();

                OkHttpClient client = new OkHttpClient();
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                String url = "http://10.0.3.2:8080/pdm-api/pdm/trabalho";

                RequestBody body = RequestBody.create(JSON, gson.toJson(trabalho));

                Request request = new Request.Builder().url(url).post(body).build();

                try {
                    Response response = client.newCall(request).execute();

                    if(response.code() == 201){
                        Toast.makeText(getContext(), "Trabalho cadastrado",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), "Erro!",
                                Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
