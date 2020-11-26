public class Food {

    private String name;
    private int cost;
    private String distributionDay;

    public Food(String name,int cost,String distributionDay){
        this.name = name;
        this.cost = cost;
        this.distributionDay = distributionDay;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public void setDistributionDay(String distributionDay) {
        this.distributionDay = distributionDay;
    }

    public String getDistributionDay() {
        return distributionDay;
    }

    @Override
    public String toString() {
        return name + " " + cost;
    }
}
