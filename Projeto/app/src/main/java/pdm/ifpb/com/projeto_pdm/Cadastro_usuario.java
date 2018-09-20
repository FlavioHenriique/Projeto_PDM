package pdm.ifpb.com.projeto_pdm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;

import pdm.ifpb.com.projeto_pdm.model.Usuario;
import pdm.ifpb.com.projeto_pdm.services.CadastroService;


public class Cadastro_usuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        this.setTitle("Cadastro de usuário");

        final Spinner spinner = findViewById(R.id.cadEstado);
        spinner.setPrompt("Selecione");

        String[] arrayEstados = {"PB","PE","RJ","SP","SC"};
        ArrayAdapter<String> adapter= new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item,arrayEstados);

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button btCadastrar  = findViewById(R.id.btCadastrar);
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation shake = AnimationUtils.loadAnimation(
                        v.getContext(),
                        R.anim.anim_alpha
                );
                v.startAnimation(shake);

                EditText nome = findViewById(R.id.atNome);
                EditText cidade = findViewById(R.id.cadCidade);

                EditText senha = findViewById(R.id.cadSenha);
                EditText email = findViewById(R.id.cadEmail);

                Usuario usuario = new Usuario();
                usuario.setCidade(cidade.getText().toString());
                usuario.setEmail(email.getText().toString());
                usuario.setEstado(spinner.getSelectedItem().toString());
                usuario.setNome(nome.getText().toString());
                usuario.setSenha(senha.getText().toString());

                Gson gson = new Gson();

               Intent intent = new Intent(Cadastro_usuario.this,
                       CadastroService.class);
                intent.putExtra("usuario",gson.toJson(usuario));

                startService(intent);
            }
        });

    }
}
