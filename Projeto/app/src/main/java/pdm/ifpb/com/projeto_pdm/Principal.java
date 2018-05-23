package pdm.ifpb.com.projeto_pdm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Principal extends AppCompatActivity {

    List<String> palavras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_principal);

        ListView lista = findViewById(R.id.lista);
        palavras = new ArrayList<>();
        palavras.add("kofkdofk");
        palavras.add("aaaaaaa");
        palavras.add("bbbbbbk");

        palavras.add("ccccccc");
        palavras.add("ddddd");
        palavras.add("eeeeeee");
        palavras.add("ffffff");

        MyAdapter my = new MyAdapter();
        lista.setAdapter(my);
    }

     class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return palavras.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.trabalhos,null);
            TextView nome = (TextView) convertView.findViewById(R.id.nome);
            TextView descricao = (TextView)  convertView.findViewById(R.id.descricao);


            nome.setText(palavras.get(position));
            descricao.setText("descricao");
            return convertView;
        }
    }

}
