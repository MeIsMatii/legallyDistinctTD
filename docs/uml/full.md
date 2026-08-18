```mermaid
classDiagram
direction BT
class Animations {
<<Interface>>
  + addedToWorld(World) void
  + playAnimation() void
  + startAnimation() void
  + isAnimating(boolean) void
  + generateFrameList() void
   List~String~ frameList
   String spritePath
   String spriteName
   int animationSpeed
   int currentFrameIndex
   int frameCounter
}
class AntiCheat {
  - AntiCheat() 
  - checkHoneypot() void
  + update() void
  - tick() void
  - checkRunningProcesses() void
  - checkValueCeiling() void
}
class BackButton {
  + BackButton() 
  + getBack(World) void
  + onClick() void
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
  + onClick() void
  + act() void
  + onRemove() void
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
  + isTouching(Class~?~) boolean
  + act() void
  + followMouse() void
}
class CustomWorld {
  + CustomWorld(int, int, int) 
  + CustomWorld() 
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
  + values() Difficulty[]
  + valueOf(String) Difficulty
   int winWave
}
class DifficultySelector {
  + DifficultySelector(GameMap, Difficulty) 
  + onClick() void
}
class DifficultySelectorPopup {
  + DifficultySelectorPopup(GameMap) 
  + act() void
  + onRemove() void
  + addedToWorld(World) void
}
class Enemy {
  + Enemy(double, int) 
  ~ int initialLives
  ~ double lives
  ~ double speed
  + onHover() void
  + spawnHitbox(int, int) void
  + applySlow(double, int) void
  + findPath() void
  + updateSlow() void
  + addedToWorld(World) void
  + act() void
  + damage(double) void
  + onHit(Entity) void
  + moveTo(int, int) void
   String name
   double lives
   int initialLives
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
  + setLocation(int, int) void
  + onUnhover() void
  + onHover() void
  + act() void
  + checkHover(boolean) void
  + addedToWorld(World) void
  + setLocation(int, int, boolean) void
  + spawnHitbox(int, int) void
  + onHit(Entity) void
   String uniqueId
}
class ExampleActor {
  + ExampleActor() 
  + act() void
  + onClick() void
  - updateAppearance() void
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
  + FlameProjectile() 
  + FlameProjectile(Tower) 
  + move() void
}
class Flamethrower {
  + Flamethrower() 
  + act() void
  + shoot(Enemy) void
  + addedToWorld(World) void
  - recharge() void
  + upgrade(int) void
   String name
   int animationSpeed
}
class Gambling {
  + Gambling() 
  - ImageDisplay slot1
  - ImageDisplay slot3
  - ImageDisplay slot2
   ImageDisplay slot1
   ImageDisplay slot3
   ImageDisplay slot2
}
class GamblingWonCredits {
  + GamblingWonCredits() 
  + credits() void
  + act() void
}
class GameMap {
  + GameMap() 
  + GameMap(boolean, boolean) 
  - boolean isPaused
  - boolean isMultiplayer
  - Player player
  - boolean hasGameStarted
  - Difficulty difficulty
  - int[] spawnLocation
  - UpgradeMenu upgradeMenu
  - GameSaveManager gameSaveManager
  - int wave
  - boolean isUpgradeMenuVisible
  - boolean isForcedPause
  - int receivedWaveMoney
  + processCommand(String) void
  + spawnTowerFromNetwork(String, String, int, int) void
  + upgradeTowerFromNetwork(String, int, int) void
  + targetEnemyFromNetwork(String, String) void
  + addPath(int[][]) void
  + spawnWave(int, int) void
  + readNetworkData() void
  + isPathValid(int, int) boolean
  + pauseObjects(boolean, boolean) void
  + spawnProjectileFromNetwork(String, String, String) void
  + checkPaused() void
  + resetWave() void
  + startHost() void
  + onContinue() void
  + removeEntityFromNetwork(String) void
  + addHud() void
  + act() void
  + pauseObjects() void
  + setUpgradeMenuVisibility(boolean, Tower) void
  + moveEntityFromNetwork(String, int, int) void
  + damageEnemyFromNetwork(String, double) void
  + spawnEnemyFromNetwork(String, String) void
  + Wavecheat() void
  + removeDeadEnemies() void
  + showWave() void
   UpgradeMenu upgradeMenu
   int winWave
   GameSaveManager gameSaveManager
   int receivedWaveMoney
   int mapNumber
   int[] spawnLocation
   int waveFromNetwork
   int wave
   int coinsFromNetwork
   Difficulty difficulty
   boolean isForcedPause
   boolean isMultiplayer
   boolean hasGameStarted
   Player player
   boolean isUpgradeMenuVisible
   boolean isPaused
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
  + GameMap3() 
  + GameMap3(boolean, boolean) 
   int mapNumber
}
class GameMap4 {
  + GameMap4(boolean, boolean) 
  + GameMap4() 
   int mapNumber
}
class GameMap5 {
  + GameMap5() 
  + GameMap5(boolean, boolean) 
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
  + saveFileExists(String) boolean
  + get(String) String
  + loadGame(GameMap) void
  + reload() void
  + saveTowerData(GameMap) String
  + removeSaveFile() void
  + set(String, Object) void
  + getAsDouble(String, double) double
  + getAsInt(String, int) int
  + loadTowerData() void
  + createSaveFile() void
  + getAsBoolean(String, boolean) boolean
  + saveGame() void
   GameSaveManager instance
   int coins
   String mapNr
   Map~String, Supplier~Tower~~ TOWER_LIST
   int lastRound
}
class GoToTutButton {
  + GoToTutButton() 
  + onClick() void
}
class HasSound {
<<Interface>>
  + playSound(String) void
  + playSound(GreenfootSound) void
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
  + addedToWorld(World) void
  + place() void
  + upgrade(int) void
   String name
   int animationSpeed
}
class Hitbox {
  + Hitbox(int, int, Entity) 
  - boolean isFollowing
  - Entity owner
  + updateProportions() void
  + addedToWorld(World) void
  + act() void
  + updateAppearance(boolean) void
  + checkTouching() void
  + checkHover() boolean
  + drawHitbox(int, int, Color, boolean) GreenfootImage
  + getSpecificEntitiesInHitbox(Class~T~) List~T~
  + followPlayer() void
  + setDebug() void
   Entity owner
   List~Entity~ entitiesInHitbox
   boolean isFollowing
}
class HomingProjectile {
  + HomingProjectile() 
  + HomingProjectile(Tower) 
  - int homingRadius
  + move() void
  + target(Enemy) void
  + addedToWorld(World) void
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
  - typeBackspace() void
  + onClick() void
  - onConnect() void
  + onRemove() void
  + act() void
  - typeChar(char) void
  - redraw() void
  - handleKeyboard() void
   String IP
   boolean connected
}
class Ice {
  + Ice(Tower) 
  + Ice() 
  + onHit(Entity) void
  + act() void
}
class IceTower {
  + IceTower() 
  + int slowTimer
  + double slow
  - double destroyAfter
  + addedToWorld(World) void
  + upgrade(int) void
   double slow
   String name
   int slowTimer
   int animationSpeed
   double destroyAfter
}
class ImageDisplay {
  + ImageDisplay(int, int, String, Color, Color, Font) 
  + ImageDisplay(String, int, int) 
  + ImageDisplay(GreenfootImage) 
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
  + addCoordinates(int, int) void
  + act() void
  + roundCoordinates(int[][]) int[][]
  + printArray(int[][]) void
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
  + lastPlayed() void
  + act() void
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
  + updateApperance() void
  + onClick() void
}
class NetworkClient {
  + NetworkClient(String, int) 
  + setMap(int, String) void
  + run() void
  + send(String) void
}
class NetworkHost {
  + NetworkHost(int) 
  + send(String) void
  + run() void
}
class NetworkManager {
  - NetworkManager() 
  - boolean isMultiplayer
  - Difficulty difficulty
  - boolean isDisconnected
  - boolean isConnected
  - NetworkManager instance
  - int mapNr
  - boolean isHost
  + startHost(int) void
  + startClient(String, int) void
  + sendData(String) void
  + queueIncomingMessage(String) void
   Difficulty difficulty
   boolean isConnected
   int mapNr
   NetworkManager instance
   boolean isDisconnected
   boolean active
   boolean isMultiplayer
   boolean connectionTimeouted
   boolean isHost
   boolean connectionTimedOut
   ConcurrentLinkedQueue~String~ inboundQueue
}
class NewSaveButton {
  + NewSaveButton(int, boolean) 
  + NewSaveButton(int) 
  - int worldNr
  + onClick() void
   int worldNr
}
class Path {
  + Path(int, int, int) 
  - int nextPathX
  - int nextPathY
  + addedToWorld(World) void
  + onHit(Entity) void
  - checkLocations() void
  + onHover() void
   int nextPathX
   int nextPathY
}
class PauseMenu {
  + PauseMenu() 
  + onRemove() void
  + addedToWorld(World) void
}
class PlayOnButton {
  + PlayOnButton() 
  + onClick() void
}
class Player {
  + Player(int, int) 
  - int health
  - int coins
  + setCoins(int, boolean) void
  + coinCheat() void
  + act() void
  + show(World) void
  + damage(int) void
  + addedToWorld(World) void
   int coins
   int health
}
class PopupScreen {
  + PopupScreen() 
  + onRemove() void
}
class Projectile {
  + Projectile(Tower) 
  + Projectile() 
  - double speed
  - int targetX
  - int targetY
  - Tower owner
  - double piercing
  - double damage
  + updateIFrames() void
  + act() void
  + onHit(Entity) void
  + target() void
  + addedToWorld(World) void
  + move() void
   Tower owner
   double damage
   int targetY
   int targetX
   double speed
   double piercing
}
class QuestionPopup {
  + QuestionPopup(String, Button, Button) 
  - ClosePopupButton closeButton
  - Button leftButton
  - Button rightButton
  + onRemove() void
  + act() void
  # addedToWorld(World) void
   Button leftButton
   Button rightButton
   ClosePopupButton closeButton
}
class RangeDisplay {
  + RangeDisplay(Tower, double, boolean) 
  - boolean isFollowing
  + setRangeVisibility(boolean, Color) void
  - getGreenfootImage(int, Color) GreenfootImage
  + updateRange(double) void
  + act() void
  + followTower() void
  + displayRange(Color) void
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
  + Rocket(Tower) 
  + Rocket() 
  + onHit(Entity) void
  - handleVisual() void
}
class Rocketlauncher {
  + Rocketlauncher() 
  + addedToWorld(World) void
  + upgrade(int) void
   String name
   int animationSpeed
}
class SaveManager {
  - SaveManager() 
  - SaveManager instance
  + getAsBoolean(String, boolean) boolean
  + reload() void
  + get(String) String
  + getAsDouble(String, double) double
  + getAsInt(String, int) int
  + set(String, Object) void
   SaveManager instance
   int volume
   int lastMap
   boolean soundOn
   boolean tutorialStatus
}
class Saveable {
<<Interface>>
  + saveAll(String, Properties) void
  + getValue(Properties, String) String
  + getBoolean(Properties, String, boolean) boolean
  + loadSave(String) Properties
  + getInt(Properties, String, int) int
  + getDouble(Properties, String, double) double
  + saveValue(String, String, Object) void
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
  + onRemove() void
  + act() void
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
  + shoot(Enemy) void
  + act() void
  + upgrade(int) void
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
  - draw() void
  - openDropdown() void
  + onClick() void
  - closeDropdown() void
  + act() void
   boolean isOpen
}
class SoundSettings {
  - SoundSettings() 
  - int masterVolume
  - boolean muted
  + decreaseVolume(int) void
  + increaseVolume(int) void
  + unMuteAllSounds() void
  + syncGlobalVolume() void
  + muteALLSound() void
  + addRegisteredSound(GreenfootSound) void
   boolean muted
   int masterVolume
   SoundSettings instance
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
  + act() void
  + addedToWorld(World) void
  + upgrade(int) void
   String name
   int animationSpeed
}
class TextBlock {
  + TextBlock(String, double) 
  - double speed
  + addedToWorld(World) void
  - updateImage() void
  + act() void
  + goUp() void
   double speed
}
class Textboard {
  + Textboard(int, int) 
   String text
}
class Tower {
  + Tower(int, boolean, int, int, int, int, int, int) 
  # int[] upgrade1Prices
  - boolean isPlacing
  # int upgrade3
  # int shootingDelay
  - Color colorGrey
  # String[] upgradeDescription1
  # int shootingDelayCounter
  # int projectileIFrames
  # String[] upgradeDescription3
  - String spriteName
  - int frameCounter
  - boolean canPlace
  - Color colorRed
  # double projectileSpeed
  - RangeDisplay rangeDisplay
  # int[] upgrade2Prices
  # double projectilePiercing
  - double range
  # int upgrade1
  # double projectileDamage
  - List~String~ frameList
  # String[] upgradeDescription2
  # int[] upgrade3Prices
  - String spritePath
  # Projectile projectileToShoot
  # int upgrade2
  # int price
  - Enemy targetedEnemy
  + onUnhover() void
  + upgrade1() void
  + setTargetedEnemy() void
  + checkPlacement() void
  + upgrade3() void
  + shoot(Enemy) void
  + isAnimating(boolean) void
  + upgrade1(boolean) void
  + shoot(Enemy, String) void
  + upgrade2(boolean) void
  + act() void
  + startAnimation() void
  + upgrade2() void
  + checkHover(boolean) void
  + createProjectile() Projectile
  + onHover() void
  + followCursor() void
  + onUpgrade(int) void
  + onClick() void
  + canShoot() boolean
  + place() void
  + addedToWorld(World) void
  + upgrade(int) void
  + onHit(Entity) void
  # canPlace() boolean
  + upgrade3(boolean) void
   List~String~ frameList
   boolean isPlacing
   int animationSpeed
   int frameCounter
   String spritePath
   int shootingDelayCounter
   int upgrade2
   String upgradeDescription3
   Color colorGrey
   double projectileSpeed
   boolean canPlace
   String spriteName
   int[] upgrade1Prices
   int projectileIFrames
   int[] upgrade2Prices
   Enemy targetedEnemyManual
   double projectilePiercing
   int currentFrameIndex
   int shootingDelay
   Projectile projectileToShoot
   Color colorRed
   double range
   int price
   int[] upgrade3Prices
   double projectileDamage
   RangeDisplay rangeDisplay
   int upgrade1
   int upgrade3
   String upgradeDescription2
   String name
   String upgradeDescription1
   Enemy targetedEnemy
}
class TowerSelector {
  + TowerSelector(Supplier~Tower~) 
  + onClick() void
  + act() void
  + updatePrice() void
  + addedToWorld(World) void
}
class TowerSelectorSpawner {
  + TowerSelectorSpawner() 
  # addedToWorld(World) void
}
class TrapTower {
  + TrapTower() 
  + act() void
  + onHit(Entity) void
  + onClick() void
  + addedToWorld(World) void
  + shoot(Enemy) void
  + upgrade(int) void
  + checkPlacement() void
   String name
   int animationSpeed
}
class Tuple~A, B~ {
  + Tuple(A, B) 
  - A first
  - B second
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
  + textBoardPlacer() void
  - showTutorialBoard() void
  + act() void
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
   String description
   int level
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
  # addedToWorld(World) void
  + act() void
  + onClick() void
  + checkText() void
  + onRemove() void
  + updatePrice() void
  - removeOverlay() void
  + updatePrice(int) void
  + updateText(int) void
   int[]? upgradePrices
   int maxPath
   int otherUpgradeB
   int otherUpgradeA
   int currentUpgradeLevel
}
class VolumeSlider {
  + VolumeSlider() 
  - redrawSlider() void
  + onClick() void
  + act() void
  - isMouseOverSlider(MouseInfo) boolean
  - updateVolumeFromMousePosition(MouseInfo) void
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
BackButton  ..>  MapSelector : «create»
Button  ..>  Clickable 
Button "1" *--> "owner 1" PopupScreen 
ClickawayImageDisplay  ..>  Clickable 
ClickawayImageDisplay  -->  ImageDisplay 
ClientButton  -->  Button 
ClientButton  ..>  IPMenuOverlay : «create»
ClosePopupButton  -->  Button 
DevNotesMap  ..>  BackButton : «create»
DevNotesMap  -->  CustomWorld 
DeveloperNotesButton  -->  Button 
DeveloperNotesButton  ..>  DevNotesMap : «create»
GameMap  -->  Difficulty 
DifficultySelector  -->  Button 
DifficultySelector "1" *--> "difficulty 1" Difficulty 
DifficultySelector "1" *--> "map 1" GameMap 
DifficultySelectorPopup "1" *--> "closeButton 1" ClosePopupButton 
DifficultySelectorPopup  ..>  ClosePopupButton : «create»
DifficultySelectorPopup  ..>  DifficultySelector : «create»
DifficultySelectorPopup "1" *--> "map 1" GameMap 
DifficultySelectorPopup  -->  PopupScreen 
Enemy  ..>  EnemyHitbox : «create»
Enemy  -->  Entity 
EnemyHitbox  -->  Hitbox 
EnemyLevel1  -->  Enemy 
EnemyLevel2  -->  Enemy 
EnemyLevel3  -->  Enemy 
EnemyLevel4  -->  Enemy 
EnemyLevel5  -->  Enemy 
EnemyLevel6  -->  Enemy 
Entity  ..>  Hitbox : «create»
Entity  -->  MainClass 
ExampleActor  ..>  Clickable 
ExampleActor  ..>  HasSound 
ExampleActor  -->  MainClass 
Explosion  ..>  HasSound 
Explosion  -->  Projectile 
FlameProjectile  -->  Projectile 
Flamethrower  ..>  FlameProjectile : «create»
Flamethrower  -->  Tower 
Gambling  ..>  BackButton : «create»
Gambling  -->  CustomWorld 
Gambling "1" *--> "slot1 1" ImageDisplay 
Gambling  ..>  ImageDisplay : «create»
Gambling  ..>  SlotMachineButtonGamble : «create»
GamblingWonCredits  ..>  BackButton : «create»
GamblingWonCredits  -->  CustomWorld 
GamblingWonCredits  ..>  HasSound 
GamblingWonCredits  ..>  TextBlock : «create»
GameMap  ..>  BackButton : «create»
GameMap "1" *--> "cursor 1" Cursor 
GameMap  ..>  Cursor : «create»
GameMap  -->  CustomWorld 
GameMap "1" *--> "difficulty 1" Difficulty 
GameMap "1" *--> "aliveEnemies *" Enemy 
GameMap  ..>  GameSaveManager : «create»
GameMap "1" *--> "gameSaveManager 1" GameSaveManager 
GameMap  ..>  HasSound 
GameMap  ..>  Path : «create»
GameMap  ..>  PauseMenu : «create»
GameMap  ..>  PlayOnButton : «create»
GameMap  ..>  Player : «create»
GameMap "1" *--> "player 1" Player 
GameMap  ..>  QuestionPopup : «create»
GameMap  ..>  RestartMultiplayerButton : «create»
GameMap  ..>  TowerSelectorSpawner : «create»
GameMap  ..>  UpgradeMenu : «create»
GameMap "1" *--> "upgradeMenu 1" UpgradeMenu 
GameMap "1" *--> "waveManager 1" WaveManager 
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
GoToTutButton  ..>  MapTitlescreen : «create»
Helicopter  -->  Tower 
HelicopterPad  ..>  Helicopter : «create»
HelicopterPad  -->  Tower 
Hitbox "1" *--> "owner 1" Entity 
Hitbox  -->  MainClass 
HomingProjectile "1" *--> "targetedEnemy 1" Enemy 
HomingProjectile  -->  Projectile 
HomingTower  ..>  HomingProjectile : «create»
HomingTower  -->  Tower 
HostButton  -->  Button 
IPMenuOverlay  ..>  Clickable 
IPMenuOverlay  -->  PopupScreen 
Ice  ..>  HasSound 
Ice  -->  Projectile 
IceTower  ..>  Ice : «create»
IceTower  -->  Tower 
ImageDisplay  -->  MainClass 
LastPLayed  -->  MainClass 
LoadSaveButton  -->  Button 
LoadSaveButton  ..>  GameMap1 : «create»
LoadSaveButton  ..>  GameMap2 : «create»
LoadSaveButton  ..>  GameMap3 : «create»
LoadSaveButton  ..>  GameMap4 : «create»
LoadSaveButton  ..>  GameMap5 : «create»
LoadSaveButton  ..>  GameMap6 : «create»
LoadSaveButton  ..>  GameMap7 : «create»
LoadSaveButton  ..>  GameMap8 : «create»
MapPreview  ..>  Clickable 
MapPreview  ..>  ClosePopupButton : «create»
MapPreview  ..>  GameSaveManager : «create»
MapPreview  ..>  LoadSaveButton : «create»
MapPreview  ..>  NewSaveButton : «create»
MapPreview  ..>  QuestionPopup : «create»
MapSelector  -->  CustomWorld 
MapSelector  ..>  HasSound 
MapSelector  ..>  ImageDisplay : «create»
MapSelector  ..>  LastPLayed : «create»
MapSelector  ..>  MapPreview : «create»
MapSelector  ..>  MultiplayerPreview : «create»
MapSelector  ..>  SettingsButton : «create»
MapSelector  ..>  SlotMachineButton : «create»
MapTitlescreen  -->  CustomWorld 
MapTitlescreen  ..>  StartingButton : «create»
MultiplayerPreview  ..>  Clickable 
MultiplayerPreview  ..>  ClientButton : «create»
MultiplayerPreview  ..>  ClosePopupButton : «create»
MultiplayerPreview  ..>  HostButton : «create»
MultiplayerPreview  ..>  QuestionPopup : «create»
MuteButton  -->  Button 
NetworkClient  ..>  GameMap1 : «create»
NetworkClient  ..>  GameMap2 : «create»
NetworkClient  ..>  GameMap3 : «create»
NetworkClient  ..>  GameMap4 : «create»
NetworkClient  ..>  GameMap5 : «create»
NetworkClient  ..>  GameMap6 : «create»
NetworkClient  ..>  GameMap7 : «create»
NetworkClient  ..>  GameMap8 : «create»
NetworkClient  ..>  MultiplayerConnection 
NetworkHost  ..>  MultiplayerConnection 
NetworkManager "1" *--> "difficulty 1" Difficulty 
NetworkManager "1" *--> "activeWorker 1" MultiplayerConnection 
NetworkManager  ..>  NetworkClient : «create»
NetworkManager  ..>  NetworkHost : «create»
NewSaveButton  -->  Button 
NewSaveButton  ..>  DifficultySelectorPopup : «create»
NewSaveButton  ..>  GameMap1 : «create»
NewSaveButton  ..>  GameMap2 : «create»
NewSaveButton  ..>  GameMap3 : «create»
NewSaveButton  ..>  GameMap4 : «create»
NewSaveButton  ..>  GameMap5 : «create»
NewSaveButton  ..>  GameMap6 : «create»
NewSaveButton  ..>  GameMap7 : «create»
NewSaveButton  ..>  GameMap8 : «create»
Path  -->  Entity 
Path  ..>  Hitbox : «create»
PauseMenu "1" *--> "backButton 1" BackButton 
PauseMenu  ..>  BackButton : «create»
PauseMenu "1" *--> "playOnButton 1" PlayOnButton 
PauseMenu  ..>  PlayOnButton : «create»
PauseMenu  -->  PopupScreen 
PauseMenu  ..>  RetryButton : «create»
PauseMenu "1" *--> "retryButton 1" RetryButton 
PauseMenu "1" *--> "settingsButton 1" SettingsButton 
PauseMenu  ..>  SettingsButton : «create»
PauseMenu  ..>  WaveResetButton : «create»
PauseMenu "1" *--> "waveResetButton 1" WaveResetButton 
PlayOnButton  -->  Button 
Player  ..>  BackButton : «create»
Player  ..>  HasSound 
Player  ..>  ImageDisplay : «create»
Player  -->  MainClass 
Player  ..>  QuestionPopup : «create»
Player  ..>  RetryButton : «create»
Projectile "1" *--> "hitEnemies *" Enemy 
Projectile  -->  Entity 
Projectile  ..>  HasSound 
Projectile "1" *--> "owner 1" Tower 
QuestionPopup "1" *--> "rightButton 1" Button 
QuestionPopup  ..>  ClosePopupButton : «create»
QuestionPopup "1" *--> "closeButton 1" ClosePopupButton 
QuestionPopup  -->  PopupScreen 
RangeDisplay  -->  MainClass 
RangeDisplay "1" *--> "OWNER 1" Tower 
RestartMultiplayerButton  -->  Button 
RetryButton  -->  Button 
Rocket  ..>  HasSound 
Rocket  ..>  ImageDisplay : «create»
Rocket  -->  Projectile 
Rocketlauncher  ..>  Rocket : «create»
Rocketlauncher  -->  Tower 
SaveManager  ..>  Saveable 
SellButton  -->  Button 
SellButton  ..>  HasSound 
SellButton "1" *--> "player 1" Player 
SellButton "1" *--> "tower 1" Tower 
SettingsButton  -->  Button 
SettingsButton  ..>  SettingsPopup : «create»
SettingsPopup "1" *--> "closeButton 1" ClosePopupButton 
SettingsPopup  ..>  ClosePopupButton : «create»
SettingsPopup "1" *--> "DeveloperNotesButton 1" DeveloperNotesButton 
SettingsPopup  ..>  DeveloperNotesButton : «create»
SettingsPopup "1" *--> "GoToTutButton 1" GoToTutButton 
SettingsPopup  ..>  GoToTutButton : «create»
SettingsPopup  ..>  MuteButton : «create»
SettingsPopup "1" *--> "muteButton 1" MuteButton 
SettingsPopup  -->  PopupScreen 
SettingsPopup  ..>  SongDropDown : «create»
SettingsPopup "1" *--> "songDropDown 1" SongDropDown 
SettingsPopup  ..>  VolumeSlider : «create»
SettingsPopup "1" *--> "volumeSlider 1" VolumeSlider 
SlotMachineButton  -->  Button 
SlotMachineButton  ..>  Gambling : «create»
SlotMachineButtonGamble  -->  Button 
SlotMachineButtonGamble  ..>  GamblingWonCredits : «create»
SlotMachineButtonGamble  ..>  ImageDisplay : «create»
Sniper  -->  Tower 
SongButton  ..>  Clickable 
SongButton  ..>  HasSound 
SongButton  -->  MainClass 
SongDropDown  ..>  Clickable 
SongDropDown  -->  MainClass 
SongDropDown  ..>  SongButton : «create»
SongDropDown "1" *--> "songButtons *" SongButton 
StartingButton  -->  Button 
StartingButton  ..>  MapSelector : «create»
StartingButton  ..>  TutorialText : «create»
TestBloon  -->  Enemy 
TestProjectile  -->  Projectile 
TestTower  ..>  TestProjectile : «create»
TestTower  -->  Tower 
Textboard  -->  MainClass 
Tower  ..>  Animations 
Tower  ..>  Clickable 
Tower "1" *--> "targetedEnemy 1" Enemy 
Tower  -->  Entity 
Tower  ..>  HasSound 
Tower "1" *--> "projectileToShoot 1" Projectile 
Tower  ..>  RangeDisplay : «create»
Tower "1" *--> "rangeDisplay 1" RangeDisplay 
TowerSelector  ..>  Clickable 
TowerSelector  -->  MainClass 
TowerSelector "1" *--> "tower 1" Tower 
TowerSelectorSpawner  -->  MainClass 
TowerSelectorSpawner  ..>  TowerSelector : «create»
TrapTower  ..>  Clickable 
TrapTower  ..>  Explosion : «create»
TrapTower  -->  Tower 
TutorialHud  -->  Button 
TutorialHud  ..>  Clickable 
TutorialHud  ..>  TutorialMap : «create»
TutorialMap  ..>  ClickawayImageDisplay : «create»
TutorialMap  -->  GameMap 
TutorialText  -->  CustomWorld 
TutorialText  ..>  TutorialHud : «create»
UpgradeMenu "1" *--> "player 1" Player 
UpgradeMenu  ..>  SellButton : «create»
UpgradeMenu "1" *--> "sellButton 1" SellButton 
UpgradeMenu "1" *--> "tower 1" Tower 
UpgradeMenu "1" *--> "path1 1" UpgradePath 
UpgradeMenu  ..>  UpgradePath : «create»
UpgradePath  ..>  Clickable 
UpgradePath "1" *--> "tower 1" Tower 
UpgradePath  ..>  UpgradeDescriptionOverlay : «create»
VolumeSlider  ..>  Clickable 
VolumeSlider  -->  MainClass 
WaveManager  -->  MainClass 
WaveResetButton  -->  Button 
WaveResetButton  ..>  Clickable 
```
