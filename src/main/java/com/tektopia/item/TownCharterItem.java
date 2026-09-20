package com.tektopia.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity; import net.minecraft.world.entity.npc.villager.Villager; import net.minecraft.world.entity.npc.villager.VillagerData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/** Right-click a villager to inspect it: profession, level, XP and health. */
public class TownCharterItem extends Item {

    public TownCharterItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player,
                                                  LivingEntity target, InteractionHand hand) {
        if (!(target instanceof Villager villager)) {
            return InteractionResult.PASS;
        }

        if (!player.level().isClientSide()) {
            VillagerData data = villager.getVillagerData();
            String profession = data.profession().getRegisteredName();

            Component message = Component.translatable(
                    "message.tektopia.villager_info",
                    profession,
                    data.level(),
                    villager.getVillagerXp(),
                    String.format("%.0f", villager.getHealth()),
                    String.format("%.0f", villager.getMaxHealth()));

            player.displayClientMessage(message, true);
        }
        return InteractionResult.SUCCESS;
    }
}
