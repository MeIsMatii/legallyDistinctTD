classDiagram
direction BT
class Animations {
<<Interface>>

}
class AntiCheat
class BackButton
class Button
class Clickable {
<<Interface>>

}
class ClickawayImageDisplay
class ClientButton
class ClosePopupButton
class Cursor
class CustomWorld
class DevNotesMap
class DeveloperNotesButton
class DifficultySelector
class DifficultySelectorPopup
class Enemy
class EnemyHitbox
class EnemyLevel1
class EnemyLevel2
class EnemyLevel3
class EnemyLevel4
class EnemyLevel5
class EnemyLevel6
class Entity
class ExampleActor
class Explosion
class FlameProjectile
class Flamethrower
class Gambling
class GamblingWonCredits
class GameMap
class GameMap1
class GameMap2
class GameMap3
class GameMap4
class GameMap5
class GameMap6
class GameMap7
class GameMap8
class GameSaveManager
class GoToTutButton
class HasSound {
<<Interface>>

}
class Helicopter
class HelicopterPad
class Hitbox
class HomingProjectile
class HomingTower
class HostButton
class IPMenuOverlay
class Ice
class IceTower
class ImageDisplay
class LastPLayed
class LoadSaveButton
class MainClass
class MapCoordinatesUtilGuy
class MapPreview
class MapSelector
class MapTitlescreen
class MultiplayerConnection {
<<Interface>>

}
class MultiplayerPreview
class MuteButton
class NetworkClient
class NetworkHost
class NetworkManager
class NewSaveButton
class Path
class PauseMenu
class PlayOnButton
class Player
class PopupScreen
class Projectile
class QuestionPopup
class RangeDisplay
class RestartMultiplayerButton
class RetryButton
class Rocket
class Rocketlauncher
class SaveManager
class Saveable {
<<Interface>>

}
class SellButton
class SettingsButton
class SettingsPopup
class SlotMachineButton
class SlotMachineButtonGamble
class Sniper
class SongButton
class SongDropDown
class SoundSettings
class StartingButton
class TestBloon
class TestProjectile
class TestTower
class TextBlock
class Textboard
class Tower
class TowerSelector
class TowerSelectorSpawner
class TrapTower
class Tuple~A, B~
class TutorialHud
class TutorialMap
class TutorialText
class Upgrade
class UpgradeDescriptionOverlay
class UpgradeMenu
class UpgradePath
class VolumeSlider
class WaveManager
class WaveResetButton

AntiCheat  -->  MainClass 
BackButton  -->  Button 
Button  ..>  Clickable 
ClickawayImageDisplay  ..>  Clickable 
ClickawayImageDisplay  -->  ImageDisplay 
ClientButton  -->  Button 
ClosePopupButton  -->  Button 
DevNotesMap  -->  CustomWorld 
DeveloperNotesButton  -->  Button 
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
