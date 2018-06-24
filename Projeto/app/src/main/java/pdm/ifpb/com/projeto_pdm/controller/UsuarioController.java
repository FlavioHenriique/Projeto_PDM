package pdm.ifpb.com.projeto_pdm.controller;

import android.content.Context;
import android.os.Message;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import pdm.ifpb.com.projeto_pdm.menu;
import pdm.ifpb.com.projeto_pdm.model.Usuario;

public class UsuarioController {

    private OkHttpClient client;
    private Gson gson;
    private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private Context context;

    public UsuarioController(Context context){
        client = new OkHttpClient();
        this.context = context;
        this.gson = new Gson();
    }

    public String login(String dados){

        String url = "http://10.0.3.2:8080/pdm-api/pdm/usuario";
        RequestBody body = RequestBody.create(JSON, dados);

        Request request = new Request.Builder().url(url).post(body).build();

        try {
            Response response = client.newCall(request).execute();
            if(response.code() != 200){
                Toast.makeText(context, "Usuário não encontrado controa",
                        Toast.LENGTH_SHORT).show();
            }else{
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void cadastrar(String dados){

        String url = "http://10.0.3.2:8080/pdm-api/pdm/usuario/cadastro";
        RequestBody body = RequestBody.create(JSON, dados);
        Request request = new Request.Builder().url(url)
                .post(body).build();

        try {

            Response response = client.newCall(request).execute();

            if (response.code() == 201){
                Toast.makeText(context, "Usuário cadastrado!",
                        Toast.LENGTH_SHORT).show();
            }else if (response.code() == 403){
                Toast.makeText(context, "Este email já está sendo utilizado!",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Usuario atualizar(Usuario u){

        String url = "http://10.0.3.2:8080/pdm-api/pdm/usuario";
        RequestBody body = RequestBody.create(JSON, gson.toJson(u));
        Request request = new Request.Builder().url(url).put(body).build();

        try {
            Response response = client.newCall(request).execute();
            Toast.makeText(context, "Usuário atualizado",
                    Toast.LENGTH_SHORT).show();

            return gson.fromJson(response.body().string(), Usuario.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
