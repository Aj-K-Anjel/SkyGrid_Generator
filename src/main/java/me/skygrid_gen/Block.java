package me.skygrid_gen;

public class Block {
    public Integer layer;
    public String biome;
    public String name;
    public Integer spawn_chance;

    public Block(String name, Integer layer, Integer spawn_chance) {
        this.name = name;
        this.layer = layer;
        this.spawn_chance = spawn_chance;
    }
}
