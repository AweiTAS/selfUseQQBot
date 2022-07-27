package my.mirai;

import my.mirai.service.GenericService;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;
import java.io.File;

public class JavaMain {
    public static void main(String[] args) {
        // using costume config
        Bot bot = BotFactory.INSTANCE.newBot(botQQNumber, "password", new BotConfiguration() {{
            fileBasedDeviceInfo(); // 使用 device.json 存储设备信息
            setProtocol(MiraiProtocol.ANDROID_PHONE); // 切换协议
            setWorkingDir(new File("D:/workspace/mirai"));
        }});

        bot.login();

        GenericService gs = new GenericService(bot);
        gs.run();
    }
}
