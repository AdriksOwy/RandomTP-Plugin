package org.apache.maven.plugins;

import org.apache.maven.plugins.Events.SpecialButton;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class RandomTP extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getLogger().log(Level.INFO, "Plugin is enabled");
        getServer().getPluginManager().registerEvents(new SpecialButton(), this);
    }

    @Override
    public void onDisable() {
        this.getLogger().log(Level.INFO, "Plugin is disabled");
    }
}
