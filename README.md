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

Check out the [Releases](https://github.com/MinecraftMachina/McMouser/releases) section.

## How does it work

Right-click emulation is achieved in two different ways depending on the version of Minecraft.

For Minecraft <1.13, which use LWJGL2, the emulation is included inside the LWJGL2 native, `liblwjgl.dylib`. This limits our options, so the mod ships a modified (byte patched) version of the library and forces LWJGL2 to load it instead of the original. For reference, [here](https://github.com/LWJGL/lwjgl/commit/43a6a8bfbb1b55fe49ccbcb82997ddad51ce809b) is the commit that needs to be reverted. Both amd64 and arm64 versions of the library are include in the mod.

For Minecraft 1.14+, which use LWJGL3, the emulation is performed in regular Minecraft code. This makes it trivial to patch with a mixin.
