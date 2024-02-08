package com.ceetron.Redux.core.registry;

import java.util.function.Supplier;

import com.ceetron.Redux.Redux;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ReduxBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Redux.MOD_ID);

    public static final RegistryObject<Block> FOSSIL_ORE = registerBlock("fossil_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).sound(SoundType.STONE)));
    public static final RegistryObject<Block> DEEPSLATE_FOSSIL_ORE = registerBlock("deepslate_fossil_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).sound(SoundType.STONE)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ReduxItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
    
}
