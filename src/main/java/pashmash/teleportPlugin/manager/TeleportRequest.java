package pashmash.teleportPlugin.manager;

import org.bukkit.entity.Player;
import pashmash.teleportPlugin.util.TeleportType;

public class TeleportRequest {
    private final Player sender;
    private final Player target;
    private final TeleportType type;

    public TeleportRequest(Player sender, Player target, TeleportType type) {
        this.sender = sender;
        this.target = target;
        this.type = type;
    }

    public Player getSender() {
        return sender;
    }

    public Player getTarget() {
        return target;
    }

    public TeleportType getType() {
        return type;
    }
}