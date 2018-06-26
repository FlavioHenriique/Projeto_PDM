package pdm.ifpb.com.projeto_pdm.adapter;

import android.app.Activity;
import android.content.Context;

import android.os.Bundle;
import android.support.v4.app.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import pdm.ifpb.com.projeto_pdm.R;
import pdm.ifpb.com.projeto_pdm.TelaTrabalho;
import pdm.ifpb.com.projeto_pdm.cadastro_trabalho;
import pdm.ifpb.com.projeto_pdm.model.Trabalho;

public class MyAdapter extends BaseAdapter{

    private Context context;
    private List<Trabalho> lista;
    private FragmentManager manager;

    public MyAdapter(Context context, List<Trabalho> lista) {
        this.lista = lista;
        this.context = context;

    }

    public FragmentManager getManager() {
        return manager;
    }

    public void setManager(FragmentManager manager) {
        this.manager = manager;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {

        final Gson gson = new Gson();
        View view = convertView;
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        view = inflater.inflate(R.layout.trabalhos, null);

        final TextView nome = view.findViewById(R.id.tNome);
        TextView descricao = view.findViewById(R.id.tDescricao);

        nome.setText(lista.get(position).getTitulo());
        descricao.setText(lista.get(position).getDescricao());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new TelaTrabalho();
                Bundle bundle = new Bundle();
                bundle.putString("atual",gson.toJson(lista.get(position)));
                fragment.setArguments(bundle);

                manager.beginTransaction()
                        .replace(R.id.frame_container, fragment)
                        .commit();

            }
        });
        return view;
    }
}
