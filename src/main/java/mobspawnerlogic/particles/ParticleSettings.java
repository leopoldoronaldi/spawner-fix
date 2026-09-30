package mobspawnerlogic.particles;

import net.minecraft.server.level.ServerPlayer;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class ParticleSettings {
    
    private static final Map<UUID, Boolean> playerSettings = new ConcurrentHashMap<>();
    
    private static final boolean DEFAULT_ENABLED = true;

    private ParticleSettings() {
    }

    public static boolean shouldShowParticles(UUID playerUuid) {
        return playerSettings.getOrDefault(playerUuid, DEFAULT_ENABLED);
    }

    public static boolean toggleParticles(ServerPlayer player) {
        UUID uuid = player.getUUID();
        boolean current = playerSettings.getOrDefault(uuid, DEFAULT_ENABLED);
        boolean newState = !current;
        
        if (newState == DEFAULT_ENABLED) {
            playerSettings.remove(uuid);
        } else {
            playerSettings.put(uuid, newState);
        }
        
        return newState;
    }

    public static void setParticles(ServerPlayer player, boolean enabled) {
        UUID uuid = player.getUUID();
        if (enabled == DEFAULT_ENABLED) {
            playerSettings.remove(uuid);
        } else {
            playerSettings.put(uuid, enabled);
        }
    }

    public static boolean getParticles(ServerPlayer player) {
        return shouldShowParticles(player.getUUID());
    }

    public static void clearPlayer(UUID playerUuid) {
        playerSettings.remove(playerUuid);
    }

    public static void updateFromServer(UUID playerUuid, boolean enabled) {
        if (enabled == DEFAULT_ENABLED) {
            playerSettings.remove(playerUuid);
        } else {
            playerSettings.put(playerUuid, enabled);
        }
    }
}
