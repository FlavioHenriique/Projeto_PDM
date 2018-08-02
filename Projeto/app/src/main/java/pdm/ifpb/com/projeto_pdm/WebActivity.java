package pdm.ifpb.com.projeto_pdm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        this.setTitle("Página do App");

        String url = "https://projetosif.blogspot.com/2018/05/" +
                "flavio-henrique-aplicacao-para-busca-e.html";
        WebView webView = findViewById(R.id.webViewBlog);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);


    }
}
