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

public class SolicitacaoController {
    private OkHttpClient client;
    private Gson gson;
    private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private Context context;
    private String urlApi;

    public SolicitacaoController(Context context){
        this.client = new OkHttpClient();
        this.context = context;
        this.urlApi ="http://10.0.3.2:8080/pdm-api/pdm/solicitacao";
        this.gson = new Gson();
    }

    public void solicitarTrabalho(String dados){

        RequestBody body = RequestBody.create(JSON, dados);
        Request request = new Request.Builder().url(urlApi)
                .post(body).build();

        try {
            Response response = client.newCall(request).execute();

            if(response.code() == 201){
                Toast.makeText(context, "Solicitação enviada",
                        Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "Erro na solicitação",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
