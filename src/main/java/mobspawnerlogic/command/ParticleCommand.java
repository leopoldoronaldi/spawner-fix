package mobspawnerlogic.command;

import mobspawnerlogic.network.ParticleSettingsSync;
import mobspawnerlogic.particles.ParticleSettings;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import static net.minecraft.commands.Commands.literal;

public final class ParticleCommand {

    private ParticleCommand() {
    }

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, commandSelection, registrationEnvironment) -> {
            dispatcher.register(literal("particles")
                .executes(ParticleCommand::showStatus)
                .then(literal("toggle")
                    .executes(ParticleCommand::toggleParticles)
                )
                .then(literal("on")
                    .executes(context -> setParticles(context, true))
                )
                .then(literal("off")
                    .executes(context -> setParticles(context, false))
                )
            );
        });
    }

    private static int showStatus(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer player = getPlayer(context);
        boolean enabled = ParticleSettings.getParticles(player);
        
        Component message = Component.translatable(
            enabled ? "command.spawner-fix.particles.enabled" : "command.spawner-fix.particles.disabled"
        );
        player.sendSystemMessage(message);
        
        return Command.SINGLE_SUCCESS;
    }

    private static int toggleParticles(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer player = getPlayer(context);
        boolean newState = ParticleSettings.toggleParticles(player);
        
        ParticleSettingsSync.sendToPlayer(player);
        
        Component message = Component.translatable(
            newState ? "command.spawner-fix.particles.toggled.on" : "command.spawner-fix.particles.toggled.off"
        );
        player.sendSystemMessage(message);
        
        return Command.SINGLE_SUCCESS;
    }

    private static int setParticles(CommandContext<CommandSourceStack> context, boolean enabled) throws CommandSyntaxException {
        ServerPlayer player = getPlayer(context);
        ParticleSettings.setParticles(player, enabled);
        
        ParticleSettingsSync.sendToPlayer(player);
        
        Component message = Component.translatable(
            enabled ? "command.spawner-fix.particles.enabled" : "command.spawner-fix.particles.disabled"
        );
        player.sendSystemMessage(message);
        
        return Command.SINGLE_SUCCESS;
    }

    private static ServerPlayer getPlayer(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        return context.getSource().getPlayerOrException();
    }
}
