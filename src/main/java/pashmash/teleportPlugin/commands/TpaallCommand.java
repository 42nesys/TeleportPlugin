package pashmash.teleportPlugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pashmash.teleportPlugin.util.ColorUtil;
import pashmash.teleportPlugin.util.TeleportType;
import pashmash.teleportPlugin.manager.TeleportManager;

public class TpaallCommand implements CommandExecutor {
    private final TeleportManager teleportManager;

    public TpaallCommand(TeleportManager teleportManager) {
        this.teleportManager = teleportManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            for (Player target : player.getServer().getOnlinePlayers()) {
                if (!target.equals(player)) {
                    teleportManager.sendTeleportRequest(player, target, TeleportType.TPAALL);
                }
            }
            player.sendMessage(ColorUtil.PREFIX + "Teleport request sent to all players");
        }
        return true;
    }
}