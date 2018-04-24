package pds.ufrn.com.br.agendadoaluno.request;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class GenericRequest {

    private String host = "http://192.168.0.24:8080";
    protected String url = "";
    private String fullAddress = "";
    protected String methodType = "GET";
    protected String requestContent = "";
    protected String xApiKey = "EGaxX1G86pwPBmmyVtACA23P02uS8ZXJXIX5FWnG";
    protected Map<String, String> properties = new HashMap<String, String>();

    public String objectRequest(){
        String strJson = "";
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(getFullAddress());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(methodType);
            urlConnection.setRequestProperty("Content-Type", "application/json");


            if (properties != null && properties.size() > 0) {
                for (Entry<String, String> propriedadesEntry : properties.entrySet()) {
                    urlConnection.setRequestProperty(propriedadesEntry.getKey(), propriedadesEntry.getValue());
                }
            }

            if(requestContent != null && !requestContent.equals("")) {
                urlConnection.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));
                writer.write(requestContent);
                writer.close();
                wr.close();
                requestContent = null;
            }

            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String linha;
            StringBuffer buffer = new StringBuffer();
            while((linha = reader.readLine()) != null) {
                buffer.append(linha);
                buffer.append("\n");
            }
            strJson = buffer.toString();
            if(strJson != null && strJson.length() > 0){
                return strJson;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            return null;
        }
    }

    public String arrayRequest(){
        String strJson = "";
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(getFullAddress());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(methodType);
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String linha;
            StringBuffer buffer = new StringBuffer();
            while((linha = reader.readLine()) != null) {
                buffer.append(linha);
                buffer.append("\n");
            }
            strJson = buffer.toString();
            if(strJson != null && strJson.length() > 0){
                return strJson;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            return null;
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }


    public String getFullAddress() {
        fullAddress = getHost() + getUrl();
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

}
