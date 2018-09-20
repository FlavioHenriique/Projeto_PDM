package pdm.ifpb.com.projeto_pdm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Base64;
import android.view.Menu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import pdm.ifpb.com.projeto_pdm.model.Usuario;
import pdm.ifpb.com.projeto_pdm.sqlite.FotoController;

public class menu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Usuario atual;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.setTitle("Trabalhos na sua cidade");

        Gson gson = new Gson();
        Intent intent = getIntent();
        atual =  gson.fromJson(intent.getStringExtra("atual"),
                Usuario.class);

        strictmode();
        items();
        foto();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().
                    add(R.id.frame_container, new TelaPrincipal()).commit();
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.sair) {
            Intent intent = new Intent(menu.this, Inicial.class);
            startActivity(intent);

            SharedPreferences.Editor editor = getSharedPreferences("usuario",
                    Context.MODE_PRIVATE).edit();
            editor.remove("atual");
            editor.commit();

            finish();

        } else if (id == R.id.buscar_trabalhos) {

            getSupportFragmentManager().beginTransaction().
                    replace(R.id.frame_container, new busca()).commit();
            this.setTitle("Buscar trabalhos");

        } else if (id == R.id.meu_perfil) {

            getSupportFragmentManager().beginTransaction().
                    replace(R.id.frame_container, new MeuPerfil()).commit();
            this.setTitle("Editar meu perfil");

        } else if (id == R.id.meus_trabalhos) {
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.frame_container, new MeusTrabalhos()).commit();
            this.setTitle("Meus trabalhos");

        }else if (id == R.id.tela_inicial){

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_container, new TelaPrincipal()).commit();
            this.setTitle("Trabalhos na sua cidade");

        }else if (id == R.id.minhas_solicitacoes){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_container, new MinhasSolicitacoes())
                    .commit();
            this.setTitle("Minhas solicitações");

        }else if(id == R.id.configuracoes){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_container,new Configuracoes())
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public Usuario getAtual() {
        return atual;
    }

    public void setAtual(Usuario atual) {
        this.atual = atual;

    }

    public void items(){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        TextView tv = header.findViewById(R.id.nomeUsuario);
        tv.setText(atual.getNome());

        imageView = header.findViewById(R.id.imagem);

    }

    public void strictmode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }

    private void foto(){

        FotoController controller = new FotoController(this);
        String foto = controller.recuperaFoto(this.getAtual().getEmail());

        byte[] bytes = Base64.decode(foto,0);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Bitmap imagem = (Bitmap) data.getExtras().get("data");
        imageView.setImageBitmap(imagem);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imagem.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] array = stream.toByteArray();

        // Salvando no SQLite
        FotoController controller = new FotoController(this);
        controller.inserirFoto(
                Base64.encodeToString(array,Base64.DEFAULT),
                this.getAtual().getEmail()
        );
    }

}
