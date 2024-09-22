package me.skygrid_gen;

import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.LimitedRegion;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class SpawnersPopulator extends BlockPopulator {

    private static final EntityType[] whitelist = new EntityType[] {
            EntityType.ARMADILLO, EntityType.AXOLOTL, EntityType.BAT, EntityType.BEE,
            EntityType.BLAZE, EntityType.BOGGED, EntityType.BREEZE, EntityType.CAMEL, EntityType.CAT,
            EntityType.CAVE_SPIDER, EntityType.CHICKEN, EntityType.COW, EntityType.CREEPER, EntityType.DOLPHIN,
            EntityType.DONKEY, EntityType.DROWNED, EntityType.ELDER_GUARDIAN, EntityType.ENDERMAN, EntityType.ENDERMITE,
            EntityType.EVOKER, EntityType.FOX, EntityType.FROG, EntityType.GHAST, EntityType.GIANT, EntityType.GLOW_SQUID,
            EntityType.GOAT, EntityType.GUARDIAN, EntityType.HOGLIN, EntityType.HORSE, EntityType.HUSK, EntityType.ILLUSIONER,
            EntityType.IRON_GOLEM, EntityType.LLAMA, EntityType.MAGMA_CUBE, EntityType.MULE, EntityType.OCELOT, EntityType.PANDA,
            EntityType.PARROT, EntityType.PHANTOM, EntityType.PIG, EntityType.PILLAGER, EntityType.POLAR_BEAR, EntityType.PUFFERFISH,
            EntityType.RABBIT, EntityType.SHEEP, EntityType.SILVERFISH, EntityType.SKELETON, EntityType.SKELETON_HORSE, EntityType.SLIME,
            EntityType.SNOW_GOLEM, EntityType.SPIDER, EntityType.SQUID, EntityType.STRAY, EntityType.STRIDER, EntityType.TURTLE,
            EntityType.VEX, EntityType.VILLAGER, EntityType.VINDICATOR, EntityType.WANDERING_TRADER, EntityType.WARDEN, EntityType.WITCH,
            EntityType.WITHER_SKELETON, EntityType.WOLF, EntityType.ZOGLIN, EntityType.ZOMBIE, EntityType.ZOMBIE_HORSE, EntityType.ZOMBIE_VILLAGER,
            EntityType.ZOMBIFIED_PIGLIN
    };

    @Override
    public void populate(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull LimitedRegion limitedRegion) {
        int chunk_pos_x = chunkX*16;
        int chunk_pos_z = chunkZ*16;

        for (int y = 0; y < 255; y+=4) {
            for (int x = chunk_pos_x; x < chunk_pos_x+16; x+=4) {
                for (int z = chunk_pos_z; z < chunk_pos_z+16; z+=4) {

                    if (!limitedRegion.isInRegion(x,y,z))
                        continue;

                    Material startMaterial = limitedRegion.getType(x,y,z);

                    if (startMaterial == Material.SPAWNER) {
                        CreatureSpawner cs = (CreatureSpawner) limitedRegion.getBlockState(x,y,z);
                        cs.setSpawnedType(whitelist[random.nextInt(whitelist.length)]);

                        cs.update(false,false);
                    }

                }
            }
        }
    }
}
