package pashmash.teleportPlugin.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pashmash.teleportPlugin.TeleportPlugin;
import pashmash.teleportPlugin.TeleportType;

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

        target.sendMessage("You have received a teleport request from " + sender.getName());

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
                request.getSender().teleport(target);
            } else if (request.getType() == TeleportType.TPAHERE) {
                target.teleport(request.getSender());
            } else if (request.getType() == TeleportType.TPAALL) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!player.equals(request.getSender())) {
                        player.teleport(request.getSender());
                    }
                }
            }
            target.sendMessage("You have accepted the teleport request from " + request.getSender().getName());
            return true;
        }
        return false;
    }
}