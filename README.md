# Spawner Fix

Spawner Fix is a Fabric utility mod for configuring mob spawner behavior through standard Minecraft game rules.

Vanilla mob spawners only activate when a player is within 16 blocks. This mod makes the activation range configurable and defaults it to 128 blocks, matching the normal hostile mob spawning range. It also lets servers tune the horizontal spawn radius and spawner particle visibility without a restart.

## Starten

### Im Entwicklungsprojekt

Voraussetzungen: Java 25 und Windows. Im Projektordner ausführen:

```bat
gradlew.bat runClient
```

Alternativ können in IntelliJ IDEA die Run-Konfigurationen **Minecraft Client** und
**Minecraft Server** gestartet werden. Beide verwenden den aktuellen Projektordner und
funktionieren daher auch nach dem Verschieben oder Kopieren des Projekts.

### Als Mod im Minecraft-Launcher

1. `build/libs/spawner-fix-1.1.2.jar` bauen mit `gradlew.bat build`.
2. Die JAR-Datei in den `mods`-Ordner einer Fabric-Installation für Minecraft 26.3 kopieren.
3. Fabric Loader mindestens 0.19.3 und die passende Fabric API installieren.
4. Minecraft mit Java 25 starten.

## Features

- Server-side installation for dedicated servers and singleplayer worlds.
- Vanilla-style configuration through `/gamerule`.
- Runtime changes without restarting the server.
- Configurable spawner activation range, spawn radius, particle visibility, and Silk Touch drops.

## Game Rules

| Game Rule | Default | Range | Description |
| --- | --- | --- | --- |
| `spawner_player_range` | `128` | `0` - `10000` | Maximum player distance before the spawner goes idle. |
| `spawner_spawn_radius` | `4` | `0` - `10000` | Horizontal radius around the spawner where mobs can spawn. |
| `spawner_particles` | `true` | `true` / `false` | Global visibility setting for spawner flame particles. |
| `spawner_drop_with_silk_touch` | `false` | `true` / `false` | Allows spawners to drop when mined with Silk Touch. |

## Examples

```mcfunction
/gamerule spawner_player_range 128
/gamerule spawner_spawn_radius 16
/gamerule spawner_particles false
/gamerule spawner_drop_with_silk_touch true
```

## Personal Particle Setting

Players can toggle their own spawner particles with:

```mcfunction
/particles
/particles toggle
/particles on
/particles off
```

This player setting is independent of the global `spawner_particles` game rule.

## License

This project is licensed under the CC-BY-4.0 license.
