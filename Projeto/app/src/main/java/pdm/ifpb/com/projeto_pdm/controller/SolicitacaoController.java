package pdm.ifpb.com.projeto_pdm.controller;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import pdm.ifpb.com.projeto_pdm.model.Trabalho;
import pdm.ifpb.com.projeto_pdm.model.Usuario;

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

    public List<Usuario> buscarSolicitacoes(int trabalho){

        String url = urlApi + "/"+ trabalho;

        Request request = new Request.Builder().url(url).get().build();

        try {
            Response response = client.newCall(request).execute();
            if(response.code() == 302){
                return retornaLista(gson.fromJson(response.body()
                        .string(),Usuario[].class));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removerSolicitacao(int trabalho, String email){

        String url = urlApi + "/"+ email + "/" + trabalho;

        Request request = new Request.Builder().url(url).delete().build();
        try {
            Response response = client.newCall(request).execute();
            if(response.code()== 200){
                Toast.makeText(context, "Solicitação cancelada",
                        Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "Erro", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Trabalho> minhasSolicitacoes(String email){

        String url = urlApi + "/busca" + "/" + email;

        Request request = new Request.Builder().url(url).get().build();
        try {
            Response response = client.newCall(request).execute();
            if(response.code() == 200){
                Trabalho[] trabalhos = gson.fromJson(response.body().string(),
                        Trabalho[].class );

                return retornaListaTrabalhos(trabalhos);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void aceitarSolicitacao(String dados){

        RequestBody body = RequestBody.create(JSON, dados);
        Request request = new Request.Builder().url(urlApi).put(body).build();

        try {
            Response response = client.newCall(request).execute();
            if (response.code() == 200){
                Toast.makeText(context, "Solicitação aceita",
                        Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "Erro",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> retornaLista(Usuario[] lista){

        List<Usuario> novaLista = new ArrayList<>();

        for(Usuario u : lista){
            novaLista.add(u);
        }
        return novaLista;
    }

    public List<Trabalho> retornaListaTrabalhos(Trabalho[] lista){

        List<Trabalho> novaLista = new ArrayList<>();

        for(Trabalho t : lista){
            novaLista.add(t);
        }
        return novaLista;
    }

}
