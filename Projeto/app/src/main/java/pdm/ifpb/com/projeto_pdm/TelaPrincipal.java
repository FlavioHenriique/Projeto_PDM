package pdm.ifpb.com.projeto_pdm;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class TelaPrincipal extends Fragment {

    private ArrayList<String> palavras;


    public TelaPrincipal() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_tela_principal, container, false);
    }

}
