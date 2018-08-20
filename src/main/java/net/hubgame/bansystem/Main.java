package net.hubgame.bansystem;

import lombok.Getter;
import net.hubgame.bansystem.mysql.MySQLBans;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {

    public static String PREFIX = "§8»§e Ban §8×§7 ";
    public static String DISCONNECTPREFIX = "§7➤ §bHubGame.net ";

    @Getter
    private static Main instance;
    @Getter
    private MySQLBans mySQLBans;


    @Override
    public void onEnable() {
        instance = this;
        mySQLBans = new MySQLBans();
    }

    @Override
    public void onDisable() {

    }

}
