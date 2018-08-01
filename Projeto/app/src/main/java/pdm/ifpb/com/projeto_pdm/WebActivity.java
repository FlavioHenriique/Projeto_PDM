package pdm.ifpb.com.projeto_pdm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        String url = "https://projetosif.blogspot.com/b/post-preview?" +
                "token=tpfj-GQBAAA.oJNxIIEnW9zccr0bp93hrn6TGEOarkybDC3gR1IDg" +
                "-ZMObaAhDkillz5-ObTvdq9DeDiLMBlascyGdLJMwB05w.l_Lkzclsuj2dkTO" +
                "eh7m7kQ&postId=1525072369699128250&type=POST";
        WebView webView = findViewById(R.id.webViewBlog);
        webView.loadUrl(url);

    }
}
