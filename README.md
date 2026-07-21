# Spawner Fix

<p align="center">
  <a href="https://modrinth.com"><img src="https://img.shields.io/badge/Platform-Fabric-dbba13?style=for-the-badge&logo=fabric" alt="Fabric"></a>
  <a href="https://java.com"><img src="https://img.shields.io/badge/Java-25-red?style=for-the-badge&logo=openjdk" alt="Java 25"></a>
  <a href="https://creativecommons.org/licenses/by/4.0/"><img src="https://img.shields.io/badge/License-CC_BY_4.0-blue?style=for-the-badge" alt="CC BY 4.0"></a>
</p>

A lightweight, performance-focused **Fabric** mod that introduces native, server-wide game rules to dynamically control mob spawner activation ranges and horizontal spawn radiuses.

---

## Overview

Vanilla Minecraft strictly limits spawner activation to a hardcoded player proximity (16 blocks) and spawn radius (4 blocks). **Spawner Fix** exposes these mechanics through standard Minecraft `/gamerule` commands, enabling server administrators and singleplayer worlds to customize spawner behaviour globally without heavy tick listeners or performance degradation.

---

## Features

- **Native Game Rule Integration:** Configure activation distance and spawn radiuses on the fly using standard `/gamerule` syntax.
---

## Requirements & Compatibility

| Component | Requirement |
| :--- | :--- |
| **Minecraft** | **26.1**, **26.1.1**, **26.1.2**, **26.2** |
| **Fabric Loader** | `0.19.3+` |
| **Fabric API** | Required (Matching your MC version) |
| **Java** | **Java 25** |

> ⚠️ **Important Note:** Compatibility was verified by compiling against each release and verifying identical `BaseSpawner` mixin targets (`isNearPlayer`, `serverTick`, `spawnRange`, `requiredPlayerRange`).  
> **Minecraft Snapshots and version `26.3+` are NOT supported.**

---

## Game Rules & Configuration

Spawner Fix registers two global game rules into your world's rule registry:

### 1. `spawner_player_range`
* **Description:** Controls the maximum distance (in blocks) at which a player activates a mob spawner.
* **Default Value:** `128`
* **Valid Range:** `0` – `10000`

### 2. `spawner_spawn_radius`
* **Description:** Controls the maximum horizontal radius (in blocks) around a spawner where mobs can spawn.
* **Default Value:** `4`
* **Valid Range:** `0` – `10000`

---

### Command Usage Examples

To view or modify game rules in-game, use standard operator permissions:

```mcfunction
# Check the current activation distance
/gamerule spawner_player_range

# Set player activation distance to 128 blocks
/gamerule spawner_player_range 128

# Expand horizontal spawn radius to 16 blocks
/gamerule spawner_spawn_radius 16

# Disable spawner activation (Set range to 0)
/gamerule spawner_player_range 0
