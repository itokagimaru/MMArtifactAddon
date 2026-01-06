package io.github.itokagimaru.mMArtifactAddon;

import io.github.itokagimaru.mMArtifactAddon.newSkill.SetPdc;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.bukkit.events.MythicMechanicLoadEvent;
import io.lumine.mythic.core.skills.SkillExecutor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MMArtifactAddon extends JavaPlugin implements Listener {
    public static SkillExecutor skillExecutor;
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        skillExecutor = MythicBukkit.inst().getSkillManager();
        if (skillExecutor == null){
            getLogger().warning("skillExecutor is null");
        } else {
            getLogger().info("ArtifactMMAddon enabled");
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onMechanicLoad(MythicMechanicLoadEvent e) {
        getLogger().info("artifact_pdc_set!!");
        if (!e.getMechanicName().equals("artifact_pdc_set")) {

            e.register(new SetPdc(
                    skillExecutor,
                    e.getContainer().getFile(),
                    e.getConfig().getLine(),
                    e.getConfig()
            ));
        }
    }
}
