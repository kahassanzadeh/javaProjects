import java.util.Arrays;

/**
 * this class is for generating the location types
 *
 * @author Mohammadreza Hassanzadeh
 * @since 18 Nov 2020
 * @version 1.0
 */

public class LocationTypes {
    public enum Locations{
        NO,
        HL,
        JG,
        RV,
        BR,
        CC,
        SH,
        AL,
        AX
    }
    //location type of the land type
    private Locations locationType;
    //location  of the land type
    private int[] location;

    /**
     * constructor for the land types
     * @param locationType location of the land type
     * @param x x of the land type
     * @param y y of the land type
     */
    public LocationTypes(String locationType,int x , int y){
        this.locationType = Locations.valueOf(locationType);
        this.location = new int[2];
        this.location[0] = x;
        this.location[1] = y;
        GameManager.setLocationTypesArray(this.locationType.toString(),x,y);
    }

    /**
     * to String method overriding
     * @return
     */
    @Override
    public String toString() {
        return this.locationType.toString();
    }
}
