package pashmash.teleportPlugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pashmash.teleportPlugin.manager.TeleportManager;
import pashmash.teleportPlugin.util.ColorUtil;

public class TpacceptCommand implements CommandExecutor {
    private final TeleportManager teleportManager;

    public TpacceptCommand(TeleportManager teleportManager) {
        this.teleportManager = teleportManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                if (!teleportManager.acceptTeleportRequest(player)) {
                    player.sendMessage(ColorUtil.PREFIX + "No teleport request found");
                } else teleportManager.acceptTeleportRequest(player);
            } else if (args.length == 1) {
                Player target = player.getServer().getPlayer(args[0]);
                if (target != null && teleportManager.acceptTeleportRequest(target)) {
                    player.sendMessage(ColorUtil.PREFIX + "Accepted the teleport request from " + target.getName());
                } else {
                    player.sendMessage(ColorUtil.PREFIX + "No teleport request found from " + args[0]);
                }
            } else {
                player.sendMessage(ColorUtil.PREFIX + "Usage: /tpaccept <player>");
            }
        }
        return true;
    }
}