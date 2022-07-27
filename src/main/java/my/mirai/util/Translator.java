package my.mirai.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Pattern;

public class Translator {
    public static String translateToURL(String s) throws UnsupportedEncodingException {
        StringBuilder url = new StringBuilder("https://api.lolicon.app/setu/v2");
        boolean firstModify = true;
        String[] possibleTags = s.split(" ");
        StringBuilder tags = new StringBuilder();
        for(String possibleTag:possibleTags){
            if(Pattern.matches(".*图", possibleTag) || Pattern.matches(".*tu", possibleTag)) continue;
            if(firstModify && (possibleTag.equals("r18") || possibleTag.equals("R18"))){
                url.append("?r18=1");
                firstModify = false;
                continue;
            }
            tags.append("&tag="+ URLEncoder.encode(possibleTag,"UTF-8"));
        }
        if(tags.length()==0){
            return url.toString();
        }
        if(firstModify){
            tags.setCharAt(0, '?');
        }

        return url.append(tags).toString();
    }
}
