package com.ceetron.Redux.core.registry;

import com.ceetron.Redux.Redux;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ReduxCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Redux.MOD_ID);

    @SuppressWarnings("null")
    public static final RegistryObject<CreativeModeTab> REDUX_TAB = CREATIVE_MODE_TABS.register("redux_tab", 
        () -> CreativeModeTab.builder().icon(() -> new ItemStack(ReduxItems.FOSSIL.get()))
        .title(Component.translatable("creativetab.redux_tab"))
        .displayItems((pParameters, pOutput) -> {
            pOutput.accept(ReduxItems.FOSSIL.get());
        }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
    
}
