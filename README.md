<p align="center">
    <img width="256" heigth="256" src="logo.svg">
    <h1 align="center">McMouser</h1>
    <p align="center">
        A mod that disables the <kbd>Ctrl</kbd> + <kbd>Left Click</kbd> = <kbd>Right Click</kbd> emulation on macOS
    </p>
</p>

## Introduction

You can now play the game like everyone else. You know, sprinting and attacking.
Seriously, it's this simple.

## Downloads

The mod supports all major versions of Minecraft since 1.8.9, both Fabric and Forge. If your version is not listed, raise an issue, it is trivial to port the code.

- Minecraft 1.18.0
  - [Forge](https://github.com/MinecraftMachina/McMouser/releases/download/v1.18.0-forge-1.0.0/mcmouser-1.18-forge-1.0.0.jar)
  - [Fabric](https://github.com/MinecraftMachina/McMouser/releases/download/v1.18.0-fabric-1.0.0/mcmouser-1.18-fabric-1.0.0.jar)
- Minecraft 1.17.1
  - [Forge](https://github.com/MinecraftMachina/McMouser/releases/download/v1.17.1-forge-1.0.0/mcmouser-1.17.1-forge-1.0.0.jar)
  - [Fabric](https://github.com/MinecraftMachina/McMouser/releases/download/v1.17.1-fabric-1.0.0/mcmouser-1.17.1-fabric-1.0.0.jar)
- Minecraft 1.16.5
  - [Forge](https://github.com/MinecraftMachina/McMouser/releases/download/v1.16.5-forge-1.0.0/mcmouser-1.16.5-forge-1.0.0.jar)
  - [Fabric](https://github.com/MinecraftMachina/McMouser/releases/download/v1.16.5-fabric-1.0.0/mcmouser-1.16.5-fabric-1.0.0.jar)
- Minecraft 1.12.2
  - [Forge](https://github.com/MinecraftMachina/McMouser/releases/download/v1.12.2-forge-1.0.0/mcmouser-1.12.2-forge-1.0.0.jar)
- Minecraft 1.8.9
  - [Forge](https://github.com/MinecraftMachina/McMouser/releases/download/v1.8.9-forge-1.0.0/mcmouser-1.8.9-forge-1.0.0.jar)

## How does it work

Right-click emulation is achieved in two different ways depending on the version of Minecraft.

For Minecraft <1.13, which use LWJGL2, the emulation is included inside the LWJGL2 native, `liblwjgl.dylib`. This limits our options, so the mod ships a modified (byte patched) version of the library and forces LWJGL2 to load it instead of the original. For reference, [here](https://github.com/LWJGL/lwjgl/commit/43a6a8bfbb1b55fe49ccbcb82997ddad51ce809b) is the commit that needs to be reverted. Both amd64 and arm64 versions of the library are include in the mod.

For Minecraft 1.14+, which use LWJGL3, the emulation is performed in regular Minecraft code. This makes it trivial to patch with a mixin.
