package me.skygrid_gen;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import me.skygrid_gen.Generator;

public final class Skygrid_gen extends JavaPlugin {

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new Generator();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Deu");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
