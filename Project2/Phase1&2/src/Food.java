import java.io.Serializable;

/**
 * this class created for the Foods
 *
 * @author Mihammadreza Hassanzadeh
 * @since 17 Dec 2020
 * @version 1.0
 */
public class Food implements Serializable {
    //name of the food
    private String name;
    //int cost of the food
    private int cost;
    //String distribution day
    private String distributionDay;

    /**
     * constructor for food
     *
     * @param name nam eof the food
     * @param cost int cost of the food
     * @param distributionDay day that this food will ditributed
     */
    public Food(String name,int cost,String distributionDay){
        this.name = name;
        this.cost = cost;
        this.distributionDay = distributionDay;
    }

    /**
     * setter for the name of the food
     * @param name name of the food
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter for the name of the food
     * @return String food name
     */
    public String getName() {
        return name;
    }

    /**
     * setting the cost of the food
     * @param cost int cost of the food
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * getter for the cost of the food
     * @return int number of the food's cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * setting the day that this food will distributed
     * @param distributionDay String of distribution day
     */
    public void setDistributionDay(String distributionDay) {
        this.distributionDay = distributionDay;
    }

    /**
     * getting the distribution day
     * @return String of the distribution day
     */
    public String getDistributionDay() {
        return distributionDay;
    }

    /**
     * override method
     * getting the name and cost of the food
     *
     * @return String of cost and name of the food
     */
    @Override
    public String toString() {
        return name + " " + cost;
    }
}
