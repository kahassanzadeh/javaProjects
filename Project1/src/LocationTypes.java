import java.util.Arrays;

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
    private Locations locationType;

    private int[] location;

    public LocationTypes(String locationType,int x , int y){
        this.locationType = Locations.valueOf(locationType);
        this.location = new int[2];
        this.location[0] = x;
        this.location[1] = y;
        GameManager.setLocationTypesArray(this.locationType.toString(),x,y);
    }

    @Override
    public String toString() {
        return this.locationType.toString();
    }
}
