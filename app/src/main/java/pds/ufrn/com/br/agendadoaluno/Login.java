package pds.ufrn.com.br.agendadoaluno;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import  pds.ufrn.com.br.agendadoaluno.Constants;

import ca.mimic.oauth2library.OAuth2Client;
import ca.mimic.oauth2library.OAuthResponse;


public class Login extends AppCompatActivity {

    private static final String CLIENT_ID_VALUE = "agenda-do-aluno-id";
    private static final String SECRET_KEY = "segredo";

    private static final String REDIRECT_URI = "https://api.ufrn.br";
    private static final String AUTHORIZATION_URL = "http://apitestes.info.ufrn.br/authz-server/oauth/authorize";
    private static final String ACCESS_TOKEN_URL = "http://apitestes.info.ufrn.br/authz-server/oauth/token";
    private static final String RESPONSE_TYPE_PARAM = "response_type";
    private static final String GRANT_TYPE = "authorization_code";
    private static final String RESPONSE_TYPE_VALUE = "code";
    private static final String CLIENT_ID_PARAM = "client_id";
    private static final String REDIRECT_URI_PARAM = "redirect_uri";

    private static final String QUESTION_MARK = "?";
    private static final String AMPERSAND = "&";
    private static final String EQUALS = "=";

    private static final String KEY_ACCESS_TOKEN = "ACCESS_TOKEN";
    private static final String KEY_REFRESH_TOKEN = "REFRESH_TOKEN";
    private static final String KEY_EXPIRES_IN = "EXPIRES_IN";
    private static final String KEY_TOKEN_TYPE = "TOKEN_TYPE";
    private static final String KEY_USER_INFO = "USER_INFO";

    private WebView webView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        webView = (WebView) findViewById(R.id.login_activity_web_view);
        webView.requestFocus(View.FOCUS_DOWN);

        progressDialog = ProgressDialog.show(this, "", "Loading", true);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                if(progressDialog != null && progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String authorizationUrl) {
                if(authorizationUrl.startsWith(REDIRECT_URI)){
                    Uri uri = Uri.parse(authorizationUrl);

                    String authorizationToken = uri.getQueryParameter(RESPONSE_TYPE_VALUE);
                    if(authorizationToken == null){
                        return true;
                    }

                    new PostRequestAsyncTask().execute(authorizationToken);
                }else{
                    webView.loadUrl(authorizationUrl);
                }

                return true;
            }
        });

        String authUrl = getAuthorizationUrl();
        webView.loadUrl(authUrl);
    }

    private static String getAuthorizationUrl() {
        return AUTHORIZATION_URL
                + QUESTION_MARK + RESPONSE_TYPE_PARAM + EQUALS + RESPONSE_TYPE_VALUE
                + AMPERSAND + CLIENT_ID_PARAM + EQUALS + CLIENT_ID_VALUE
                + AMPERSAND + REDIRECT_URI_PARAM + EQUALS + REDIRECT_URI;
    }

    private class PostRequestAsyncTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            ProgressDialog.show(Login.this, "", "Loading", true);
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                OAuth2Client client;
                Map<String, String> map = new HashMap<>();
                map.put(REDIRECT_URI_PARAM, REDIRECT_URI);
                map.put(RESPONSE_TYPE_VALUE, strings[0]);

                client = new OAuth2Client.Builder(CLIENT_ID_VALUE, SECRET_KEY, ACCESS_TOKEN_URL)
                        .grantType(GRANT_TYPE)
                        .parameters(map)
                        .build();

                OAuthResponse response = client.requestAccessToken();
                if (response.isSuccessful()) {
                    savePreferences(response);
                    return true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean status) {
            if(progressDialog != null && progressDialog.isShowing()){
                progressDialog.dismiss();
            }

            if(status){
                Intent startOffersActivity = new Intent(Login.this, Calendar.class);
                Login.this.startActivity(startOffersActivity);
            }
        }
    }

//    private void savePreferences(OAuthResponse response) {
//        String accessToken = response.getAccessToken();
//        String refreshToken = response.getRefreshToken();
//        Long expiresIn = response.getExpiresIn();
//
//        SharedPreferences preferences = this.getSharedPreferences("user_info", 0);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString(Constants.KEY_ACCESS_TOKEN, accessToken);
//        editor.putString(Constants.KEY_REFRESH_TOKEN, refreshToken);
//        editor.putLong(Constants.KEY_EXPIRES_IN, expiresIn);
//        editor.commit();
//    }

    public void savePreferences(OAuthResponse response) {
        String accessToken = response.getAccessToken();
        String refreshToken = response.getRefreshToken();
        String tokenType = response.getTokenType();
        Long expiresIn = response.getExpiresIn();

        SharedPreferences preferences = this.getSharedPreferences(KEY_USER_INFO, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.putString(KEY_ACCESS_TOKEN, accessToken);
        editor.putString(KEY_REFRESH_TOKEN, refreshToken);
        editor.putString(KEY_TOKEN_TYPE, tokenType);
        editor.putLong(KEY_EXPIRES_IN, expiresIn);
        editor.commit();
    }
}
