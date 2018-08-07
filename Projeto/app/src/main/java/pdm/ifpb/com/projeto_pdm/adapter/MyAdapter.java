package pdm.ifpb.com.projeto_pdm.adapter;

import android.app.Activity;
import android.content.Context;

import android.os.Bundle;
import android.support.v4.app.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import pdm.ifpb.com.projeto_pdm.MeusTrabalhos;
import pdm.ifpb.com.projeto_pdm.R;
import pdm.ifpb.com.projeto_pdm.TelaTrabalho;
import pdm.ifpb.com.projeto_pdm.cadastro_trabalho;
import pdm.ifpb.com.projeto_pdm.controller.TrabalhoController;
import pdm.ifpb.com.projeto_pdm.model.Trabalho;

public class MyAdapter extends BaseAdapter{


    private String tela;
    private Context context;
    private List<Trabalho> lista;
    private FragmentManager manager;
    private String email;

    public MyAdapter(Context context, List<Trabalho> lista, String tela, String email) {
        this.lista = lista;
        this.email = email;
        this.tela = tela;
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

        if(email.equals(lista.get(position).getContratante().getEmail())){


            opcaoExcluir(view,position,true);
        }else{
            opcaoExcluir(view,position,false);
        }

        nome.setText(lista.get(position).getTitulo());
        descricao.setText(lista.get(position).getDescricao());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new TelaTrabalho();
                Bundle bundle = new Bundle();
                bundle.putString("atual",gson.toJson(lista.get(position)));
                bundle.putString("tela",tela);
                fragment.setArguments(bundle);

                manager.beginTransaction()
                        .replace(R.id.frame_container, fragment)
                        .commit();

                ((Activity) context).setTitle(lista.get(position).getTitulo());
            }
        });
        return view;
    }

    public void opcaoExcluir(View view, final int position, boolean verificacao){

        ImageView excluir = view.findViewById(R.id.excluir);
        if(verificacao){

            excluir = view.findViewById(R.id.excluir);
            excluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TrabalhoController controller = new TrabalhoController(context);
                    controller.excluirTrabalho(lista.get(position).getCodigo());

                    Fragment fragment = new MeusTrabalhos();
                    manager.beginTransaction()
                            .replace(R.id.frame_container, fragment)
                            .commit();

                }
            });
        }else{
            excluir.setVisibility(View.GONE);
        }
    }
}
