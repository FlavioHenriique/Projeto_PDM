package pdm.ifpb.com.projeto_pdm;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import pdm.ifpb.com.projeto_pdm.model.Trabalho;
import pdm.ifpb.com.projeto_pdm.model.Usuario;


public class MeusTrabalhos extends Fragment {


    private Trabalho[] lista;

    public MeusTrabalhos() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_meus_trabalhos, container,
                false);



        FloatingActionButton button = view.findViewById(R.id.addTrabalho);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new cadastro_trabalho();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container, fragment);
                transaction.commit();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        meusTrabalhos();
    }

    private void meusTrabalhos() {

        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        String email = ((menu) getActivity()).getAtual().getEmail();
        String url = "http://10.0.3.2:8080/pdm-api/pdm/trabalho/"+ email;

        Request request = new Request.Builder().url(url).get().build();
        Gson gson = new Gson();

        try {
            Response response = client.newCall(request).execute();
            lista = gson.fromJson(response.body().string(), Trabalho[].class);

            ListView lview = getView().findViewById(R.id.listaTrabalhos);

            MyAdapter adapter = new MyAdapter(getContext());

            lview.setAdapter(adapter);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private class MyAdapter extends BaseAdapter{

        Context context;

        public MyAdapter(Context context) {
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

            TextView nome = view.findViewById(R.id.tNome);
            TextView descricao = view.findViewById(R.id.tDescricao);

            nome.setText(lista[position].getTitulo());
            descricao.setText(lista[position].getDescricao());

            return view;
        }
    }

}

