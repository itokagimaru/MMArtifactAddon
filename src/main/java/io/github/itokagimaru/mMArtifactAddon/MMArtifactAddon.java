package io.github.itokagimaru.mMArtifactAddon;

import io.lumine.mythic.bukkit.MythicBukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class MMArtifactAddon extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("ArtifactMMAddon enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
