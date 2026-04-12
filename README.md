# Mineral Apples
A mod for Forge 1.20.1 that adds stronger and weaker mineral apples, based off of the idea of the golden apple.  
More information can be found on the Modrinth page of the mod.

# Links
Modrinth: https://modrinth.com/mod/mineral-apples

# Contributing
Fork the mod, make your changes, then submit a pull request.

## Translation
Strings are in `src/main/resources/assets/mineral_apples/lang/`. When you're done translating, make a pull request.

# Building
**Make sure you have JDK 17 installed, and set as default**

On Windows, run:  
`.\gradlew.bat build` on PowerShell or  
`gradlew.bat build` on CMD  
On Linux and macOS, run:  
`./gradlew build`  
The freshly built jar should be in `build/libs/`  

## Changing the version number
Change the version number in `gradle.properties` and in `src/main/resources/META-INF/mods.toml`. Make sure they match.