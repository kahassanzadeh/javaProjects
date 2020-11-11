public class LocationTypes {
    public enum Locations{
        NO,
        HL,
        JG,
        RV,
        BR,
        CC,
        SH
    }
    private Locations locationType;

    private int[] location;

    public LocationTypes(String locationType,int x , int y){
        this.locationType = Locations.valueOf(locationType);
        this.location = new int[2];
        this.location[0] = y;
        this.location[1] = x;
        GameManager.setGameLocations(this.locationType.toString(),x,y);
    }

}
