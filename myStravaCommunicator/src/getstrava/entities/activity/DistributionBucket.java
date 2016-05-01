package getstrava.entities.activity;

/**
 * Created by roberto on 2/7/14.
 */
public class DistributionBucket
{
    private int max;
    private int min;
    private int time;

    /**
     *
     */
    public DistributionBucket() {
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
    public int getMin() {
        return min;
    }

    /**
     *
     * @param min
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     *
     * @return
     */
    public int getTime() {
        return time;
    }

    /**
     *
     * @param time
     */
    public void setTime(int time) {
        this.time = time;
    }
}
