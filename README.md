# 2D RPG Game Documentation

## Overview

The game is a 2D RPG where the player controls a character on an island. The goal is to collect keys and items, unlock buildings, and ultimately gather all items to win the game. The player can move in four directions, interact with objects, and benefit from boosts that enhance their speed. The game utilizes Java Swing for creating the graphical user interface.

## Project Structure

### 1. **Main Package**

#### **KeyHandler**

Handles keyboard inputs for player movement and debug commands.

- **Fields:**
  - `boolean upPressed`, `downPressed`, `leftPressed`, `rightPressed`, `idle`: Track movement and idle states.
  - `boolean checkDrawTime`: For debugging.

- **Methods:**
  - `keyTyped(KeyEvent e)`: Unused; required by `KeyListener`.
  - `keyPressed(KeyEvent e)`: Sets movement flags based on key presses. Toggles `checkDrawTime` for debugging.
  - `keyReleased(KeyEvent e)`: Clears movement flags when keys are released.

#### **Sound**

Manages sound effects and background music.

- **Fields:**
  - `Clip clip`: Represents the audio clip being played.
  - `URL[] soundURL`: Array of URLs for different sound files.

- **Methods:**
  - `setFile(int i)`: Loads an audio file into the `clip`.
  - `play()`: Starts playing the sound clip.
  - `loop()`: Loops the sound clip indefinitely.
  - `stop()`: Stops the sound clip.

#### **UI**

Handles the game's user interface, including displaying game status and playtime.

- **Fields:**
  - `GamePanel gp`: Reference to the `GamePanel`.
  - `Font arial_40`: Font used for UI text.
  - `boolean gameFinished`: Indicates if the game is finished.
  - `double playTime`: Tracks the elapsed playtime.
  - `DecimalFormat dFormat`: Formats playtime for display.

- **Methods:**
  - `draw(Graphics2D g2)`: Draws the game status and playtime on the screen. Displays the win message and playtime if the game is finished; otherwise, shows key count, item count, and elapsed time.

### 2. **Entity Package**

#### **Entity**

Base class for all entities in the game (player, monsters, NPCs).

- **Fields:**
  - `int worldX`, `worldY`: Position of the entity in the world.
  - `int speed`: Movement speed.
  - `BufferedImage up1`, up2, down1, down2, left1, left2, right1, right2, idleUp, idleDown, idleLeft, idleRight: Sprites for different movements and states.
  - `String direction`: Current movement direction.
  - `int spriteCounter`, `spriteNum`: Animation handling.
  - `Rectangle solidArea`: Hitbox for collision detection.
  - `int solidAreaDefaultX`, `solidAreaDefaultY`: Default hitbox position.
  - `boolean collisionOn`: Flag indicating if a collision is detected.

#### **Player**

Represents the player character.

- **Fields:**
  - `GamePanel gp`: Reference to the `GamePanel`.
  - `KeyHandler keyH`: Reference to the `KeyHandler`.
  - `int screenX`, `screenY`: Screen position of the player.
  - `int hasKey`, `hasItem`: Counts of keys and items collected.

- **Methods:**
  - `setDefaultValues()`: Sets the initial values for the player’s position, speed, and direction.
  - `getPlayerImage()`: Loads player images from resources.
  - `update()`: Updates player position based on input and handles collisions.
  - `pickUpObject(int i)`: Manages object interactions, such as collecting keys, using doors, and picking up items.
  - `draw(Graphics2D g2)`: Draws the player sprite based on movement or idle state.

### 3. **Object Package**

#### **SuperObject**

Base class for all in-game objects.

- **Fields:**
  - `BufferedImage image`: Image representing the object.
  - `String name`: Name of the object.
  - `boolean collision`: Indicates if the object has collision properties.
  - `int worldX`, `worldY`: Position of the object in the world.
  - `Rectangle solidArea`: Hitbox for collision detection.

- **Methods:**
  - `draw(Graphics2D g2, GamePanel gp)`: Draws the object on the screen based on its position and visibility relative to the player.

#### **OBJ_Boots**

Represents a boost item that increases player speed.

- **Constructor:**
  - Sets the name to "Boots" and loads the image.

#### **OBJ_Door**

Represents a door object that can be opened with a key.

- **Constructor:**
  - Sets the name to "Door", loads the image, and sets `collision` to true.

#### **OBJ_Uranium**

Represents an item that contributes to winning the game.

- **Constructor:**
  - Sets the name to "Uranium" and loads the image.

#### **OBJ_Key**

Represents a key object that allows the player to open doors.

- **Constructor:**
  - Sets the name to "Key" and loads the image.

### 4. **Tile Package**

#### **Tile**

Represents a single tile in the game world.

- **Fields:**
  - `BufferedImage image`: Image representing the tile.
  - `boolean collision`: Indicates if the tile has collision properties.

#### **TileManager**

Manages tiles and the tile map.

- **Fields:**
  - `GamePanel gp`: Reference to the `GamePanel`.
  - `Tile[] tile`: Array of tiles.
  - `int mapTileNum[][]`: 2D array representing the tile map.

- **Methods:**
  - `getTileImage()`: Loads tile images and initializes the `tile` array.
  - `loadMap(String filePath)`: Reads a map file and populates the `mapTileNum` array.
  - `draw(Graphics2D g2)`: Draws the visible tiles on the screen based on the player’s position.

### 5. **Java Swing Integration**

The game uses Java Swing to create and manage the graphical user interface. Swing provides a rich set of components for building GUIs and is part of the Java Foundation Classes (JFC).

#### **GamePanel**

The `GamePanel` class, which extends `JPanel`, is the main component that integrates with the Swing framework.

- **Fields:**
  - `int screenWidth`, `screenHeight`: Dimensions of the game screen.
  - `int tileSize`: Size of each tile.
  - `int maxWorldCol`, `maxWorldRow`: Dimensions of the game world.
  - `TileManager tileM`: Manages the game tiles.
  - `KeyHandler keyH`: Handles keyboard inputs.
  - `Player player`: Represents the player character.
  - `UI ui`: Manages the user interface.
  - `Sound sound`: Manages game sounds.
  - `boolean gameFinished`: Indicates if the game is finished.
  - `CollisionChecker cChecker`: Checks for collisions.
  - `SuperObject[] obj`: Array of game objects.
  - `Thread gameThread`: Main game loop thread.

- **Methods:**
  - `setupGame()`: Initializes the game objects, player, and other game components.
  - `startGameThread()`: Starts the game loop thread.
  - `run()`: Contains the game loop, updating and repainting the game at a fixed interval.
  - `paintComponent(Graphics g)`: Custom painting of the game screen, called by the Swing framework.
  - `update()`: Updates game logic, including player and object states.

The `GamePanel` class leverages Swing's `JPanel` for custom drawing and managing the game state, providing a responsive and interactive gaming experience.
