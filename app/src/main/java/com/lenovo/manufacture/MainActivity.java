package com.lenovo.manufacture;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.lenovo.manufacture.C.MC8Activity;
import com.lenovo.manufacture.b.Main2Activity;
import com.lenovo.manufacture.b.Main3Activity;
import com.lenovo.manufacture.b.Main4Activity;
import com.lenovo.manufacture.b.Main6Activity;
import com.lenovo.manufacture.b.Main8Activity;
import com.lenovo.manufacture.czx.Item2Activity;
import com.lenovo.manufacture.czx.Item6Activity;
import com.lenovo.manufacture.czx.Item7Activity;
import com.lenovo.manufacture.czx.Item8;
import com.lenovo.manufacture.zhy.five.Problem;
import com.lenovo.manufacture.zhy.four.Production;
import com.lenovo.manufacture.zhy.three.Market;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Amoly
 * @date 2019/10/24.
 * GitHub：
 * email：
 * description：
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initView();
        initWebView();
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void initWebView() {

        TWebView webView = new TWebView(this, null);
        ViewGroup viewParent = findViewById(R.id.webView1);
        viewParent.addView(webView, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));

        webView.loadUrl("file:///android_asset/indexd.html");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                /* mWebView.showLog("test Log"); */
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setBackgroundColor(0);
        webView.requestFocus();
        webView.addJavascriptInterface(new JavaScriptInterface(this), "nativeMethod");
        WebSettings webSetting = webView.getSettings();
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(false);
        webSetting.setAppCacheEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setDefaultTextEncodingName("utf-8");
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);


        int screenDensity = getResources().getDisplayMetrics().densityDpi;
        WebSettings.ZoomDensity zoomDensity = WebSettings.ZoomDensity.MEDIUM;
        switch (screenDensity) {
            case DisplayMetrics.DENSITY_LOW:
                zoomDensity = WebSettings.ZoomDensity.CLOSE;
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                zoomDensity = WebSettings.ZoomDensity.MEDIUM;
                break;
            case DisplayMetrics.DENSITY_HIGH:
                zoomDensity = WebSettings.ZoomDensity.FAR;
                break;
        }
        webSetting.setDefaultZoom(zoomDensity);

        webView.addJavascriptInterface(new JavaScriptInterface(this), "android");

    }



    public class JavaScriptInterface {
        Activity mActivity;

        JavaScriptInterface(Activity mActivity) {
            this.mActivity = mActivity;
        }

        /**
         * 与js交互时用到的方法，在js里直接调用的
         */
        @JavascriptInterface
        public void startActivity(int activityName) {
            Intent intent = new Intent();
//            intent.putExtra("fromWhich", "webViewUrl");
//            intent.putExtra("replyID", "replyID");
            switch (activityName) {
                case 1:
                    intent.setClass(mActivity, TestActivity.class);
                    break;
                case 2:
                    intent.setClass(mActivity, Item2Activity.class);
                    break;
                case 3:
                    intent.setClass(mActivity, Market.class);
                    break;
                case 4:
                    intent.setClass(mActivity, Production.class);
                    break;
                    case 5:
                   intent.setClass(mActivity, Problem.class);  //
                    break;
                case 6:
                    intent.setClass(mActivity, Item6Activity.class);
                    break;
                case 7:
                    intent.setClass(mActivity, Item7Activity.class);
                    break;
                case 8:
                    intent.setClass(mActivity, Item8.class);
                    break;
                case 9:
                    intent.setClass(mActivity, TestActivity.class);
                    break;
                case 10:
                    intent.setClass(mActivity, Main2Activity.class);
                    break;
                case 11:
                    intent.setClass(mActivity, Main3Activity.class);
                    break;
                case 12:
                    intent.setClass(mActivity, Main4Activity.class);
                    break;
                case 13:
                    intent.setClass(mActivity, Problem.class);
                    break;
                case 14:
                    intent.setClass(mActivity, Main6Activity.class);
                    break;
                case 15:
                    intent.setClass(mActivity, MC8Activity.class);
                    break;
                case 16:
                    intent.setClass(mActivity, Main8Activity.class);
                    break;
                case 17:
                    intent.setClass(mActivity, Item7Activity.class);
                    break;
                case 18:
                    intent.setClass(mActivity, Item2Activity.class);
                    break;
                case 19:
                    intent.setClass(mActivity, TestActivity.class);
                    break;
                case 20:
                    intent.setClass(mActivity, Item8.class);
                    break;
                case 21:
                    intent.setClass(mActivity, TestActivity.class);
                    break;
                case 22:
                    intent.setClass(mActivity, Main8Activity.class);
                    break;
                case 23:
                    intent.setClass(mActivity, TestActivity.class);
                    break;
                case 24:
                    intent.setClass(mActivity, TestActivity.class);
                    break;
                default:
                    break;
            }
            mActivity.startActivity(intent);


        }
    }

}
