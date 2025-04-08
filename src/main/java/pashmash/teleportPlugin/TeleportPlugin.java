package pashmash.teleportPlugin;

import org.bukkit.plugin.java.JavaPlugin;
import pashmash.teleportPlugin.commands.TpaCommand;
import pashmash.teleportPlugin.commands.TpaallCommand;
import pashmash.teleportPlugin.commands.TpacceptCommand;
import pashmash.teleportPlugin.commands.TpahereCommand;
import pashmash.teleportPlugin.manager.TeleportManager;

public final class TeleportPlugin extends JavaPlugin {
    private TeleportManager teleportManager;

    @Override
    public void onEnable() {
        teleportManager = new TeleportManager(this);

        getCommand("tpa").setExecutor(new TpaCommand(teleportManager));
        getCommand("tpahere").setExecutor(new TpahereCommand(teleportManager));
        getCommand("tpaall").setExecutor(new TpaallCommand(teleportManager));
        getCommand("tpaccept").setExecutor(new TpacceptCommand(teleportManager));
    }

}
