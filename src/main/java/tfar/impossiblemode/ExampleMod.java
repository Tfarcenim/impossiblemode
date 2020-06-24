package tfar.impossiblemode;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.SoundType;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import org.apache.logging.log4j.Logger;

import static net.minecraft.util.DamageSource.LAVA;

@Mod(modid = ExampleMod.MODID, name = ExampleMod.NAME, version = ExampleMod.VERSION)
@Mod.EventBusSubscriber
public class ExampleMod {
    public static final String MODID = "impossiblemode";
    public static final String NAME = "Impossible Mode";
    public static final String VERSION = "1.0";

    public ExampleMod(){
        ForgeModContainer.zombieBabyChance = 1;
    }

    public static Block unlit_torch;
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    }

    @SubscribeEvent
    public static void block(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(unlit_torch = new TorchBlockExt().setSoundType(SoundType.WOOD)
                .setRegistryName("unlit_torch")
                .setTranslationKey("impossiblemode.unlit_torch")
                .setHardness(0.0F).setLightLevel(0));
    }

    @SubscribeEvent
    public static void item(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemBlock(unlit_torch)
                .setRegistryName("unlit_torch")
        .setTranslationKey("impossiblemode.unlit_torch")
        );
    }

    @SubscribeEvent
    public static void burnOutTorchesPart1(BlockEvent.EntityPlaceEvent e) {
        if (e.getPlacedBlock().getBlock() == Blocks.TORCH) {
            e.getWorld().scheduleUpdate(e.getPos(),Blocks.TORCH,400);
        }
    }

    @SubscribeEvent
    public static void multiplyLava(LivingDamageEvent e) {
        if (e.getSource() == LAVA) {
            e.setAmount(e.getAmount() * 10);
        }

        Entity attacker = e.getSource().getTrueSource();
        if (attacker instanceof EntitySilverfish) {
            for (int i = 0 ; i < 3; i++) {
                EntitySilverfish silverfish1 = new EntitySilverfish(e.getEntityLiving().world);
                silverfish1.setLocationAndAngles(attacker.getPosition().getX() + 0.5D,
                        attacker.getPosition().getY(),
                        attacker.getPosition().getZ() + 0.5D,
                        0.0F, 0.0F);
                e.getEntityLiving().world.spawnEntity(silverfish1);
            }
        }
    }

    @SubscribeEvent
    public static void noPigsOrBats(EntityJoinWorldEvent e){
        if (e.getEntity() instanceof EntityPig || e.getEntity() instanceof EntityBat) e.setCanceled(true);

        if (e.getEntity() instanceof EntityCreeper) {
            e.getEntity().onStruckByLightning(null);
        }
    }


    @SubscribeEvent
    public static void models(ModelRegistryEvent e) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(unlit_torch),
                0,new ModelResourceLocation(Item.getItemFromBlock(unlit_torch).getRegistryName(), "inventory"));
    }
}
