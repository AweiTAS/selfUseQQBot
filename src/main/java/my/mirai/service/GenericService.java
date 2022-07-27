package my.mirai.service;

import my.mirai.admin.AdminService;
import my.mirai.group.GroupService;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.events.BotEvent;
import net.mamoe.mirai.event.events.FriendMessageEvent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GenericService {
    private final Bot bot;
    public GenericService(Bot bot){
        this.bot = bot;
    }
    public void run(){

        ExecutorService xct = Executors.newCachedThreadPool();


        EventChannel<BotEvent> ech = bot.getEventChannel();
        GroupService gs = new GroupService(bot, groupID, xct);

        ech.subscribeAlways(FriendMessageEvent.class, (event) -> {
            if (event.getSender().getId() == adminQQNumber) {
                AdminService.service(event);
            }
        });
    }
}
