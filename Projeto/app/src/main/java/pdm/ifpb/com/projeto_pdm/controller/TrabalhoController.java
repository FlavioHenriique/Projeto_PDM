package pdm.ifpb.com.projeto_pdm.controller;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import pdm.ifpb.com.projeto_pdm.menu;
import pdm.ifpb.com.projeto_pdm.model.Trabalho;

public class TrabalhoController {

    private Context context;
    private Gson gson;
    private OkHttpClient client;
    private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public TrabalhoController(Context context){
        gson = new Gson();
        client = new OkHttpClient();
        this.context = context;
    }

    public void cadastrar(Trabalho trabalho){

        String url = "http://10.0.3.2:8080/pdm-api/pdm/trabalho";

        RequestBody body = RequestBody.create(JSON, gson.toJson(trabalho));

        Request request = new Request.Builder().url(url).post(body).build();

        try {
            Response response = client.newCall(request).execute();

            if(response.code() == 201){
                Toast.makeText(context, "Trabalho cadastrado - controler",
                        Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "Erro!",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Trabalho[] meusTrabalhos(String email){


        String url = "http://10.0.3.2:8080/pdm-api/pdm/trabalho/"+ email;

        Request request = new Request.Builder().url(url).get().build();

        try {
            Response response = client.newCall(request).execute();
            return gson.fromJson(response.body().string(), Trabalho[].class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Trabalho[] buscarTrabalhos(String campo, String valor){

        String url = "http://10.0.3.2:8080/pdm-api/pdm/trabalho/busca/"
                +campo+"/" + valor;

        Request request = new Request.Builder().url(url).get().build();

        try {
            Response response = client.newCall(request).execute();

            return gson.fromJson(response.body().string(), Trabalho[].class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
