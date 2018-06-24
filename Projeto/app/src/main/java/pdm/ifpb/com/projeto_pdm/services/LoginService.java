package pdm.ifpb.com.projeto_pdm.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import pdm.ifpb.com.projeto_pdm.Inicial;
import pdm.ifpb.com.projeto_pdm.controller.UsuarioController;
import pdm.ifpb.com.projeto_pdm.model.Usuario;

public class LoginService extends Service {
    public LoginService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
      return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

            UsuarioController controller = new UsuarioController(getBaseContext());


            Message msg = new Message();
            msg.obj = controller.login(intent.getStringExtra("login"));

            Inicial.handler.sendMessage(msg);

        return START_STICKY;
    }
}
