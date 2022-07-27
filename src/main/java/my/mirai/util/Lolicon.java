package my.mirai.util;


import com.google.gson.Gson;
import my.mirai.pojo.LoliconResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Lolicon {
    public static LoliconResponse getJSON(String url, int timeout) throws IOException {
        //URL u = new URL(URLEncoder.encode(url, StandardCharsets.UTF_8));
        URL u = new URL(url);
        HttpURLConnection c = (HttpURLConnection) u.openConnection();
        c.setRequestMethod("GET");
        c.setUseCaches(false);
        c.setAllowUserInteraction(false);
        c.setConnectTimeout(timeout);
        c.setReadTimeout(timeout);
        c.connect();

        int status = c.getResponseCode();

        switch (status) {
            case 200:
            case 201:
                BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                Gson gson = new Gson();
                LoliconResponse lr = new LoliconResponse();
                lr = gson.fromJson(sb.toString(), LoliconResponse.class);

                return lr;
        }

        return null;
    }
}
