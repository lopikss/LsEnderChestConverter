NOTE: in 99% of cases all you need to do is install the plugin and let it do its thing, it automatically converts everyone who joins.

# LsEnderChestConverter

Convert vanilla Minecraft Ender Chest contents into **LsEnderChest** storage.

LsEnderChestConverter is a companion plugin for [LsEnderChest](https://modrinth.com/plugin/lsenderchest) that helps migrate players from the vanilla Ender Chest system to the custom **LsEnderChest** storage system.

---

## Features

- Convert a single player's vanilla Ender Chest
- Convert all online players
- Automatically convert players on join
- Stores converted player UUIDs to prevent duplicate conversions
- Lightweight and simple configuration

---

## Requirements

- **Paper / Spigot 1.21+**
- **Java 21**
- **LsEnderChest** installed

---

## Installation

1. Install **LsEnderChest**
2. Place **LsEnderChestConverter** into your `plugins` folder
3. Start the server
4. Configure the plugin if needed
5. Use the command below to begin converting Ender Chests

---

## Commands

| Command | Description |
|--------|-------------|
| `/lsecconvert <player>` | Convert one online player's vanilla Ender Chest |
| `/lsecconvert all` | Convert all online players |

---

## Permissions

| Permission | Description | Default |
|-----------|-------------|---------|
| `lsec.convert` | Allows use of converter commands | `op` |

---

## Configuration

```yml
# convert-on-first-join, means first join After the plugin is installed not first time joining the server ever.
convert-on-first-join: true

debug: false
