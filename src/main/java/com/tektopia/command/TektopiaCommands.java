package com.tektopia.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.npc.Villager;

import java.util.List;

/** /tektopia villagers [radius]  - counts villagers around the player. */
public final class TektopiaCommands {
    private TektopiaCommands() {}

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("tektopia")
                .then(Commands.literal("villagers")
                        .executes(ctx -> countVillagers(ctx.getSource(), 32))
                        .then(Commands.argument("radius", IntegerArgumentType.integer(1, 128))
                                .executes(ctx -> countVillagers(
                                        ctx.getSource(),
                                        IntegerArgumentType.getInteger(ctx, "radius"))))));
    }

    private static int countVillagers(CommandSourceStack source, int radius) {
        ServerPlayer player = source.getPlayer();
        if (player == null) {
            source.sendFailure(Component.translatable("message.tektopia.player_only"));
            return 0;
        }

        List<Villager> villagers = player.level().getEntitiesOfClass(
                Villager.class, player.getBoundingBox().inflate(radius));

        int count = villagers.size();
        source.sendSuccess(
                () -> Component.translatable("message.tektopia.villager_count", count, radius),
                false);
        return count;
    }
}
