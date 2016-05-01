package getstrava.entities.activity;

import java.util.List;

/**
 * Created by roberto on 2/7/14.
 */
public class Zone {

    private int score;
    private List<DistributionBucket> distribution_buckets;
    private String type;
    private int resource_state;
    private boolean sensor_based;
    private int points;
    private boolean custom_zones;
    private int max;
    private double bike_weight;
    private double athlete_weight;

    /**
     *
     */
    public Zone() {
    }

    /**
     *
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     *
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     *
     * @return
     */
    public List<DistributionBucket> getDistribution_buckets() {
        return distribution_buckets;
    }

    /**
     *
     * @param distribution_buckets
     */
    public void setDistribution_buckets(List<DistributionBucket> distribution_buckets) {
        this.distribution_buckets = distribution_buckets;
    }

    /**
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     */
    public int getResource_state() {
        return resource_state;
    }

    /**
     *
     * @param resource_state
     */
    public void setResource_state(int resource_state) {
        this.resource_state = resource_state;
    }

    /**
     *
     * @return
     */
    public boolean isSensor_based() {
        return sensor_based;
    }

    /**
     *
     * @param sensor_based
     */
    public void setSensor_based(boolean sensor_based) {
        this.sensor_based = sensor_based;
    }

    /**
     *
     * @return
     */
    public int getPoints() {
        return points;
    }

    /**
     *
     * @param points
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     *
     * @return
     */
    public boolean isCustom_zones() {
        return custom_zones;
    }

    /**
     *
     * @param custom_zones
     */
    public void setCustom_zones(boolean custom_zones) {
        this.custom_zones = custom_zones;
    }

    /**
     *
     * @return
     */
    public int getMax() {
        return max;
    }

    /**
     *
     * @param max
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     *
     * @return
     */
    public double getBike_weight() {
        return bike_weight;
    }

    /**
     *
     * @param bike_weight
     */
    public void setBike_weight(double bike_weight) {
        this.bike_weight = bike_weight;
    }

    /**
     *
     * @return
     */
    public double getAthlete_weight() {
        return athlete_weight;
    }

    /**
     *
     * @param athlete_weight
     */
    public void setAthlete_weight(double athlete_weight) {
        this.athlete_weight = athlete_weight;
    }
}
