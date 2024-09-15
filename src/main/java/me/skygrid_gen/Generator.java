package me.skygrid_gen;

import org.bukkit.Material;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Random;

public class Generator extends ChunkGenerator {

    private final HashMap<String, Integer> odds;

    public Generator() {
        this.odds = new HashMap<>();

        this.odds.put("GRASS_BLOCK", 90);
        this.odds.put("STONE", 80);
        this.odds.put("COBBLESTONE", 60);
        this.odds.put("ANDESITE", 50);
    }

    @Override
    public void generateSurface(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkData chunkData) {
        int len = Material.values().length;

        for (int y = 0; y < 255; y += 4) {
            for (int x = 0; x < 16; x += 4) {
                for (int z = 0; z < 16; z += 4) {
                    Material mat = Material.values()[random.nextInt(len)];

                    int odd = random.nextInt(100);
                    String[] blocks = this.odds.entrySet().stream().filter(a->odd<=a.getValue()).map(e->e.getKey()).toArray(String[]::new);

                    if (blocks.length > 0) {
                        String chosen = blocks[random.nextInt(blocks.length)];
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
        return false;
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
