package net.hubgame.bansystem.commands;

import net.hubgame.bansystem.Main;
import net.hubgame.bansystem.utils.BanReason;
import net.hubgame.bansystem.utils.BungeeText;
import net.hubgame.bansystem.utils.UUIDFetcher;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.awt.font.NumericShaper;
import java.util.UUID;

public class BanCommand extends Command {

    public BanCommand() {
        super("ban");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if (!(commandSender instanceof ProxiedPlayer))
            return;
        ProxiedPlayer p = (ProxiedPlayer) commandSender;
        if (args.length != 2) {
            sendHelp(p);
            return;
        }
        String targetName = args[0];
        String idString = args[1];
        int banID;
        try {
            banID = Integer.parseInt(idString);
        } catch (NumberFormatException ex) {
            sendHelp(p);
            return;
        }
        BanReason banReason = BanReason.getBanReason(banID);
        if (banReason == null) {
            p.sendMessage(new BungeeText(Main.PREFIX + "Dieser Grund existiert nicht.").build());
            return;
        }
        UUID uuid = UUIDFetcher.getUUID(targetName);
        if (uuid == null) {
            p.sendMessage(new BungeeText(Main.PREFIX + "Der Spieler existiert nicht.").build());
            return;
        }
        ProxiedPlayer proxiedPlayer = ProxyServer.getInstance().getPlayer(uuid);
        if (proxiedPlayer != null) {
            Main.getInstance().getMySQLBans().addBan(uuid, proxiedPlayer.getAddress().getHostName(), banReason.getID(), System.currentTimeMillis());
            proxiedPlayer.disconnect(new BungeeText(Main.DISCONNECTPREFIX + "\n§cDu wurdest von Netzwerk gebannt! \n §bJoine erneut für mehr Details.").build());
        } else {
            Main.getInstance().getMySQLBans().addBan(uuid, "", banReason.getID(), System.currentTimeMillis());
        }
    }

    public void sendHelp(ProxiedPlayer p) {
        p.sendMessage("§cBenutze §7/ban <Spielername> <ID>");
        for (BanReason banReason : BanReason.values()) {
            p.sendMessage("ID: " + banReason.getID() + " §b" + banReason.getReason());
        }


    }
}
