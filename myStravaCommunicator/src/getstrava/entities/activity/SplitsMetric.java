package getstrava.entities.activity;

/**
 * Created by roberto on 12/27/13.
 */
public class SplitsMetric {

    private float distance;
    private int elapsed_time;
    private float elevation_difference;
    private int moving_time;
    private int split;

    /**
     *
     */
    public SplitsMetric() {


    }

    @Override
    public String toString() {
        return ""+split;
    }

    /**
     *
     * @return
     */
    public float getDistance() {
        return distance;
    }

    /**
     *
     * @param distance
     */
    public void setDistance(float distance) {
        this.distance = distance;
    }

    /**
     *
     * @return
     */
    public int getElapsed_time() {
        return elapsed_time;
    }

    /**
     *
     * @param elapsed_time
     */
    public void setElapsed_time(int elapsed_time) {
        this.elapsed_time = elapsed_time;
    }

    /**
     *
     * @return
     */
    public float getElevation_difference() {
        return elevation_difference;
    }

    /**
     *
     * @param elevation_difference
     */
    public void setElevation_difference(float elevation_difference) {
        this.elevation_difference = elevation_difference;
    }

    /**
     *
     * @return
     */
    public int getMoving_time() {
        return moving_time;
    }

    /**
     *
     * @param moving_time
     */
    public void setMoving_time(int moving_time) {
        this.moving_time = moving_time;
    }

    /**
     *
     * @return
     */
    public int getSplit() {
        return split;
    }

    /**
     *
     * @param split
     */
    public void setSplit(int split) {
        this.split = split;
    }
}
