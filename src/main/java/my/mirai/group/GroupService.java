package my.mirai.group;

import my.mirai.pojo.LoliconResponse;
import my.mirai.util.Lolicon;
import my.mirai.util.SauceNAOSearcher;
import my.mirai.util.Translator;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.events.BotEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.*;
import net.mamoe.mirai.utils.ExternalResource;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.regex.Pattern;

public class GroupService {
    private final Bot bot;
    private final long groupID;
    private String lastImg;
    private final ExecutorService xct;

    public GroupService(Bot bot, long groupID, ExecutorService xct){
        this.bot = bot;
        this.groupID = groupID;
        this.xct = xct;
        EventChannel<BotEvent> ech = bot.getEventChannel();

        ech.subscribeAlways(GroupMessageEvent.class, (event) -> {
            if(event.getGroup().getId()!=groupID) return;
            if(event.getSender().getId()==botQQNumber) return;

            Image image = (Image) event.getMessage().stream().filter(Image.class::isInstance).findFirst().orElse(null);
            if(image!=null){
                lastImg = Image.queryUrl(image);
                return;
            }

            String s = event.getMessage().contentToString();
            if(!Pattern.matches("@"+"bot's qq number here"+".*", s)) return;

            service(event);
        });
    }
    public void service(GroupMessageEvent event){
        QuoteReply quote = event.getMessage().get(QuoteReply.Key);

        if(quote!=null){
            MessageChain mc = quote.getSource().getOriginalMessage();
            event.getGroup().sendMessage(mc.get(0));
        }else{
            String s = event.getMessage().contentToString().substring(11);
            if(Pattern.matches(".*图", s) || Pattern.matches(".*tu", s)){
                Runnable r = sendGroupImageByLoliconUrl(s, event.getSubject());
                xct.submit(r);
                return;
            }else if(Pattern.matches(".*英雄.*", s)){
                try {
                    s = SauceNAOSearcher.search(lastImg, 5000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            MessageChainBuilder msgb = new MessageChainBuilder().append(new At(event.getSender().getId())).append(" ");
            Message msg = msgb.append(s).build();
            event.getGroup().sendMessage(msg);
        }
    }
    public static Runnable sendGroupImageByLoliconUrl(String s, Group group){
        return() ->{
            HttpURLConnection c = null;
            try{
                try {
                    LoliconResponse lr = Lolicon.getJSON(Translator.translateToURL(s), 5000);
                    if(lr==null || lr.getData()==null){
                        group.sendMessage("得到了不合法的Lolicon api返回\n快叫叫叫主人！！！");
                    }else if(lr.getData().length==0){
                        group.sendMessage("得到了空的返回数据\n赣！你的xp好几把怪哦！");
                    }else{
                        String result = lr.getData()[0].getUrls().get("original");
                        if (result != null) {
                            URL u = new URL(result);
                            c = (HttpURLConnection) u.openConnection();
                            c.connect();
                            byte[] bytes = c.getInputStream().readAllBytes();
                            Image image = group.uploadImage(ExternalResource.create(bytes));
                            group.sendMessage(image);
                            group.sendMessage(result);
                        } else {
                            group.sendMessage("此结果没有original分辨率\n而有些懒狗还没有写其他分辨率的读取");
                        }
                    }
                } finally {
                    if (c != null) {
                        c.disconnect();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }
}
