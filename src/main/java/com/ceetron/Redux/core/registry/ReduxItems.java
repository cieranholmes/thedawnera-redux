package com.ceetron.Redux.core.registry;

import com.ceetron.Redux.Redux;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ReduxItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Redux.MOD_ID);

    public static final RegistryObject<Item> FOSSIL_ITEM = ITEMS.register("fossil_item", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DNA_TYRANNOSAURUS = ITEMS.register("dna_tyrannosaurus", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DNA_TRICERATOPS = ITEMS.register("dna_triceratops", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DNA_STRUTHIOMIMUS = ITEMS.register("dna_struthiomimus", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DNA_MIXOSAURUS = ITEMS.register("dna_mixosaurus", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DNA_PSITTACOSAURUS = ITEMS.register("dna_psittacosaurus", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DNA_DIABLOCERATOPS = ITEMS.register("dna_diabloceratops", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DNA_DIMORPHODON = ITEMS.register("dna_dimorphodon", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DNA_CERATOSAURUS = ITEMS.register("dna_ceratosaurus", () -> new Item(new Item.Properties())); 
    public static final RegistryObject<Item> DNA_DUNKLEOSTEUS = ITEMS.register("dna_dunkleosteus", () -> new Item(new Item.Properties())); 

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
    
}
