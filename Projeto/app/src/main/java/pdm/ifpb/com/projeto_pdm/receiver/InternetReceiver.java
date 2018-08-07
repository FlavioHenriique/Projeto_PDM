package pdm.ifpb.com.projeto_pdm.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class InternetReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if(!verificarConexao(context)){
            Toast.makeText(context, "Sem conexão com a internet!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private boolean verificarConexao(Context context){

        ConnectivityManager manager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if(manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()){
            return true;
        }
        return false;
    }
}
