package pdm.ifpb.com.projeto_pdm.controller;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import pdm.ifpb.com.projeto_pdm.R;
import pdm.ifpb.com.projeto_pdm.menu;
import pdm.ifpb.com.projeto_pdm.model.Trabalho;

public class TrabalhoController {

    private Context context;
    private Gson gson;
    private OkHttpClient client;
    private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private String urlApi;

    public TrabalhoController(Context context){
        this.gson = new Gson();
        //this.urlApi = "http://10.0.3.2:8081/pdm-api/pdm/trabalho/";
        this.urlApi = context.getString(R.string.restUrl).concat("trabalho/");
        this.client = new OkHttpClient();
        this.context = context;
    }

    public boolean cadastrar(Trabalho trabalho){

        if(verificaTrabalho(trabalho)){
            String url = urlApi;
            RequestBody body = RequestBody.create(JSON, gson.toJson(trabalho));
            Request request = new Request.Builder().url(url).post(body).build();

            try {
                Response response = client.newCall(request).execute();

                if(response.code() == 201){
                    Toast.makeText(context, "Trabalho cadastrado",
                            Toast.LENGTH_SHORT).show();
                    return true;
                }else{
                    Toast.makeText(context, "Erro!",
                            Toast.LENGTH_SHORT).show();
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
            return false;
    }

    public List<Trabalho> meusTrabalhos(String email){


        String url = urlApi  + email;
        Request request = new Request.Builder().url(url).get().build();

        try {
            Response response = client.newCall(request).execute();
            if(response.body() != null){

                return Arrays.asList(gson.fromJson(response.body()
                        .string(), Trabalho[].class));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Trabalho> buscarTrabalhos(String campo, String valor, String email){

        String url = urlApi + "busca/" +campo+"/" + valor;

        Request request = new Request.Builder().url(url).get().build();

        try {
            Response response = client.newCall(request).execute();
            String corpo = response.body().string();
            return verificaContratante(email,gson.fromJson(corpo, Trabalho[].class));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public List<Trabalho> verificaContratante(String email,Trabalho[] lista){

        List<Trabalho> novaLista = new ArrayList<>();

        for(Trabalho t: lista){
            if(!t.getContratante().getEmail().equals(email)){
                novaLista.add(t);
            }
        }
        return novaLista;
    }

    public void excluirTrabalho(int codigo){

        String url = urlApi +codigo;
        Request request = new Request.Builder().url(url).delete().build();

        try {
            Response response = client.newCall(request).execute();
            if(response.code() == 200){
                Toast.makeText(context, "Trabalho deletado com sucesso",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean verificaTrabalho(Trabalho t){

        if(t.getData().equals("") || t.getCategoria().equals("")
                || t.getCidade().equals("") || t.getDescricao().equals("")
                || t.getHorario().equals("") || t.getTitulo().equals("")){

            Toast.makeText(context, "Preencha todos os campos",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
