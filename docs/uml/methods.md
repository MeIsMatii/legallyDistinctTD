classDiagram
direction BT
class Animations {
<<Interface>>
  + playAnimation() void
  + generateFrameList() void
  + startAnimation() void
  + isAnimating(boolean) void
  + addedToWorld(World) void
   List~String~ frameList
   String spritePath
   String spriteName
   int frameCounter
   int currentFrameIndex
   int animationSpeed
}
class AntiCheat {
  - AntiCheat() 
  - checkHoneypot() void
  - checkRunningProcesses() void
  - checkValueCeiling() void
  - tick() void
  + update() void
}
class BackButton {
  + BackButton() 
  + onClick() void
  + getBack(World) void
}
class Button {
  + Button() 
  # PopupScreen owner
  + act() void
   PopupScreen owner
}
class Clickable {
<<Interface>>
  + checkClick() void
  + onClick() void
}
class ClickawayImageDisplay {
  + ClickawayImageDisplay(String, int, int) 
  + ClickawayImageDisplay(String, int, int, boolean) 
  + onRemove() void
  + onClick() void
  + act() void
}
class ClientButton {
  + ClientButton() 
  + onClick() void
}
class ClosePopupButton {
  + ClosePopupButton(PopupScreen) 
  + onClick() void
}
class Cursor {
  + Cursor() 
  + act() void
  + followMouse() void
  + isTouching(Class~?~) boolean
}
class CustomWorld {
  + CustomWorld() 
  + CustomWorld(int, int, int) 
  - setupPaintOrder() void
  + act() void
}
class DevNotesMap {
  + DevNotesMap() 
}
class DeveloperNotesButton {
  + DeveloperNotesButton() 
  + onClick() void
}
class Difficulty {
<<enumeration>>
  - Difficulty(int) 
  - int winWave
  + valueOf(String) Difficulty
  + values() Difficulty[]
   int winWave
}
class DifficultySelector {
  + DifficultySelector(GameMap, Difficulty) 
  + onClick() void
}
class DifficultySelectorPopup {
  + DifficultySelectorPopup(GameMap) 
  + onRemove() void
  + addedToWorld(World) void
  + act() void
}
class Enemy {
  + Enemy(double, int) 
  ~ int initialLives
  ~ double speed
  ~ double lives
  + act() void
  + onHit(Entity) void
  + applySlow(double, int) void
  + spawnHitbox(int, int) void
  + damage(double) void
  + updateSlow() void
  + onHover() void
  + moveTo(int, int) void
  + findPath() void
  + addedToWorld(World) void
   int initialLives
   String name
   double lives
   double speed
}
class EnemyHitbox {
  + EnemyHitbox(int, int, Entity) 
  + drawHitbox(int, int, Color, boolean) GreenfootImage
}
class EnemyLevel1 {
  + EnemyLevel1() 
   String name
}
class EnemyLevel2 {
  + EnemyLevel2() 
   String name
}
class EnemyLevel3 {
  + EnemyLevel3() 
   String name
}
class EnemyLevel4 {
  + EnemyLevel4() 
   String name
}
class EnemyLevel5 {
  + EnemyLevel5() 
   String name
}
class EnemyLevel6 {
  + EnemyLevel6() 
   String name
}
class Entity {
  + Entity() 
  # String uniqueId
  + move(int) void
  + addedToWorld(World) void
  + checkHover(boolean) void
  + onHit(Entity) void
  + onUnhover() void
  + setLocation(int, int, boolean) void
  + onHover() void
  + spawnHitbox(int, int) void
  + setLocation(int, int) void
  + act() void
   String uniqueId
}
class ExampleActor {
  + ExampleActor() 
  + act() void
  - updateAppearance() void
  + onClick() void
}
class Explosion {
  + Explosion(Tower) 
  + Explosion() 
  - int explosionCounter
  + act() void
  + onHit(Entity) void
   int explosionCounter
}
class FlameProjectile {
  + FlameProjectile(Tower) 
  + FlameProjectile() 
  + move() void
}
class Flamethrower {
  + Flamethrower() 
  + upgrade(int) void
  - recharge() void
  + act() void
  + addedToWorld(World) void
  + shoot(Enemy) void
   String name
   int animationSpeed
}
class Gambling {
  + Gambling() 
  - ImageDisplay slot1
  - ImageDisplay slot2
  - ImageDisplay slot3
   ImageDisplay slot1
   ImageDisplay slot3
   ImageDisplay slot2
}
class GamblingWonCredits {
  + GamblingWonCredits() 
  + act() void
  + credits() void
}
class GameMap {
  + GameMap(boolean, boolean) 
  + GameMap() 
  - boolean isMultiplayer
  - int wave
  - boolean isUpgradeMenuVisible
  - Difficulty difficulty
  - int receivedWaveMoney
  - boolean hasGameStarted
  - UpgradeMenu upgradeMenu
  - Player player
  - boolean isPaused
  - GameSaveManager gameSaveManager
  - boolean isForcedPause
  - int[] spawnLocation
  + processCommand(String) void
  + showWave() void
  + pauseObjects(boolean, boolean) void
  + removeDeadEnemies() void
  + removeEntityFromNetwork(String) void
  + Wavecheat() void
  + checkPaused() void
  + addHud() void
  + spawnProjectileFromNetwork(String, String, String) void
  + moveEntityFromNetwork(String, int, int) void
  + spawnWave(int, int) void
  + resetWave() void
  + damageEnemyFromNetwork(String, double) void
  + setUpgradeMenuVisibility(boolean, Tower) void
  + act() void
  + spawnEnemyFromNetwork(String, String) void
  + onContinue() void
  + upgradeTowerFromNetwork(String, int, int) void
  + isPathValid(int, int) boolean
  + addPath(int[][]) void
  + targetEnemyFromNetwork(String, String) void
  + startHost() void
  + pauseObjects() void
  + spawnTowerFromNetwork(String, String, int, int) void
  + readNetworkData() void
   boolean isForcedPause
   UpgradeMenu upgradeMenu
   int receivedWaveMoney
   boolean isMultiplayer
   GameSaveManager gameSaveManager
   int coinsFromNetwork
   int waveFromNetwork
   int wave
   int[] spawnLocation
   Difficulty difficulty
   Player player
   int mapNumber
   boolean isPaused
   boolean isUpgradeMenuVisible
   int winWave
   boolean hasGameStarted
}
class GameMap1 {
  + GameMap1(boolean, boolean) 
  + GameMap1() 
   int mapNumber
}
class GameMap2 {
  + GameMap2(boolean, boolean) 
  + GameMap2() 
   int mapNumber
}
class GameMap3 {
  + GameMap3(boolean, boolean) 
  + GameMap3() 
   int mapNumber
}
class GameMap4 {
  + GameMap4() 
  + GameMap4(boolean, boolean) 
   int mapNumber
}
class GameMap5 {
  + GameMap5(boolean, boolean) 
  + GameMap5() 
   int mapNumber
}
class GameMap6 {
  + GameMap6() 
  + GameMap6(boolean, boolean) 
   int mapNumber
}
class GameMap7 {
  + GameMap7() 
  + GameMap7(boolean, boolean) 
   int mapNumber
}
class GameMap8 {
  + GameMap8() 
  + GameMap8(boolean, boolean) 
   int mapNumber
}
class GameSaveManager {
  + GameSaveManager() 
  - GameSaveManager instance
  - Map~String, Supplier~Tower~~ TOWER_LIST
  + createSaveFile() void
  + set(String, Object) void
  + getAsBoolean(String, boolean) boolean
  + getAsInt(String, int) int
  + reload() void
  + saveTowerData(GameMap) String
  + loadTowerData() void
  + saveFileExists(String) boolean
  + saveGame() void
  + loadGame(GameMap) void
  + get(String) String
  + getAsDouble(String, double) double
  + removeSaveFile() void
   int lastRound
   GameSaveManager instance
   String mapNr
   Map~String, Supplier~Tower~~ TOWER_LIST
   int coins
}
class GoToTutButton {
  + GoToTutButton() 
  + onClick() void
}
class HasSound {
<<Interface>>
  + playSound(GreenfootSound) void
  + playSound(String) void
  + playSoundAndKeep(String) GreenfootSound
   boolean audioHardwareSupported
}
class Helicopter {
  + Helicopter() 
  + upgrade(int) void
   String name
   int animationSpeed
}
class HelicopterPad {
  + HelicopterPad() 
  + place() void
  + addedToWorld(World) void
  + upgrade(int) void
   String name
   int animationSpeed
}
class Hitbox {
  + Hitbox(int, int, Entity) 
  - Entity owner
  - boolean isFollowing
  + updateProportions() void
  + setDebug() void
  + act() void
  + checkTouching() void
  + drawHitbox(int, int, Color, boolean) GreenfootImage
  + followPlayer() void
  + addedToWorld(World) void
  + updateAppearance(boolean) void
  + getSpecificEntitiesInHitbox(Class~T~) List~T~
  + checkHover() boolean
   boolean isFollowing
   Entity owner
   List~Entity~ entitiesInHitbox
}
class HomingProjectile {
  + HomingProjectile() 
  + HomingProjectile(Tower) 
  - int homingRadius
  + addedToWorld(World) void
  + move() void
  + target(Enemy) void
   int homingRadius
}
class HomingTower {
  + HomingTower() 
  + addedToWorld(World) void
  + act() void
  + upgrade(int) void
   String name
   int animationSpeed
}
class HostButton {
  + HostButton(boolean) 
  + onClick() void
  + act() void
}
class IPMenuOverlay {
  + IPMenuOverlay() 
  - boolean connected
  + act() void
  + onClick() void
  - handleKeyboard() void
  + onRemove() void
  - redraw() void
  - typeBackspace() void
  - typeChar(char) void
  - onConnect() void
   boolean connected
   String IP
}
class Ice {
  + Ice(Tower) 
  + Ice() 
  + act() void
  + onHit(Entity) void
}
class IceTower {
  + IceTower() 
  + int slowTimer
  - double destroyAfter
  + double slow
  + addedToWorld(World) void
  + upgrade(int) void
   String name
   double slow
   double destroyAfter
   int animationSpeed
   int slowTimer
}
class ImageDisplay {
  + ImageDisplay(GreenfootImage) 
  + ImageDisplay(int, int, String, Color, Color, Font) 
  + ImageDisplay(String, int, int) 
  + ImageDisplay(String) 
}
class LastPLayed {
  + LastPLayed() 
}
class LoadSaveButton {
  + LoadSaveButton(int) 
  ~ int worldNr
  + onClick() void
   int worldNr
}
class MainClass {
  + MainClass() 
  - boolean isPaused
   boolean isPaused
}
class MapCoordinatesUtilGuy {
  + MapCoordinatesUtilGuy() 
  + act() void
  + printArray(int[][]) void
  + roundCoordinates(int[][]) int[][]
  + addCoordinates(int, int) void
}
class MapPreview {
  + MapPreview(int) 
  - boolean clicked
  + onClick() void
  + act() void
   boolean clicked
   int worldNr
}
class MapSelector {
  + MapSelector() 
  + act() void
  + lastPlayed() void
}
class MapTitlescreen {
  + MapTitlescreen() 
}
class MultiplayerConnection {
<<Interface>>
  + send(String) void
}
class MultiplayerPreview {
  + MultiplayerPreview() 
  + act() void
  + onClick() void
}
class MuteButton {
  + MuteButton() 
  + onClick() void
  + updateApperance() void
}
class NetworkClient {
  + NetworkClient(String, int) 
  + send(String) void
  + setMap(int, String) void
  + run() void
}
class NetworkHost {
  + NetworkHost(int) 
  + run() void
  + send(String) void
}
class NetworkManager {
  - NetworkManager() 
  - boolean isMultiplayer
  - boolean isDisconnected
  - boolean isHost
  - int mapNr
  - NetworkManager instance
  - Difficulty difficulty
  - boolean isConnected
  + startClient(String, int) void
  + sendData(String) void
  + startHost(int) void
  + queueIncomingMessage(String) void
   Difficulty difficulty
   boolean connectionTimedOut
   boolean connectionTimeouted
   boolean active
   boolean isMultiplayer
   boolean isDisconnected
   NetworkManager instance
   int mapNr
   boolean isHost
   boolean isConnected
   ConcurrentLinkedQueue~String~ inboundQueue
}
class NewSaveButton {
  + NewSaveButton(int) 
  + NewSaveButton(int, boolean) 
  - int worldNr
  + onClick() void
   int worldNr
}
class Path {
  + Path(int, int, int) 
  - int nextPathX
  - int nextPathY
  - checkLocations() void
  + addedToWorld(World) void
  + onHover() void
  + onHit(Entity) void
   int nextPathX
   int nextPathY
}
class PauseMenu {
  + PauseMenu() 
  + addedToWorld(World) void
  + onRemove() void
}
class PlayOnButton {
  + PlayOnButton() 
  + onClick() void
}
class Player {
  + Player(int, int) 
  - int coins
  - int health
  + damage(int) void
  + act() void
  + addedToWorld(World) void
  + show(World) void
  + coinCheat() void
  + setCoins(int, boolean) void
   int coins
   int health
}
class PopupScreen {
  + PopupScreen() 
  + onRemove() void
}
class Projectile {
  + Projectile() 
  + Projectile(Tower) 
  - int targetY
  - Tower owner
  - double damage
  - double speed
  - int targetX
  - double piercing
  + onHit(Entity) void
  + act() void
  + addedToWorld(World) void
  + move() void
  + updateIFrames() void
  + target() void
   Tower owner
   double damage
   int targetY
   int targetX
   double speed
   double piercing
}
class QuestionPopup {
  + QuestionPopup(String, Button, Button) 
  - Button rightButton
  - ClosePopupButton closeButton
  - Button leftButton
  # addedToWorld(World) void
  + act() void
  + onRemove() void
   Button leftButton
   Button rightButton
   ClosePopupButton closeButton
}
class RangeDisplay {
  + RangeDisplay(Tower, double, boolean) 
  - boolean isFollowing
  + setRangeVisibility(boolean, Color) void
  + act() void
  + followTower() void
  + displayRange(Color) void
  - getGreenfootImage(int, Color) GreenfootImage
  + updateRange(double) void
   boolean isFollowing
}
class RestartMultiplayerButton {
  + RestartMultiplayerButton() 
  + onClick() void
}
class RetryButton {
  + RetryButton() 
  + onClick() void
}
class Rocket {
  + Rocket() 
  + Rocket(Tower) 
  - handleVisual() void
  + onHit(Entity) void
}
class Rocketlauncher {
  + Rocketlauncher() 
  + upgrade(int) void
  + addedToWorld(World) void
   String name
   int animationSpeed
}
class SaveManager {
  - SaveManager() 
  - SaveManager instance
  + getAsDouble(String, double) double
  + get(String) String
  + getAsInt(String, int) int
  + getAsBoolean(String, boolean) boolean
  + set(String, Object) void
  + reload() void
   int lastMap
   int volume
   SaveManager instance
   boolean tutorialStatus
   boolean soundOn
}
class Saveable {
<<Interface>>
  + saveAll(String, Properties) void
  + loadSave(String) Properties
  + getValue(Properties, String) String
  + saveValue(String, String, Object) void
  + getDouble(Properties, String, double) double
  + getBoolean(Properties, String, boolean) boolean
  + getInt(Properties, String, int) int
}
class SellButton {
  + SellButton(Tower, Player) 
  + onClick() void
}
class SettingsButton {
  + SettingsButton() 
  + SettingsButton(PopupScreen) 
  + onClick() void
}
class SettingsPopup {
  + SettingsPopup() 
  + addedToWorld(World) void
  + act() void
  + onRemove() void
}
class SlotMachineButton {
  + SlotMachineButton() 
  + onClick() void
}
class SlotMachineButtonGamble {
  + SlotMachineButtonGamble() 
  + onClick() void
}
class Sniper {
  + Sniper() 
  + upgrade(int) void
  + shoot(Enemy) void
  + act() void
   String name
   int animationSpeed
}
class SongButton {
  + SongButton(String) 
  + act() void
  + onClick() void
}
class SongDropDown {
  + SongDropDown() 
  - boolean isOpen
  - closeDropdown() void
  - openDropdown() void
  - draw() void
  + onClick() void
  + act() void
   boolean isOpen
}
class SoundSettings {
  - SoundSettings() 
  - int masterVolume
  - boolean muted
  + syncGlobalVolume() void
  + increaseVolume(int) void
  + addRegisteredSound(GreenfootSound) void
  + unMuteAllSounds() void
  + muteALLSound() void
  + decreaseVolume(int) void
   int masterVolume
   SoundSettings instance
   boolean muted
}
class StartingButton {
  + StartingButton() 
  + onClick() void
}
class TestBloon {
  + TestBloon(double, int) 
   String name
}
class TestProjectile {
  + TestProjectile(Tower) 
  + TestProjectile() 
}
class TestTower {
  + TestTower() 
  + addedToWorld(World) void
  + act() void
  + upgrade(int) void
   String name
   int animationSpeed
}
class TextBlock {
  + TextBlock(String, double) 
  - double speed
  + addedToWorld(World) void
  + goUp() void
  - updateImage() void
  + act() void
   double speed
}
class Textboard {
  + Textboard(int, int) 
   String text
}
class Tower {
  + Tower(int, boolean, int, int, int, int, int, int) 
  - Enemy targetedEnemy
  # int shootingDelayCounter
  - Color colorRed
  # int[] upgrade2Prices
  # String[] upgradeDescription1
  # int price
  # int upgrade3
  # int shootingDelay
  - int frameCounter
  # double projectileDamage
  - double range
  - boolean canPlace
  - Color colorGrey
  # double projectileSpeed
  # int[] upgrade3Prices
  - String spriteName
  # int[] upgrade1Prices
  - List~String~ frameList
  # int projectileIFrames
  # String[] upgradeDescription2
  # double projectilePiercing
  - boolean isPlacing
  # int upgrade1
  # int upgrade2
  - String spritePath
  # String[] upgradeDescription3
  - RangeDisplay rangeDisplay
  # Projectile projectileToShoot
  + onHit(Entity) void
  # canPlace() boolean
  + upgrade3(boolean) void
  + upgrade1() void
  + createProjectile() Projectile
  + onClick() void
  + addedToWorld(World) void
  + upgrade2() void
  + startAnimation() void
  + upgrade1(boolean) void
  + upgrade3() void
  + canShoot() boolean
  + isAnimating(boolean) void
  + place() void
  + followCursor() void
  + checkHover(boolean) void
  + upgrade2(boolean) void
  + onUpgrade(int) void
  + onHover() void
  + shoot(Enemy) void
  + checkPlacement() void
  + setTargetedEnemy() void
  + act() void
  + onUnhover() void
  + shoot(Enemy, String) void
  ~ upgrade(int) void
   List~String~ frameList
   int price
   String spritePath
   int upgrade3
   String upgradeDescription3
   double projectilePiercing
   Color colorGrey
   int upgrade1
   String spriteName
   double projectileSpeed
   Enemy targetedEnemyManual
   Projectile projectileToShoot
   Color colorRed
   int shootingDelayCounter
   int frameCounter
   int animationSpeed
   double projectileDamage
   double range
   RangeDisplay rangeDisplay
   int upgrade2
   int[] upgrade1Prices
   String upgradeDescription2
   boolean isPlacing
   String name
   int[] upgrade3Prices
   String upgradeDescription1
   int[] upgrade2Prices
   Enemy targetedEnemy
   boolean canPlace
   int projectileIFrames
   int currentFrameIndex
   int shootingDelay
}
class TowerSelector {
  + TowerSelector(Supplier~Tower~) 
  + onClick() void
  + updatePrice() void
  + act() void
  + addedToWorld(World) void
}
class TowerSelectorSpawner {
  + TowerSelectorSpawner() 
  # addedToWorld(World) void
}
class TrapTower {
  + TrapTower() 
  + onHit(Entity) void
  + addedToWorld(World) void
  + checkPlacement() void
  + act() void
  + shoot(Enemy) void
  + onClick() void
  + upgrade(int) void
   String name
   int animationSpeed
}
class Tuple~A, B~ {
  + Tuple(A, B) 
  - B second
  - A first
   A first
   B second
}
class TutorialHud {
  + TutorialHud() 
  + onClick() void
}
class TutorialMap {
  + TutorialMap() 
  - boolean isTutorialPopupActive
  + act() void
  + textBoardPlacer() void
  - showTutorialBoard() void
   boolean isTutorialPopupActive
   int mapNumber
}
class TutorialText {
  + TutorialText() 
}
class Upgrade {
  + Upgrade() 
  - int level
  - String description
   int level
   String description
}
class UpgradeDescriptionOverlay {
  + UpgradeDescriptionOverlay(Tower, int, int) 
  - int path
   int path
}
class UpgradeMenu {
  + UpgradeMenu(Tower) 
  - Tower tower
  # addedToWorld(World) void
  + delete() void
   Tower tower
}
class UpgradePath {
  + UpgradePath(Tower, int) 
  + updateText(int) void
  - removeOverlay() void
  + onClick() void
  + updatePrice() void
  + updatePrice(int) void
  + checkText() void
  + act() void
  # addedToWorld(World) void
  + onRemove() void
   int otherUpgradeA
   int[]? upgradePrices
   int currentUpgradeLevel
   int maxPath
   int otherUpgradeB
}
class VolumeSlider {
  + VolumeSlider() 
  - updateVolumeFromMousePosition(MouseInfo) void
  + act() void
  + onClick() void
  - isMouseOverSlider(MouseInfo) boolean
  - redrawSlider() void
}
class WaveManager {
  - WaveManager() 
  - Map~String, Supplier~Enemy~~ ENEMY_LIST
  + generateWave(int) List~Enemy~
  - mapCheck(int) int
   Map~String, Supplier~Enemy~~ ENEMY_LIST
   WaveManager instance
}
class WaveResetButton {
  + WaveResetButton() 
  + onClick() void
}

