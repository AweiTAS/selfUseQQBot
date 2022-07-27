package org.example.mirai;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.QuoteReply;
import net.mamoe.mirai.utils.BotConfiguration;

import java.io.File;

public class JavaMain {
    public static void main(String[] args) {
        // using costume config
        Bot bot = BotFactory.INSTANCE.newBot(179456559, "day0bot179", new BotConfiguration() {{
            fileBasedDeviceInfo(); // 使用 device.json 存储设备信息
            setProtocol(MiraiProtocol.ANDROID_PHONE); // 切换协议
        }});

        bot.login();

        JavaMain.afterLogin(bot);
    }

    public static void afterLogin(Bot bot) {
        long yourQQNumber = 811426275;
        bot.getEventChannel().subscribeAlways(FriendMessageEvent.class, (event) -> {
            if (event.getSender().getId() == yourQQNumber) {
                event.getSubject().sendMessage(new MessageChainBuilder()
                        .append(new QuoteReply(event.getMessage()))
                        .append("Hi, you just said: '")
                        .append(event.getMessage())
                        .append("'")
                        .build()
                );
            }
        });
    }
}
