package com.tektopia;

import com.tektopia.command.TektopiaCommands;
import com.tektopia.item.TownCharterItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tektopia implements ModInitializer {
    public static final String MOD_ID = "tektopia";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static Item TOWN_CHARTER;

    @Override
    public void onInitialize() {
        registerItems();
        CommandRegistrationCallback.EVENT.register(
                (dispatcher, registryAccess, environment) -> TektopiaCommands.register(dispatcher));
        LOGGER.info("Tektopia {} loaded", MOD_ID);
    }

    private static void registerItems() {
        ResourceKey<Item> key = ResourceKey.create(
                Registries.ITEM, Identifier.fromNamespaceAndPath(MOD_ID, "town_charter"));

        TOWN_CHARTER = Registry.register(BuiltInRegistries.ITEM, key,
                new TownCharterItem(new Item.Properties().setId(key).stacksTo(1)));

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register(entries -> entries.accept(TOWN_CHARTER));
    }
}
