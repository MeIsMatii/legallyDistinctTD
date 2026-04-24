public class Lever extends ImprovedActor implements UsableItem{
    private boolean isLeverActivated = false;


    public void onUse(Character trigger) {
        isLeverActivated = !isLeverActivated;
        setState();

        //do something with the input ig

    }
    public void setState() {
        if(isLeverActivated) {
            setImage("Lever_on.png");
        } else {
            setImage("Lever_off.png");
        }
    }
}
