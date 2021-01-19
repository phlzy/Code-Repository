package shadowNinja.model;



import java.util.ArrayList;

/**
 * determine the weapon effect
 * @author 唐雷
 *
 */
public class WeaponEffect {
	//单次施放方向，'a' & 'd' & 'w' & 'x' & 's' & 'e'
    private ArrayList<Character> directions=new ArrayList<Character>(); 
    //单次施放距离(pixel)，-1则无穷
    private ArrayList<Integer> distances=new ArrayList<Integer>(); 
    //施放波次
    private int stageNum;          
    //是否连续施放
    private boolean ifSuccessive;    
    //是否是子弹形式
    private boolean isBullet;    
    //是否是喷火枪形式
    private boolean isFire;                                             

    /**
     * Default constructor.
     */
    public WeaponEffect(){
        stageNum=1;
        ifSuccessive=false;
    }

    /**
     * Constructor with params.
     * @param initDirections Initial directions.
     * @param initDistances Initial distances.
     * @param initStageNum Initial stageNum.
     * @param initIfSuccessive Initial ifSuccessive.
     */
    public WeaponEffect(ArrayList<Character> initDirections, ArrayList<Integer> initDistances,
                        int initStageNum, 
                        boolean initIfSuccessive, boolean initIsBullet, boolean initIsFire){
        directions=initDirections;
        distances=initDistances;
        stageNum=initStageNum;
        ifSuccessive=initIfSuccessive;
        isBullet=initIsBullet;
        isFire=initIsFire;
    }

    /**
     * Get stageNum.
     * @return stageNum(int).
     */
    public int getStageNum() {
        return stageNum;
    }

    /**
     * Get ifSuccessive.
     * @return ifSuccessive(boolean).
     */
    public boolean getIfSuccessive(){
        return ifSuccessive;
    }

    /**
     * Get isBullet.
     * @return isBullet(boolean).
     */
    public boolean getIsBullet(){
        return isBullet;
    }

    /**
     * Get isFire.
     * @return isFire(boolean).
     */
    public boolean getIsFire(){
        return isFire;
    }

    /**
     * Get direction of specified stage.
     *
     * @param index The stage index
     * @return The direction(char).
     */
    public char getDirectionByIndex(int index){
        return directions.get(index);
    }

    /**
     * Get distance of specified stage.
     *
     * @param index The stage index.
     * @return The distance(int).
     */
    public int getDistanceByIndex(int index){
        return distances.get(index);
    }
}