AntiCheat  -->  MainClass 
BackButton  -->  Button 
Button  ..>  Clickable 
ClickawayImageDisplay  ..>  Clickable 
ClickawayImageDisplay  -->  ImageDisplay 
ClientButton  -->  Button 
ClosePopupButton  -->  Button 
DevNotesMap  -->  CustomWorld 
DeveloperNotesButton  -->  Button 
GameMap  -->  Difficulty 
DifficultySelector  -->  Button 
DifficultySelectorPopup  -->  PopupScreen 
Enemy  -->  Entity 
EnemyHitbox  -->  Hitbox 
EnemyLevel1  -->  Enemy 
EnemyLevel2  -->  Enemy 
EnemyLevel3  -->  Enemy 
EnemyLevel4  -->  Enemy 
EnemyLevel5  -->  Enemy 
EnemyLevel6  -->  Enemy 
Entity  -->  MainClass 
ExampleActor  ..>  Clickable 
ExampleActor  ..>  HasSound 
ExampleActor  -->  MainClass 
Explosion  ..>  HasSound 
Explosion  -->  Projectile 
FlameProjectile  -->  Projectile 
Flamethrower  -->  Tower 
Gambling  -->  CustomWorld 
GamblingWonCredits  -->  CustomWorld 
GamblingWonCredits  ..>  HasSound 
GameMap  -->  CustomWorld 
GameMap  ..>  HasSound 
GameMap1  -->  GameMap 
GameMap2  -->  GameMap 
GameMap3  -->  GameMap 
GameMap4  -->  GameMap 
GameMap5  -->  GameMap 
GameMap6  -->  GameMap 
GameMap7  -->  GameMap 
GameMap8  -->  GameMap 
GameSaveManager  ..>  Saveable 
GoToTutButton  -->  Button 
Helicopter  -->  Tower 
HelicopterPad  -->  Tower 
Hitbox  -->  MainClass 
HomingProjectile  -->  Projectile 
HomingTower  -->  Tower 
HostButton  -->  Button 
IPMenuOverlay  ..>  Clickable 
IPMenuOverlay  -->  PopupScreen 
Ice  ..>  HasSound 
Ice  -->  Projectile 
IceTower  -->  Tower 
ImageDisplay  -->  MainClass 
LastPLayed  -->  MainClass 
LoadSaveButton  -->  Button 
MapPreview  ..>  Clickable 
MapSelector  -->  CustomWorld 
MapSelector  ..>  HasSound 
MapTitlescreen  -->  CustomWorld 
MultiplayerPreview  ..>  Clickable 
MuteButton  -->  Button 
NetworkClient  ..>  MultiplayerConnection 
NetworkHost  ..>  MultiplayerConnection 
NewSaveButton  -->  Button 
Path  -->  Entity 
PauseMenu  -->  PopupScreen 
PlayOnButton  -->  Button 
Player  ..>  HasSound 
Player  -->  MainClass 
Projectile  -->  Entity 
Projectile  ..>  HasSound 
QuestionPopup  -->  PopupScreen 
RangeDisplay  -->  MainClass 
RestartMultiplayerButton  -->  Button 
RetryButton  -->  Button 
Rocket  ..>  HasSound 
Rocket  -->  Projectile 
Rocketlauncher  -->  Tower 
SaveManager  ..>  Saveable 
SellButton  -->  Button 
SellButton  ..>  HasSound 
SettingsButton  -->  Button 
SettingsPopup  -->  PopupScreen 
SlotMachineButton  -->  Button 
SlotMachineButtonGamble  -->  Button 
Sniper  -->  Tower 
SongButton  ..>  Clickable 
SongButton  ..>  HasSound 
SongButton  -->  MainClass 
SongDropDown  ..>  Clickable 
SongDropDown  -->  MainClass 
StartingButton  -->  Button 
TestBloon  -->  Enemy 
TestProjectile  -->  Projectile 
TestTower  -->  Tower 
Textboard  -->  MainClass 
Tower  ..>  Animations 
Tower  ..>  Clickable 
Tower  -->  Entity 
Tower  ..>  HasSound 
TowerSelector  ..>  Clickable 
TowerSelector  -->  MainClass 
TowerSelectorSpawner  -->  MainClass 
TrapTower  ..>  Clickable 
TrapTower  -->  Tower 
TutorialHud  -->  Button 
TutorialHud  ..>  Clickable 
TutorialMap  -->  GameMap 
TutorialText  -->  CustomWorld 
UpgradePath  ..>  Clickable 
VolumeSlider  ..>  Clickable 
VolumeSlider  -->  MainClass 
WaveManager  -->  MainClass 
WaveResetButton  -->  Button 
WaveResetButton  ..>  Clickable 
