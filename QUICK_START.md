# Quick Start Guide for Developers

## Building the Mod

### Prerequisites
1. Java 21 or higher
2. Access to Fabric Maven repository (maven.fabricmc.net)
3. Gradle 8.5+

### Build Steps

```bash
# Clone the repository
git clone https://github.com/Jonas1903/Jojo_Utils.git
cd Jojo_Utils

# Generate Gradle wrapper (if needed)
gradle wrapper --gradle-version 8.10.2

# Build the mod
./gradlew build

# The built JAR will be in build/libs/
```

### Build Configuration

If you encounter build issues, verify these files:

**gradle.properties**
```properties
minecraft_version=1.21.10
yarn_mappings=1.21.10+build.3
loader_version=0.18.4
loom_version=1.7.4
mod_version=1.0.0
maven_group=com.jonas.jojoutils
archives_base_name=jojo-utils
fabric_api_version=0.138.4+1.21.10
```

**build.gradle**
```gradle
apply plugin: 'fabric-loom'
apply plugin: 'maven-publish'

dependencies {
    minecraft "com.mojang:minecraft:${project.minecraft_version}"
    mappings "net.fabricmc:yarn:${project.yarn_mappings}:v2"
    modImplementation "net.fabricmc:fabric-loader:${project.loader_version}"
    modImplementation "me.shedaniel.cloth:cloth-config-fabric:15.0.130"
    modImplementation "net.fabricmc.fabric-api:fabric-api:${project.fabric_api_version}"
    modImplementation "com.terraformersmc:modmenu:11.0.3"
}
```

## Installation

### For Users

1. Install Minecraft 1.21.10
2. Install Fabric Loader
3. Download and install dependencies:
   - Fabric API
   - Mod Menu
   - Cloth Config API
4. Place `jojo-utils-1.0.0.jar` in your `.minecraft/mods` folder
5. Launch the game

### Mod Dependencies

| Dependency | Version | Required |
|------------|---------|----------|
| Fabric Loader | 0.18.4+ | Yes |
| Fabric API | Latest for 1.21.10 | Yes |
| Mod Menu | 11.0.3+ | Yes |
| Cloth Config API | 15.0.130+ | Yes |

## First-Time Configuration

### 1. Access Configuration
- Main Menu → Mods → Jojo Utils → Config Button

### 2. Enable Features
All features are disabled by default. Enable what you want:
- Armor HUD
- Potion HUD
- Shield Status
- Inventory HUD
- Health Indicators
- Smart F3
- Fullbright
- Item Drop Prevention

### 3. Set Keybindings
Navigate to the Keybindings category in config or:
- Options → Controls → Jojo Utils category

Set keybinds for:
- HUD Layout Screen (to reposition elements)
- Quick toggles for each feature

### 4. Position HUD Elements
1. Press your HUD Layout keybind
2. Drag elements to desired positions
3. Scroll while hovering to scale
4. Hold Shift for precise placement (no grid snapping)
5. Press ESC to save

### 5. Configure Features

Each feature has specific settings:

**Armor HUD**
- Toggle percentage vs raw durability display
- Adjust opacity and background

**Potion HUD**
- Adjust opacity and background

**Shield Status**
- Red overlay opacity (when on cooldown)
- Green overlay toggle and opacity (when active)
- X/Y/Z position offsets for first-person shield

**Inventory HUD**
- Hide empty slots toggle
- Adjust opacity and background

**Health Indicators**
- Toggle for players/hostile/friendly mobs
- Vertical offset above heads
- Maximum render distance

**Smart F3**
- Toggle FPS/Ping/Direction individually
- Adjust opacity and background

**Fullbright**
- Just toggle on/off (no additional settings)

**Item Drop Prevention**
- Add item IDs to whitelist
- Format: `namespace:item_name`
- Example: `minecraft:diamond_sword`

## Development

### Project Structure
```
src/main/java/com/jonas/jojoutils/
├── config/          # Configuration system
├── feature/         # Non-HUD features
├── hud/             # HUD elements and framework
├── keybind/         # Keybinding management
└── mixin/           # Minecraft game modifications
```

### Adding a New Feature

1. Create feature class in `feature/` or HUD class in `hud/`
2. Add configuration fields to `JojoUtilsConfig.java`
3. Add UI controls to `ModMenuIntegration.java`
4. Add translations to `en_us.json`
5. Register in appropriate manager (HUDManager or feature init)
6. Add keybinding if needed in `KeybindManager.java`

### Creating a New HUD Element

```java
public class MyCustomHUD extends HUDElement {
    public MyCustomHUD() {
        super("My Custom HUD", JojoUtilsConfig.myCustomHud);
    }
    
    @Override
    public void render(DrawContext context, float tickDelta) {
        // Rendering logic here
    }
    
    @Override
    public int getWidth() { return 100; }
    
    @Override
    public int getHeight() { return 50; }
}
```

### Adding a Mixin

1. Create mixin class in `mixin/` package
2. Add to `jojo-utils.mixins.json` in the `client` array
3. Use `@Inject`, `@Redirect`, or other mixin annotations

Example:
```java
@Mixin(TargetClass.class)
public class MyMixin {
    @Inject(method = "targetMethod", at = @At("HEAD"))
    private void onTargetMethod(CallbackInfo ci) {
        // Your code here
    }
}
```

## Troubleshooting

### Mod doesn't load
- Check Fabric Loader version (0.18.4+)
- Verify all dependencies are installed
- Check logs for errors

### Features not working
- Make sure feature is enabled in config
- Check if you're in the correct game mode
- Verify keybindings are set

### HUD elements not visible
- Open HUD Layout Screen to check positions
- Verify feature is enabled
- Check opacity settings (might be set to 0)

### Configuration not saving
- Check file permissions in `.minecraft/config/`
- Look for `jojo-utils.json` file
- Check logs for IO errors

## Support

For issues, feature requests, or contributions:
- GitHub: https://github.com/Jonas1903/Jojo_Utils
- Check existing issues before creating new ones
- Provide logs and Minecraft version when reporting bugs

## License

MIT License - See LICENSE file for details
