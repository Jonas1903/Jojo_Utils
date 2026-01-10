# Jojo Utils

A comprehensive utility mod for Minecraft 1.21.10 using Fabric loader, featuring customizable HUD elements, quality of life improvements, and PvP enhancements.

## Features

### 🎨 Draggable HUD System
- **HUD Layout Editor**: Press the configured keybind to open a visual editor
- **Drag & Drop**: Click and drag HUD elements to reposition them
- **Grid Snapping**: 8px grid snapping by default (hold Shift to disable for precise placement)
- **Scaling**: Scroll while hovering over elements to adjust their size
- **Visual Preview**: All HUD elements displayed as colored boxes with labels

### 1️⃣ Armor Durability HUD
- Display equipped armor pieces with durability information
- Toggle between percentage (75%) or raw numbers (330/440) display
- Color-coded durability warnings:
  - 🟢 Green: >50% durability
  - 🟡 Yellow: 20-50% durability
  - 🔴 Red: <20% durability
- Configurable opacity and background

### 2️⃣ Potion Effects HUD
- Show active status effects with icons
- Display remaining time in M:SS format
- **Flashing Alert**: Both icon and time flash when <5 seconds remain
- Clean, compact display

### 3️⃣ Shield Status Overlay
- **First-Person View**:
  - Red overlay when shield is on cooldown
  - Optional green overlay when shield is active
  - Adjustable X/Y/Z position offsets for the shield model
- **Multiplayer**:
  - Client-side prediction for other players' shields
  - Red overlay on enemy shields after you hit them (5-tick/0.25s cooldown)

### 4️⃣ Inventory HUD
- Visual copy of your main inventory (27 slots, excluding hotbar)
- Purely visual - no click interaction
- Toggle to hide empty slots (items don't shift, creating gaps)
- Scalable display

### 5️⃣ Health Indicators
- Display hearts above entity heads
- Separate toggles for:
  - 👥 Players
  - 🗡️ Hostile Mobs
  - 🐷 Friendly Mobs
- Configurable vertical offset and distance limit
- Support for high health entities (up to 8 rows/160 HP)

### 6️⃣ Smart F3
- Clean display of essential information:
  - **FPS**: Current frames per second
  - **Ping**: Network latency (hidden in singleplayer)
  - **Direction**: Cardinal direction (N, S, E, W)
- Individual toggles for each element
- Fixed display order

### 7️⃣ Fullbright
- Remove all darkness from the game
- Perfect visibility in caves, night, and underwater
- Toggle on/off without affecting your gamma settings
- Automatic restoration when disabled

### 8️⃣ Item Drop Prevention
- Prevent accidental dropping of valuable items
- Whitelist system (add items by ID, e.g., `minecraft:diamond_sword`)
- Blocks all drop methods:
  - Drop key (default Q)
  - Drop stack (default Ctrl+Q)
  - Dragging items outside inventory
- Silent prevention (no notifications)
- Matches by item type only (ignores NBT/enchantments)

## Configuration

### Mod Menu Integration
All settings are accessible through Mod Menu:
1. Install Mod Menu
2. Click "Mods" in the main menu
3. Find "Jojo Utils" and click the config button

### Keybindings
Configure keybindings in two ways:
- **Minecraft Controls**: Options → Controls → "Jojo Utils" category
- **Mod Menu**: Jojo Utils config → Keybindings tab

Available keybindings:
- Open HUD Layout Editor
- Toggle each feature on/off

**Default**: No keybinds assigned - you must set them manually

## Installation

### Requirements
- Minecraft 1.21.10
- Fabric Loader 0.18.4+
- Fabric API
- Mod Menu (required)
- Cloth Config API (required)

### Steps
1. Install Fabric Loader for Minecraft 1.21.10
2. Download and place in your `mods` folder:
   - Fabric API
   - Mod Menu
   - Cloth Config API
   - Jojo Utils
3. Launch Minecraft with the Fabric profile

## Usage Tips

### First-Time Setup
1. All features are **disabled by default**
2. Open Mod Menu and enable the features you want
3. Set keybindings for quick toggles
4. Press your HUD Layout keybind to position elements
5. Save and enjoy!

### HUD Layout Screen
- **Move**: Click and drag elements
- **Resize**: Scroll while hovering
- **Snap**: Automatic 8px grid (hold Shift to disable)
- **Save**: Press ESC to save and close

### Item Drop Prevention
1. Enable the feature in config
2. Add item IDs to the whitelist (format: `namespace:item_name`)
3. Example: `minecraft:diamond_sword`, `minecraft:netherite_pickaxe`
4. Protected items cannot be dropped by any method

## Technical Details

- **Mod ID**: `jojoutils`
- **Package**: `com.jonas.jojoutils`
- **Config Format**: JSON (stored in `config/jojo-utils.json`)
- **Language**: Java 21+

## License

MIT License

## Credits

Developed by Jonas1903
