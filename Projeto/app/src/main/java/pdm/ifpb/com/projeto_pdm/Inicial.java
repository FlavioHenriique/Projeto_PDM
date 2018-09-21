package pdm.ifpb.com.projeto_pdm;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.BatteryManager;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import pdm.ifpb.com.projeto_pdm.model.Usuario;
import pdm.ifpb.com.projeto_pdm.receiver.InternetReceiver;
import pdm.ifpb.com.projeto_pdm.services.LoginService;

public class Inicial extends AppCompatActivity {
    private int cont = 0;
    public static Handler handler;
    private static int count = 0;
    private InternetReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);
        strictmode();
        handler = new MyHandler();
        this.setTitle("Login");
        //registerReceiver(receiver,new IntentFilter
                //("android.net.wifi.WIFI_STATE_CHANGED"));

        if(verificarConexao()){
            verificarUsuarioLogado();
        }

        final TextView web = findViewById(R.id.webview);

        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                web.setTextColor(Color.RED);

                Intent intent = new Intent(Inicial.this,
                        WebActivity.class);
                startActivity(intent);
            }
        });

         final TextView tv = findViewById(R.id.cadastrar);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setTextColor(Color.RED);
                if(verificarConexao()){
                    Intent intent = new Intent(Inicial.this,
                            Cadastro_usuario.class);
                    startActivity(intent);
                }
            }
        });

        Button btEntrar = findViewById(R.id.btEntrar);
        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation shake = AnimationUtils.loadAnimation(
                        v.getContext(),
                        R.anim.anim_alpha
                );
                v.startAnimation(shake);
                if(verificarConexao()){
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
            }
        });
        }


    public void strictmode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }

    public void verificarUsuarioLogado(){

        SharedPreferences preferences = getSharedPreferences("usuario"
                ,Context.MODE_PRIVATE);
        String user = preferences.getString("atual","default");

        if(!user.equals("default")){

            entrar(user);
        }
    }

    private boolean verificarConexao(){
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(CONNECTIVITY_SERVICE);

        if(manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()){
            return true;
        }
        Toast.makeText(this, "Sem conexão com a internet!",
                Toast.LENGTH_SHORT).show();
        return false;
    }

    public void entrar(String user){

        Intent intent = new Intent(Inicial.this, menu.class);
        intent.putExtra("atual",user);
        startActivity(intent);
        finish();
    }

        private class MyHandler extends Handler{
            public MyHandler(){
                super();
            }

            @Override
            public void handleMessage(Message msg) {

                SharedPreferences.Editor editor = getSharedPreferences("usuario",
                        Context.MODE_PRIVATE).edit();
                editor.putString("atual",(String)msg.obj);
                editor.commit();

                entrar((String) msg.obj);
            }
        }

    @Override
    protected void onDestroy() {

    //    unregisterReceiver(receiver);
        super.onDestroy();
    }
}

