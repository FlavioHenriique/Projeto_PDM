package pdm.ifpb.com.projeto_pdm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Cadastro_usuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        Spinner spinner = findViewById(R.id.estado);
        spinner.setPrompt("Selecione");

        String[] arrayEstados = {"PB","PE","RJ","SP","SC"};
        ArrayAdapter<String> adapter= new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item,arrayEstados);

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
