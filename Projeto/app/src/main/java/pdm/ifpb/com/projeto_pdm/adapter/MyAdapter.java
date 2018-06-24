package pdm.ifpb.com.projeto_pdm.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import pdm.ifpb.com.projeto_pdm.R;
import pdm.ifpb.com.projeto_pdm.model.Trabalho;

public class MyAdapter extends BaseAdapter{

    private Context context;
    private Trabalho[] lista;

    public MyAdapter(Context context, Trabalho[] lista) {
        this.lista = lista;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lista.length;
    }

    @Override
    public Object getItem(int position) {
        return lista[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        view = inflater.inflate(R.layout.trabalhos, null);

        final TextView nome = view.findViewById(R.id.tNome);
        TextView descricao = view.findViewById(R.id.tDescricao);

        nome.setText(lista[position].getTitulo());
        descricao.setText(lista[position].getDescricao());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, nome.getText().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
