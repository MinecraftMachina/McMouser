<p align="center">
    <img width="256" heigth="256" src="logo.svg">
    <h1 align="center">McMouser</h1>
    <p align="center">
        A mod that fixes various mouse bugs on macOS
    </p>
</p>

## Introduction

Install this mod and you can now play the game like everyone else. Seriously, it's this simple.

## Features
- Fixes Shift + Scroll not working on macOS ([MC-121772](https://bugs.mojang.com/browse/MC-121772))
- Disables `Ctrl` + `Left Click` = `Right Click` emulation
## Downloads

The mod supports all major versions of Minecraft since 1.8.9, both Fabric and Forge. If your version is not listed, raise an issue, it is trivial to port the code.

Check out the [Releases](https://github.com/MinecraftMachina/McMouser/releases) section or [CurseForge](https://www.curseforge.com/minecraft/mc-mods/mcmouser).

## Building

The code for each Minecraft version is released in a separate git branch, so check out the one that interests you. Building is as simple as running `./gradlew build`, and the mod JAR will be under `build/libs`.

## How does it work

Right-click emulation is achieved in two different ways depending on the version of Minecraft.

For Minecraft <1.13, which use LWJGL2, the emulation is included inside the LWJGL2 native, `liblwjgl.dylib`. This limits our options, so the mod ships a modified version of the library and forces LWJGL2 to load it instead of the original. For arm64, the library is custom-built from [MinecraftMachina/lwjgl2#right-click-fix](https://github.com/MinecraftMachina/lwjgl/tree/right-click-fix). For x86_64, the original library is byte patched. For reference, [here](https://github.com/LWJGL/lwjgl/commit/43a6a8bfbb1b55fe49ccbcb82997ddad51ce809b) is the commit that needs to be reverted.

For Minecraft 1.14+, which use LWJGL3, the emulation is performed in regular Minecraft code. This makes it trivial to patch with a mixin.

Shift scroll bug is already fixed by Forge, but for Fabric, it is achieved in the same way via a mixin.

