package me.skygrid_gen;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Generator extends ChunkGenerator {

    private final Block[] odds = new Block[] {
            new Block("GRASS_BLOCK", 50, 95),
            new Block("STONE", 255, 80),
            new Block("COBBLESTONE", 255, 60),
            new Block("ANDESITE", 255, 50),
    };

    @Override
    public @NotNull List<BlockPopulator> getDefaultPopulators(@NotNull World world) {
        return List.of(new SpawnersPopulator());
    }

    @Override
    public void generateSurface(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkData chunkData) {
        int len = Material.values().length;

        for (int y = 0; y < 255; y += 4) {
            for (int x = 0; x < 16; x += 4) {
                for (int z = 0; z < 16; z += 4) {
                    Material mat = Material.values()[random.nextInt(len)];

                    int odd = random.nextInt(100);
                    int _y = y;
                    Block[] blocks = Arrays.stream(this.odds).filter(a->odd<=a.spawn_chance && _y <= a.layer).toArray(Block[]::new);

                    if (blocks.length > 0) {
                        String chosen = blocks[random.nextInt(blocks.length)].name;
                        mat = Material.valueOf(chosen);
                    } else {
                        while (!mat.isBlock() || mat.isAir() || !mat.isCollidable()) {
                            mat = Material.values()[random.nextInt(len)];

                            if (mat.isBlock() && !mat.isAir() && mat.isCollidable())
                                break;
                        }
                    }

                    chunkData.setBlock(x, y, z, mat);
                }
            }
        }
    }

    @Override
    public boolean shouldGenerateCaves() {
        return false;
    }

    @Override
    public boolean shouldGenerateDecorations() {
        return false;
    }

    @Override
    public boolean shouldGenerateMobs() {
        return true;
    }

    @Override
    public boolean shouldGenerateNoise() {
        return false;
    }

    @Override
    public boolean shouldGenerateStructures() {
        return false;
    }

    @Override
    public boolean shouldGenerateSurface() {
        return true;
    }
}
