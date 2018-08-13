/*
 *
 * Player-Expansion
 * Copyright (C) 2018 Ryan McCarthy
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 *
 */
package com.extendedclip.papi.expansion.player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import me.clip.placeholderapi.PlaceholderAPIPlugin;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PlayerExpansion extends PlaceholderExpansion {

  private final String VERSION = getClass().getPackage().getImplementationVersion();

  @Override
  public String getIdentifier() {
    return "player";
  }

  @Override
  public String getAuthor() {
    return "clip";
  }

  @Override
  public String getVersion() {
    return VERSION;
  }

  public String onRequest(OfflinePlayer player, String identifier) {

    if (identifier.startsWith("ping_")) {
      identifier = identifier.split("ping_")[1];
      Player t = Bukkit.getPlayer(identifier);
      if (t != null) {
        return getPing(t);
      }
      return "0";
    }

    if (player == null) {
      return "";
    }

    // offline placeholders
    switch (identifier) {
      case "name":
        return player.getName();
      case "uuid":
        return player.getUniqueId().toString();
      case "has_played_before":
        return bool(player.hasPlayedBefore());
      case "online":
        return bool(player.isOnline());
      case "is_whitelisted":
        return bool(player.isWhitelisted());
      case "is_banned":
        return bool(player.isBanned());
      case "is_op":
        return bool(player.isOp());
      case "first_played":
      case "first_join":
        return String.valueOf(player.getFirstPlayed());
      case "first_played_formatted":
      case "first_join_date":
        return PlaceholderAPIPlugin.getDateFormat().format(new Date(player.getFirstPlayed()));
      case "last_played":
      case "last_join":
        return String.valueOf(player.getLastPlayed());
      case "last_played_formatted":
      case "last_join_date":
        return PlaceholderAPIPlugin.getDateFormat().format(new Date(player.getLastPlayed()));
      case "bed_x":
        return player.getBedSpawnLocation() != null ? player.getBedSpawnLocation().getX() + "" : "";
      case "bed_y":
        return player.getBedSpawnLocation() != null ? player.getBedSpawnLocation().getY() + "" : "";
      case "bed_z":
        return player.getBedSpawnLocation() != null ? player.getBedSpawnLocation().getZ() + "" : "";
      case "bed_world":
        return player.getBedSpawnLocation() != null ? player.getBedSpawnLocation().getWorld()
            .getName() : "";
    }

    // online placeholders
    if (!player.isOnline()) {
      return "";
    }

    Player p = (Player) player;

    if (identifier.startsWith("has_permission_")) {
      String perm = identifier.split("has_permission_")[1];
      return bool(p.hasPermission(perm));
    }

    switch (identifier) {
      case "has_empty_slot":
        return bool(p.getInventory().firstEmpty() > -1);
      case "server":
      case "servername":
        return Bukkit.getServerName();
      case "displayname":
        return p.getDisplayName();
      case "gamemode":
        return p.getGameMode().name();
      case "world":
        return p.getWorld().getName();
      case "x":
        return String.valueOf(p.getLocation().getBlockX());
      case "y":
        return String.valueOf(p.getLocation().getBlockY());
      case "z":
        return String.valueOf(p.getLocation().getBlockZ());
      case "ip":
        return p.getAddress().getAddress().getHostAddress();
      case "allow_flight":
        return bool(p.getAllowFlight());
      case "can_pickup_items":
        return bool(p.getCanPickupItems());
      case "compass_x":
        return p.getCompassTarget() != null ? String.valueOf(p.getCompassTarget().getBlockX()) : "";
      case "compass_y":
        return p.getCompassTarget() != null ? String.valueOf(p.getCompassTarget().getBlockY()) : "";
      case "compass_z":
        return p.getCompassTarget() != null ? String.valueOf(p.getCompassTarget().getBlockZ()) : "";
      case "compass_world":
        return p.getCompassTarget() != null ? p.getCompassTarget().getWorld().getName() : "";
      case "custom_name":
        return p.getCustomName() != null ? p.getCustomName() : p.getName();
      case "exp":
        return String.valueOf(p.getExp());
      case "exp_to_level":
        return String.valueOf(p.getExpToLevel());
      case "level":
        return String.valueOf(p.getLevel());
      case "fly_speed":
        return String.valueOf(p.getFlySpeed());
      case "food_level":
        return String.valueOf(p.getFoodLevel());
      case "health":
        return String.valueOf(p.getHealth());
      case "health_rounded":
        return String.valueOf(Math.round(p.getHealth()));
      case "health_scale":
        return String.valueOf(p.getHealthScale());
      case "item_in_hand":
        return p.getItemInHand() != null ? p.getItemInHand().getType().name() : "";
      case "item_in_hand_data":
        return p.getItemInHand() != null ? p.getItemInHand().getDurability() + "" : "0";
      case "item_in_offhand":
        return p.getInventory().getItemInOffHand() != null ? player.getInventory().getItemInOffHand().getType().name() : "";
      case "item_in_offhand_data":
        return p.getInventory().getItemInOffHand() != null ? player.getInventory().getItemInOffHand().getType().getMaxDurability() + "" : "0";
      case "last_damage":
        return String.valueOf(p.getLastDamage());
      case "max_health":
        return String.valueOf(p.getMaxHealth());
      case "max_health_rounded":
        return String.valueOf(Math.round(p.getMaxHealth()));
      case "max_air":
        return String.valueOf(p.getMaximumAir());
      case "max_no_damage_ticks":
        return String.valueOf(p.getMaximumNoDamageTicks());
      case "no_damage_ticks":
        return String.valueOf(p.getNoDamageTicks());
      case "armor_helmet_name":
        return player.getInventory().getHelmet().getItemMeta().getDisplayName();
      case "armor_helmet_data":
        return String.valueOf(player.getInventory().getHelmet().getDurability());
      case "armor_chestplate_name":
        return player.getInventory().getChestplate().getItemMeta().getDisplayName();
      case "armor_chestplate_data":
        return String.valueOf(player.getInventory().getChestplate().getDurability());
      case "armor_leggings_name":
        return player.getInventory().getLeggings().getItemMeta().getDisplayName();
      case "armor_leggings_data":
        return String.valueOf(player.getInventory().getLeggings().getDurability());
      case "armor_boots_name":
        return player.getInventory().getBoots().getItemMeta().getDisplayName();
      case "armor_boots_data":
        return String.valueOf(player.getInventory().getBoots().getDurability());
      case "ping":
        return getPing(p);
      case "time":
        return String.valueOf(p.getPlayerTime());
      case "time_offset":
        return String.valueOf(p.getPlayerTimeOffset());
      case "remaining_air":
        return String.valueOf(p.getRemainingAir());
      case "saturation":
        return String.valueOf(p.getSaturation());
      case "sleep_ticks":
        return String.valueOf(p.getSleepTicks());
      case "ticks_lived":
        return String.valueOf(p.getTicksLived());
      case "seconds_lived":
        return String.valueOf(p.getTicksLived() * 20);
      case "minutes_lived":
        return String.valueOf((p.getTicksLived() * 20) / 60);
      case "total_exp":
        return String.valueOf(p.getTotalExperience());
      case "walk_speed":
        return String.valueOf(p.getWalkSpeed());
      case "world_time":
        return String.valueOf(p.getWorld().getTime());
      case "world_time_12":
        return PlayerUtil.format12(p.getWorld().getTime());
      case "world_time_24":
        return PlayerUtil.format24(p.getWorld().getTime());
    }
    // return null for unknown placeholders
    return null;
  }

  public String bool(boolean b) {
    return b ? PlaceholderAPIPlugin.booleanTrue() : PlaceholderAPIPlugin.booleanFalse();
  }

  public String getPing(Player p) {
    try {
      Method getHandleMethod = p.getClass().getDeclaredMethod("getHandle", new Class[0]);
      Object nmsplayer = getHandleMethod.invoke(p, new Object[0]);
      Field pingField = nmsplayer.getClass().getDeclaredField("ping");
      return String.valueOf(pingField.getInt(nmsplayer));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "0";
  }
}
