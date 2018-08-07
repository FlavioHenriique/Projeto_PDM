package pdm.ifpb.com.projeto_pdm;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import pdm.ifpb.com.projeto_pdm.adapter.MyAdapter;
import pdm.ifpb.com.projeto_pdm.controller.TrabalhoController;
import pdm.ifpb.com.projeto_pdm.model.Trabalho;


public class busca extends Fragment {

    private ListView listView;
    private String email;
    public busca() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_busca, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        email = ((menu)getActivity()).getAtual().getEmail();

        listView = getActivity().findViewById(R.id.listaBusca);

        String[] categorias = {"Construção","Informática","Mecânica"};
        final Spinner spinner = getActivity().findViewById(R.id.buscaCategoria);
        ArrayAdapter adapterCategoria = new ArrayAdapter(this.getContext(),R.layout
                .support_simple_spinner_dropdown_item, categorias);
        adapterCategoria.setDropDownViewResource(R.layout
                .support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapterCategoria);

        Button btcategoria = getActivity().findViewById(R.id.btBuscaCategoria);
        btcategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrabalhoController controller = new TrabalhoController(getContext());
                List<Trabalho> lista = controller.buscarTrabalhos("categoria",
                        spinner.getSelectedItem().toString(),
                        email);

                criarAdapter(lista);
            }
        });

        Button bt = getActivity().findViewById(R.id.btBuscaCidade);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText busca = getActivity().findViewById(R.id.cidadeBuscada);

                if(!busca.getText().toString().equals("")){

                    TrabalhoController controller = new TrabalhoController(getContext());
                    List<Trabalho> lista= controller.buscarTrabalhos("cidade",
                            busca.getText().toString(), email);

                    criarAdapter(lista);

                }else{
                    Toast.makeText(getContext(), "Preencha o campo",
                            Toast.LENGTH_SHORT).show();
                }
       }
        });
    }

    public void criarAdapter(List<Trabalho> lista){

        if(lista.isEmpty()){
            Toast.makeText(getContext(),"Nenhum trabalho foi encontrado!",
                    Toast.LENGTH_SHORT).show();
        }
        MyAdapter adapter = new MyAdapter(getContext(),lista,"busca",email);
        adapter.setManager(getFragmentManager());
        listView.setAdapter(adapter);

    }
}
