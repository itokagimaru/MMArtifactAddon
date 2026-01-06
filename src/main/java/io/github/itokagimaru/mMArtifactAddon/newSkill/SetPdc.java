package io.github.itokagimaru.mMArtifactAddon.newSkill;

import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.skills.INoTargetSkill;
import io.lumine.mythic.api.skills.SkillMetadata;
import io.lumine.mythic.api.skills.SkillResult;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.core.skills.SkillExecutor;
import io.lumine.mythic.core.skills.SkillMechanic;
import io.lumine.mythic.core.utils.annotations.MythicMechanic;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataType;

import java.io.File;

@MythicMechanic(
        name = "artifact_pdc_set",
        aliases = {"setpdc"}
)

public class SetPdc extends SkillMechanic implements INoTargetSkill {
    private final NamespacedKey key;
    private final String type;
    private final String rawValue;

    public SetPdc(
            SkillExecutor manager,
            File file,
            String line,
            MythicLineConfig mlc
    ) {
        super(manager, file, line, mlc);

        String rawKey = mlc.getString(new String[]{"key"}, "null");
        this.type = mlc.getString(new String[]{"type"}, "int");
        this.rawValue = mlc.getString(new String[]{"value"}, "0");

        this.key = parseKey(rawKey);
    }

    private NamespacedKey parseKey(String rawKey) {
        if (rawKey.contains(":")) {
            String[] split = rawKey.split(":", 2);
            return new NamespacedKey(split[0], split[1]);
        }
        return new NamespacedKey(
                "artifact",
                rawKey
        );
    }

    @Override
    public SkillResult cast(SkillMetadata data) {
        Entity entity = BukkitAdapter.adapt(data.getCaster().getEntity());

        switch (type.toLowerCase()) {
            case "int" -> entity.getPersistentDataContainer()
                    .set(key, PersistentDataType.INTEGER, Integer.parseInt(rawValue));

            case "double" -> entity.getPersistentDataContainer()
                    .set(key, PersistentDataType.DOUBLE, Double.parseDouble(rawValue));

            case "string" -> entity.getPersistentDataContainer()
                    .set(key, PersistentDataType.STRING, rawValue);

            default -> getManager().getPlugin().getLogger()
                    .warning("[ArtifactMM] Unknown PDC type: " + type);
        }
        return SkillResult.SUCCESS;
    }
}
