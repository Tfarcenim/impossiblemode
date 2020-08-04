package tfar.impossiblemode;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

import static net.minecraft.util.DamageSource.LAVA;
import static net.minecraftforge.common.MinecraftForge.EVENT_BUS;

@Mod(ImpossibleMode.MODID)
@Mod.EventBusSubscriber
public class ImpossibleMode {
    public static final String MODID = "impossiblemode";
    public static final String NAME = "Impossible Mode";
    public static final String VERSION = "1.0";

    public ImpossibleMode(){
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        if (FMLEnvironment.dist == Dist.CLIENT) {
                EVENT_BUS.addListener(this::air);
        }
    }

    private void air(RenderGameOverlayEvent.Pre e) {
        if (e.getType() == RenderGameOverlayEvent.ElementType.AIR) {
            if (Minecraft.getInstance().player.getAir() >= Minecraft.getInstance().player.getMaxAir()) {
                e.setCanceled(true);
            }
        }
    }

    public static Block unlit_torch;

    @SubscribeEvent
    public static void block(RegistryEvent.Register<Block> event) {
        Block.Properties properties = Block.Properties.from(Blocks.TORCH).setLightLevel(state -> 0);
        event.getRegistry().register(unlit_torch = new TorchBlockExt(properties)
                .setRegistryName("unlit_torch"));
        ForgeConfig.SERVER.zombieBabyChance.set(1d);;
    }

    @SubscribeEvent
    public static void item(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new BlockItem(unlit_torch,new Item.Properties().group(ItemGroup.DECORATIONS))
                .setRegistryName("unlit_torch")
        );
    }

    @SubscribeEvent
    public static void burnOutTorchesPart1(BlockEvent.EntityPlaceEvent e) {
        if (e.getPlacedBlock().getBlock() == Blocks.TORCH) {
            e.getWorld().getPendingBlockTicks().scheduleTick(e.getPos(),Blocks.TORCH,400);
        }
    }

    @SubscribeEvent
    public static void multiplyLava(LivingDamageEvent e) {
        if (e.getSource() == LAVA) {
            e.setAmount(e.getAmount() * 10);
        }

        Entity attacker = e.getSource().getTrueSource();
        if (attacker instanceof SilverfishEntity) {
            for (int i = 0 ; i < 3; i++) {
                SilverfishEntity silverfish1 = EntityType.SILVERFISH.create(attacker.world);
                silverfish1.setLocationAndAngles(attacker.getPosition().getX() + 0.5D,
                        attacker.getPosition().getY(),
                        attacker.getPosition().getZ() + 0.5D,
                        0.0F, 0.0F);
                e.getEntityLiving().world.addEntity(silverfish1);
            }
        }
    }

    @SubscribeEvent
    public static void noPigsOrBats(EntityJoinWorldEvent e){
        if (e.getEntity() instanceof PigEntity || e.getEntity() instanceof BatEntity) e.setCanceled(true);

        if (e.getEntity() instanceof CreeperEntity) {
            e.getEntity().onStruckByLightning(null);
        }
    }

    @SubscribeEvent
    public static void everburn(LivingDamageEvent e) {
        if (e.getEntityLiving() instanceof PlayerEntity) {
            PlayerEntity victim = (PlayerEntity)e.getEntityLiving();
            if (victim.isBurning() || e.getSource().isFireDamage()) {
                victim.setFire(100);
            }
        }
    }
}
