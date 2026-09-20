# Tektopia v0.2.0 (Fabric, Minecraft 1.21.11)

## Features
- **Town Charter** item: right-click a villager to see profession, level, XP, health.
- **/tektopia villagers [radius]**: counts villagers around you (default 32, max 128).

## Build
1. Install JDK 21.
2. gradle.properties already uses Fabric versions for 1.21.11 (re-check at fabricmc.net/develop if the build fails).
3. Run:  `gradle wrapper`  then  `./gradlew build`   (Windows: `gradlew.bat build`)
4. Jar: build/libs/tektopia-0.2.0.jar  -> put in .minecraft/mods with Fabric Loader + Fabric API.
5. Test in dev: `./gradlew runClient`

## If the build fails
Copy the full error text and ask for a fix. Most likely spots: version numbers,
`Identifier` (renamed from ResourceLocation in 1.21.11), `VillagerData` accessors.
