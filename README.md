Got you — yeah your current description makes it feel like a **manual tool**, while the real power is the **automatic conversion**.

Here’s a rewritten version that puts the **on-join system front and center** and makes the commands clearly optional:

---

# LsEnderChestConverter

Automatically convert vanilla Ender Chest contents into **LsEnderChest** storage.

LsEnderChestConverter is a companion plugin for **LsEnderChest** that seamlessly migrates players from the vanilla Ender Chest system to the custom storage system — **without requiring manual action**.

---

## ⭐ Main Feature

* **Automatic conversion on join**

  * Players’ vanilla Ender Chest contents are converted the moment they join
  * Runs only once per player
  * Safe and seamless — no commands needed

---

## Features

* Automatic Ender Chest conversion when players join
* One-time conversion per player (UUID-based tracking)
* Optional manual conversion commands
* Lightweight and easy to configure

---

## Requirements

* **Paper / Spigot 1.21+**
* **Java 21**
* **LsEnderChest** installed

---

## Installation

1. Install **LsEnderChest**
2. Place **LsEnderChestConverter** into your `plugins` folder
3. Start the server
4. Players will automatically be converted on join

---

## Commands (Optional)

| Command                 | Description                         |
| ----------------------- | ----------------------------------- |
| `/lsecconvert <player>` | Manually convert one online player  |
| `/lsecconvert all`      | Manually convert all online players |

---

## Permissions

| Permission     | Description                      | Default |
| -------------- | -------------------------------- | ------- |
| `lsec.convert` | Allows use of converter commands | `op`    |

---

## Configuration

```yml
# Automatically convert players when they join
convert-on-join: true

debug: false
```

---

If you want, I can make an even stronger version that:

* sells it like a **“zero-downtime migration plugin”** (better for Modrinth downloads)
* or add a **warning/backup note** to make it look more professional/trustworthy.
