package pdm.ifpb.com.projeto_pdm.pdm.ifpb.com.projeto_pdm.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import pdm.ifpb.com.projeto_pdm.Inicial;

public class LoginService extends Service {
    public LoginService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
      return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        try {
            JSONObject json = new JSONObject(intent.getStringExtra("login"));
            String url = "http://10.0.3.2:8080/pdm-api/pdm/usuario";
            OkHttpClient client = new OkHttpClient();
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");

            RequestBody body = RequestBody.create(JSON, intent.getStringExtra("login"));

            Request request = new Request.Builder().url(url).post(body).build();

            Response response = client.newCall(request).execute();
            if(response.code() != 202){
                Toast.makeText(this, "Usuário não encontrado",
                        Toast.LENGTH_SHORT).show();
            }else{
                Message msg = new Message();
                msg.obj = response.code();

                Inicial.handler.sendMessage(msg);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return START_STICKY;
    }
}
