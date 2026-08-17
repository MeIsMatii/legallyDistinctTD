```mermaid
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
