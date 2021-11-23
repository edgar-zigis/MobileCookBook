package eu.kevin.jstest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.webkit.WebView
import android.widget.Toast

import android.webkit.JavascriptInterface

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val webView = findViewById<WebView>(R.id.web_view)
        webView.loadUrl("https://url.to.web");
        webView.settings.javaScriptEnabled = true
        webView.addJavascriptInterface(WebViewJavaScriptInterface(this), "AndroidHandler")

        Handler().postDelayed({
            webView.evaluateJavascript("window.submitCardData('LTXXXXXX', 'John Doe');") {
                //  do something
            }
        }, 2000)
    }

    class WebViewJavaScriptInterface(private val context: Context) {

        @JavascriptInterface
        fun postMessage(message: String) {
            Toast.makeText(
                context,
                message,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}