<p align="center">
  <img src="https://cdn.phototourl.com/member/2026-07-24-a95d7c42-2673-4cab-8ede-34a99ec8e936.png" width="350px" alt="Features">
</p>

<br>

<p align="center">
  <a href="https://modrinth.com/mod/spawner-fix">
    <img src="https://i.ibb.co/MDnM4C3s/Chat-GPT-Image-23-Juli-2026-15-46-39-1.png" width="225px" alt="Modrinth Button">
  </a>
  &nbsp;&nbsp;&nbsp;&nbsp;
  <a href="https://www.curseforge.com/minecraft/mc-mods/leopoldos-spawner-fix">
    <img src="https://plain-weur-prod-public.komododecks.com/202607/23/VeSAptUuVWINmHI6C6eH/image.png" width="225px" alt="CurseForge Button">
  </a>
</p>

<img src="https://cdn.phototourl.com/member/2026-07-24-77c488b9-7c9d-4cd5-b6ea-f922514ed47c.png" alt="Trennlinie">

<img src="https://cdn.phototourl.com/free/2026-07-23-5b6f0697-33d5-4002-800e-a6e64c433092.png" width="100px" alt="About">

**Spawner Fix** is a Fabric utility that bridges the gap between mob spawners and natural mob spawning. 

By default, vanilla Minecraft limits mob spawners to a strict activation range of 16 blocks, meaning players have to stand right next to them. This mod removes that hardcoded limitation, adjusting the default activation range to match the standard vanilla range for natural mob spawning (128 blocks). 

**Highly Recommended for Singleplayer & Servers Alike:** 
While it works flawlessly on dedicated servers, Spawner Fix is also fully compatible and highly recommended for **Singleplayer worlds**. Since it runs entirely on the internal server engine using native commands, it fits seamlessly into any standard survival playthrough without altering core world generation.

Whether you want to create massive custom farms, improve survival immersion, or tightly optimize performance, Spawner Fix puts the power back in your hands. It introduces simple, native `/gamerule` commands so you can dynamically scale both player activation ranges and horizontal spawn radiuses on the fly no restarts required.

<img src="https://cdn.phototourl.com/member/2026-07-24-77c488b9-7c9d-4cd5-b6ea-f922514ed47c.png" alt="Trennlinie">

<img src="https://cdn.phototourl.com/free/2026-07-23-94c1e1c4-28bd-428c-8eb0-8ca7edbe69cd.png" width="130px" alt="Features">

* **Zero Client Footprint:** This is a 100% server-side mod. Install it on your server or your singleplayer world, and players can join without needing to install anything!
* **Pure Vanilla Feel:** No messy `.toml` or `.json` config files to edit. Everything is controlled seamlessly via standard, native Minecraft `/gamerule` commands.
* **Dynamic Real-Time Tuning:** Change activation distances or spawn radiuses instantly on the fly—your changes take effect immediately without requiring a single server restart.
* **Smart Performance First:** Built from the ground up with optimization in mind, ensuring zero unnecessary CPU overhead from idle or distant spawners.

<img src="https://cdn.phototourl.com/member/2026-07-24-77c488b9-7c9d-4cd5-b6ea-f922514ed47c.png" alt="Trennlinie">

<img src="https://cdn.phototourl.com/member/2026-07-24-2309eaab-f2d3-472d-9a7e-0d41dd23fa32.png" width="160px" alt="Game Rules">

Take total, global control over how spawners behave across your dimensions using these simple settings:

| Game Rule | Default | Range | Description |
| :--- | :---: | :---: | :--- |
| `spawner_player_range` | `128` | `0` – `10000` | The maximum distance (in blocks) a player can be away before the spawner goes idle. |
| `spawner_spawn_radius` | `4` | `0` – `10000` | The horizontal block radius around the spawner where mobs are allowed to materialize. |

<img src="https://cdn.phototourl.com/member/2026-07-24-153baa93-5d64-42ce-9fb9-b63a984fba97.png" width="220px" alt="Quick Examples">

```mcfunction
# Match the natural mob spawning sphere (128 blocks)
/gamerule spawner_player_range 128

# Expand the horizontal area for massive custom mob farms
/gamerule spawner_spawn_radius 16
```
<!-- Trennlinie -->
<img src="https://cdn.phototourl.com/member/2026-07-24-77c488b9-7c9d-4cd5-b6ea-f922514ed47c.png" alt="Trennlinie">

<img src="https://cdn.phototourl.com/free/2026-07-23-de1b7d28-0140-458e-ba20-508dc5ea0a1e.png" width="100px" alt="About">

This project is licensed under the **CC-BY-4.0 license**. If you want to read the full legal details, click the button below:

<div><a href="https://raw.githubusercontent.com/leopoldoronaldi/spawner-fix/refs/heads/main/LICENSE" style="text-decoration:none" rel="nofollow"><img src="https://s1.directupload.eu/images/260723/bwrocayj.png" width="250px" style="display:inline-block;border:none"></a></div>
