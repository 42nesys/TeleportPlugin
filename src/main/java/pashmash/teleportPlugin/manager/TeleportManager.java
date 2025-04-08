package pashmash.teleportPlugin.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pashmash.teleportPlugin.TeleportPlugin;
import pashmash.teleportPlugin.util.ColorUtil;
import pashmash.teleportPlugin.util.TeleportType;
import pashmash.teleportPlugin.util.TeleportUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TeleportManager {
    private final TeleportPlugin plugin;
    private final Map<UUID, TeleportRequest> teleportRequests = new HashMap<>();

    public TeleportManager(TeleportPlugin plugin) {
        this.plugin = plugin;
    }

    public void sendTeleportRequest(Player sender, Player target, TeleportType type) {
        TeleportRequest request = new TeleportRequest(sender, target, type);
        teleportRequests.put(target.getUniqueId(), request);

        target.sendMessage(ColorUtil.translate(ColorUtil.PREFIX + sender.getName() + " has requested to teleport to you. Type /tpaccept to accept."));

        new BukkitRunnable() {
            @Override
            public void run() {
                teleportRequests.remove(target.getUniqueId());

            }
        }.runTaskLater(plugin, 1200);
    }

    public boolean acceptTeleportRequest(Player target) {
        TeleportRequest request = teleportRequests.remove(target.getUniqueId());
        if (request != null) {
            if (request.getType() == TeleportType.TPA) {
                TeleportUtil.teleport(request.getSender(), target.getLocation());
            } else if (request.getType() == TeleportType.TPAHERE) {
                TeleportUtil.teleport(target, request.getSender().getLocation());
            } else if (request.getType() == TeleportType.TPAALL) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!player.equals(request.getSender())) {
                        TeleportUtil.teleport(player, request.getSender().getLocation());
                    }
                }
            }
            target.sendMessage(ColorUtil.translate(ColorUtil.PREFIX +  "You have accepted the teleport request from " + request.getSender().getName()));
            return true;
        }
        return false;
    }
}