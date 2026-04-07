package com.maximized.more_apples;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MoreApples.MODID)
public class MoreApples
{
    public static final String MODID = "more_apples";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // Effects:
    //
    // Speed: MOVEMENT_SPEED
    // Haste: DIG_SPEED
    // Strength: DAMAGE_BOOST
    // Instant Health: HEAL
    // Jump Boost: JUMP
    // Regeneration: REGENERATION
    // Resistance: DAMAGE_RESISTANCE
    // Fire Resistance: FIRE_RESISTANCE
    // Water Breathing: WATER_BREATHING
    // Invisibility: INVISIBILITY
    // Night Vision: NIGHT_VISION
    // Health Boost: HEALTH_BOOST
    // Absorption: ABSORPTION
    // Saturation: SATURATION
    // Luck: LUCK
    // Slow Falling: SLOW_FALLING
    // Conduit Power: CONDUIT_POWER
    // Dolphin's Grace: DOLPHINS_GRACE
    // Hero of the Village: HERO_OF_THE_VILLAGE
    //
    // .effect(() -> new MobEffectInstance(MobEffects.EFFECT, secondsToTicks(duration), amplifier), chance)

    // Creates apple items with different materials
    public static final RegistryObject<Item> COAL_APPLE = ITEMS.register("coal_apple", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEat()
            .nutrition(3)
            .saturationMod(1f)
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, minutesToTicks(3), 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, minutesToTicks(2), 0), 1.0f)
            .build())));
    public static final RegistryObject<Item> IRON_APPLE = ITEMS.register("iron_apple", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEat()
            .nutrition(4)
            .saturationMod(1.1f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, minutesToTicks(3), 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, minutesToTicks(2), 0), 1.0f)
            .build())));
    public static final RegistryObject<Item> DIAMOND_APPLE = ITEMS.register("diamond_apple", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEat()
            .nutrition(6)
            .saturationMod(1.3f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, secondsToTicks(10), 1), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, minutesToTicks(2), 1), 1.0f)
            .build())));
    public static final RegistryObject<Item> EMERALD_APPLE = ITEMS.register("emerald_apple", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEat()
            .nutrition(8)
            .saturationMod(1.5f)
            .effect(() -> new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, minutesToTicks(3), 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.LUCK, minutesToTicks(5), 1), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, secondsToTicks(45), 1), 1.0f)
            .build())));
    public static final RegistryObject<Item> NETHERITE_APPLE = ITEMS.register("netherite_apple", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEat()
            .nutrition(10)
            .saturationMod(2f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, secondsToTicks(10), 2), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, minutesToTicks(4), 2), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, minutesToTicks(3), 1), 1.0f)
            .build())));
    public static final RegistryObject<Item> QUARTZ_APPLE = ITEMS.register("quartz_apple", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEat()
            .nutrition(4)
            .saturationMod(1.2f)
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, minutesToTicks(3), 0), 1.0f)
            .build())));

    // Creates a creative tab with the id "examplemod:apples_tab" for the apples, that is placed after the food tab
    public static final RegistryObject<CreativeModeTab> APPLES_TAB = CREATIVE_MODE_TABS.register("apples_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.more_apples.apples_tab"))
            .withTabsBefore(CreativeModeTabs.FOOD_AND_DRINKS)
            .icon(() -> DIAMOND_APPLE.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(COAL_APPLE.get());
                output.accept(IRON_APPLE.get());
                output.accept(DIAMOND_APPLE.get());
                output.accept(EMERALD_APPLE.get());
                output.accept(NETHERITE_APPLE.get());
                output.accept(QUARTZ_APPLE.get());
            }).build());

    public MoreApples(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        // context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("Hello from More Apples!");
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        // nothing to do
    }

    // Helper functions

    private static int minutesToTicks(int x) {
        return x * 60 * 20;
    }

    private static int secondsToTicks(int x) {
        return x * 20;
    }
}
