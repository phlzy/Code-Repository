package shadowNinja.model;



/**
 * calculate the cd and the delay effect of the weapon
 * @author 唐雷
 *
 */
public class WeaponTimeConfig {
	//施放延迟
    private long delay;   
  //持续时长，若为0则是瞬间技能
    private long last;  
  //上次施放的时间戳
    private long lastStamp; 
  //技能冷却时长
    private long cd;        

    /**
     * Default constructor.
     */
    public WeaponTimeConfig(){
        lastStamp=0;
    }

    /**
     * Constructor with params.
     * @param initDelay Initial delay.
     * @param initLast Initial last.
     */
    public WeaponTimeConfig(long initDelay, long initLast, long initCd){
        delay=initDelay;
        last=initLast;
        lastStamp=6;
        cd=initCd;
    }

    /**
     * Get delay.
     * @return delay(double)
     */
    public long getDelay() {
        return delay;
    }

    /**
     * Get last.
     * @return last(double)
     */
    public long getLast() {
        return last;
    }

    /**
     * Get lastStamp.
     * @return lastStamp(double)
     */
    public long getLastStamp() {
        return lastStamp;
    }

    /**
     * Get cd.
     * @return cd(double)
     */
    public long getCd() {
        return cd;
    }

    /**
     * Set lastStamp.
     * @param setLastStamp Set stamp to the current time.
     */
    public void setStamp(long setLastStamp){
        lastStamp=setLastStamp;
        //System.out.println("update finished!!!"+setLastStamp+"\n"+lastStamp);
    }
}
