package org.apache.maven.plugins.Events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.Directional;

import java.util.Collection;
import java.util.Random;

public class SpecialButton implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getClickedBlock().getType() == Material.STONE_BUTTON) {
                Block block = event.getClickedBlock();

                BlockFace blockFace = ((Directional) block.getState().getData()).getFacing();
                Block blockBehindButton = event.getClickedBlock().getRelative(blockFace.getOppositeFace());

                if(blockBehindButton.getType() == Material.SPONGE) {
                    double xCenter = (block.getX() + 0.5) + ((block.getX() - blockBehindButton.getX()) * 2);
                    double yCenter = block.getY() + ((block.getY() - blockBehindButton.getY()) * 2);
                    double zCenter = (block.getZ() + 0.5) + ((block.getZ() - blockBehindButton.getZ()) * 2);

                    Location locationCenter = new Location(world, xCenter, yCenter, zCenter);

                    Collection<Entity> entities = locationCenter.getWorld().getNearbyEntities(locationCenter, 2, 2, 2);

                    int rangeX = 100;
                    int rangeZ = 100;

                    Random rand = new Random();

                    double x = rand.nextInt(rangeX);
                    double y = 50.0;
                    double z = rand.nextInt(rangeZ);

                    Location locationPlayer = new Location(world, x, y, z);

                    locationPlayer.setY((locationPlayer.getWorld().getHighestBlockYAt(locationPlayer) + 3));

                    for(Entity entity : entities) {
                        if(entity instanceof Player) {
                            entity.teleport(locationPlayer);
                            entity.sendMessage("§aZostales przeteleportowany w losowe miejsce!");
                        }
                    }
                }
            }
        }
    }
}
