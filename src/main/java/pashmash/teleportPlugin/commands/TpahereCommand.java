package pashmash.teleportPlugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pashmash.teleportPlugin.util.ColorUtil;
import pashmash.teleportPlugin.util.TeleportType;
import pashmash.teleportPlugin.manager.TeleportManager;

public class TpahereCommand implements CommandExecutor {
    private final TeleportManager teleportManager;

    public TpahereCommand(TeleportManager teleportManager) {
        this.teleportManager = teleportManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {
                Player target = player.getServer().getPlayer(args[0]);
                if (target != null) {
                    teleportManager.sendTeleportRequest(player, target, TeleportType.TPAHERE);
                    player.sendMessage(ColorUtil.PREFIX + "Teleport request sent to " + target.getName());
                } else {
                    player.sendMessage(ColorUtil.PREFIX + "That player was not found");
                }
            } else {
                player.sendMessage(ColorUtil.PREFIX + "Usage: /tpahere <player>");
            }
        }
        return true;
    }
}