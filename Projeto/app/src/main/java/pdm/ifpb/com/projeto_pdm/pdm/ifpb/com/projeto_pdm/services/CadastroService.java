package pdm.ifpb.com.projeto_pdm.pdm.ifpb.com.projeto_pdm.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.widget.Toast;
import com.google.gson.Gson;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import pdm.ifpb.com.projeto_pdm.pdm.ifpb.com.projeto_pdm.model.Usuario;

public class CadastroService extends Service {
    public CadastroService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        strictmode();
        Gson gson = new Gson();

        String url = "http://10.0.3.2:8080/pdm-api/pdm/usuario/cadastro";
        OkHttpClient client = new OkHttpClient();

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, intent.getStringExtra("usuario"));

        Request request = new Request.Builder().url(url)
                .post(body).build();

        try {

            Response response = client.newCall(request).execute();
            System.out.println(response.code());
            if (response.code() == 201){
                Toast.makeText(this, "Usuário cadastrado!",
                        Toast.LENGTH_SHORT).show();
            }else if (response.code() == 403){
                Toast.makeText(this, "Este email já está sendo utilizado!",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return START_STICKY;
    }

    public void strictmode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }
}
