
public class SoundSettings {

    
    public static final int MinimumVolume = 0;
    public static final int Maximum_Volume = 100;//(max greenfoot lautstärke)
    private static final int DefaultStartingVolume = 80;
    private static SoundSettings singleInstance = null; //static so dass es bei weltänderungen bestehen bleibt
    private int masterVolume;
    private SoundSettings() {
        this.masterVolume = DefaultStartingVolume;
    }
    public int getMasterVolume() {
        return masterVolume;
    }
    /// setter und getter
    public void setMasterVolume(int newVolume) {
        // Math.max und Math.min stellen sicher das die zahl zwischen 0 und 100 ist(um fehler zu vermeiden)
        this.masterVolume = Math.max(MinimumVolume, Math.min(Maximum_Volume, newVolume));
    }

    public static SoundSettings getInstance() {
        if (singleInstance == null) {
            singleInstance = new SoundSettings();
        }
        return singleInstance;
    }

    public void increaseVolume(int stepAmount) {
        setMasterVolume(this.masterVolume + stepAmount);
    }
    public void decreaseVolume(int stepAmount) {
        setMasterVolume(this.masterVolume - stepAmount);
    }
}
