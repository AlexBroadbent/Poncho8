package gq.xander.poncho8;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public final class MainActivity extends Activity {

  private WebView webView;


  @Override
  @SuppressLint("SetJavaScriptEnabled")
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // load loyalty card homepage
    webView = findViewById(R.id.webview);
    webView.loadUrl(getString(R.string.home_url));

    // enable JavaScript
    WebSettings settings = webView.getSettings();
    settings.setJavaScriptEnabled(true);

    // set custom WebViewClient
    webView.setWebViewClient(new PonchoWebViewClient());
  }

  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
      webView.goBack();
      return true;
    }
    return super.onKeyDown(keyCode, event);
  }


  /**
   * WebViewClient implementation which will override any URL which is outside of the Poncho8 scope
   * and start a new activity for the request.
   */
  private final class PonchoWebViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
      if (request.getUrl().getHost().contains(getString(R.string.host_uri))) {
        return false;
      }

      Intent intent = new Intent(Intent.ACTION_VIEW, request.getUrl());
      startActivity(intent);
      return true;
    }

  }

}
