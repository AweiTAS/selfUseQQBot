package my.mirai.admin;

import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.QuoteReply;

public class AdminService {

    public static void service(FriendMessageEvent event){
        quoteReply(event);
    }

    public static void quoteReply(FriendMessageEvent event){
        event.getSubject().sendMessage(new MessageChainBuilder()
                .append(new QuoteReply(event.getMessage()))
                .append("Hi, you just said: '")
                .append(event.getMessage())
                .append("'")
                .build()
        );
    }

}
