package pashmash.teleportPlugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pashmash.teleportPlugin.TeleportType;
import pashmash.teleportPlugin.manager.TeleportManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpaCommand implements CommandExecutor {
    private final TeleportManager teleportManager;

    public TpaCommand(TeleportManager teleportManager) {
        this.teleportManager = teleportManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {
                Player target = player.getServer().getPlayer(args[0]);
                if (target != null) {
                    teleportManager.sendTeleportRequest(player, target, TeleportType.TPA);
                    player.sendMessage("Teleport request sent to " + target.getName());
                } else {
                    player.sendMessage("Player not found.");
                }
            } else {
                player.sendMessage("Usage: /tpa <player>");
            }
        }
        return true;
    }
}