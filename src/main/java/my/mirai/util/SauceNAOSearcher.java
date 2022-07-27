package my.mirai.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SauceNAOSearcher {
    public static String search(String url, int timeout) throws IOException {
        StringBuilder sb = new StringBuilder("https://saucenao.com/search.php?");
        sb.append("api_key=你的apikey&db=999&output_type=2&numres=3&url=");
        sb.append(URLEncoder.encode(url, "UTF-8"));
        URL u = new URL(sb.toString());
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
                sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                c.disconnect();

                return sb.toString();
        }
        return "错误！愤怒！愤怒！";
    }
}