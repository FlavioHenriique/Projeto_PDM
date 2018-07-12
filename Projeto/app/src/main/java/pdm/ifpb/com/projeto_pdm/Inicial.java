package pdm.ifpb.com.projeto_pdm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import pdm.ifpb.com.projeto_pdm.model.Usuario;
import pdm.ifpb.com.projeto_pdm.services.LoginService;

public class Inicial extends AppCompatActivity {

    public static Handler handler;
    private static int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);
        strictmode();
        handler = new MyHandler();
        this.setTitle("Login");

         TextView tv = findViewById(R.id.cadastrar);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Inicial.this,
                        Cadastro_usuario.class);
                startActivity(intent);
            }
        });

        Button bnot = findViewById(R.id.btNot);
        bnot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotificationCompat.Builder b = new NotificationCompat
                        .Builder(Inicial.this);
                b.setAutoCancel(true)
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.drawable.ic_add)
                        .setTicker("notificação nova")
                        .setContentTitle("titulo")
                        .setContentText("mensagem")
                        .setContentInfo("INFO");

                NotificationManager nm = (NotificationManager)
                        getSystemService(Context.NOTIFICATION_SERVICE);
                nm.notify(count, b.build());
                count++;
            }

        });

        Button btEntrar = findViewById(R.id.btEntrar);
        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText email = findViewById(R.id.loginEmail);
                EditText senha = findViewById(R.id.loginSenha);


                try {
                    JSONObject json = new JSONObject();
                    json.put("email",email.getText().toString());
                    json.put("senha",senha.getText().toString());

                    Intent intent = new Intent(Inicial.this,
                            LoginService.class);
                    intent.putExtra("login",json.toString());

                    startService(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        }

        public void strictmode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }
        private class MyHandler extends Handler{
            public MyHandler(){
                super();
            }

            @Override
            public void handleMessage(Message msg) {

                Gson gson = new Gson();

                Intent intent = new Intent(Inicial.this, menu.class);
                intent.putExtra("atual",(String) msg.obj);
                startActivity(intent);
                finish();
            }
        }
}
