package getstrava.entities.activity;

import getstrava.entities.activity.Activity;
import getstrava.entities.athlete.Athlete;

/**
 * Created by roberto on 1/14/14.
 */
public class LapEffort {


    private long id;
    private int resource_state;
    private String name;
    private Activity activity;
    private Athlete athlete;
    private int elapsed_time;
    private int moving_time;
    private String start_date;
    private String start_date_local;
    private float distance;
    private int start_index;
    private int end_index;
    private float total_elevation_gain;
    private float average_speed;
    private float max_speed;
    private float average_cadence;
    private float average_watts;
    private float average_heartrate;
    private float max_heartrate;
    private int lap_index;

    /**
     *
     * @param id
     */
    public LapEffort(long id) {
        this.id = id;
    }

    /**
     *
     */
    public LapEffort() {
    }

    @Override
    public String toString() {
        return name ;
    }

    /**
     *
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(long id) {
        this.id = id;
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
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public Activity getActivity() {
        return activity;
    }

    /**
     *
     * @param activity
     */
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    /**
     *
     * @return
     */
    public Athlete getAthlete() {
        return athlete;
    }

    /**
     *
     * @param athlete
     */
    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
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
    public String getStart_date() {
        return start_date;
    }

    /**
     *
     * @param start_date
     */
    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    /**
     *
     * @return
     */
    public String getStart_date_local() {
        return start_date_local;
    }

    /**
     *
     * @param start_date_local
     */
    public void setStart_date_local(String start_date_local) {
        this.start_date_local = start_date_local;
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
    public int getStart_index() {
        return start_index;
    }

    /**
     *
     * @param start_index
     */
    public void setStart_index(int start_index) {
        this.start_index = start_index;
    }

    /**
     *
     * @return
     */
    public int getEnd_index() {
        return end_index;
    }

    /**
     *
     * @param end_index
     */
    public void setEnd_index(int end_index) {
        this.end_index = end_index;
    }

    /**
     *
     * @return
     */
    public float getTotal_elevation_gain() {
        return total_elevation_gain;
    }

    /**
     *
     * @param total_elevation_gain
     */
    public void setTotal_elevation_gain(float total_elevation_gain) {
        this.total_elevation_gain = total_elevation_gain;
    }

    /**
     *
     * @return
     */
    public float getAverage_speed() {
        return average_speed;
    }

    /**
     *
     * @param average_speed
     */
    public void setAverage_speed(float average_speed) {
        this.average_speed = average_speed;
    }

    /**
     *
     * @return
     */
    public float getMax_speed() {
        return max_speed;
    }

    /**
     *
     * @param max_speed
     */
    public void setMax_speed(float max_speed) {
        this.max_speed = max_speed;
    }

    /**
     *
     * @return
     */
    public float getAverage_cadence() {
        return average_cadence;
    }

    /**
     *
     * @param average_cadence
     */
    public void setAverage_cadence(float average_cadence) {
        this.average_cadence = average_cadence;
    }

    /**
     *
     * @return
     */
    public float getAverage_watts() {
        return average_watts;
    }

    /**
     *
     * @param average_watts
     */
    public void setAverage_watts(float average_watts) {
        this.average_watts = average_watts;
    }

    /**
     *
     * @return
     */
    public float getAverage_heartrate() {
        return average_heartrate;
    }

    /**
     *
     * @param average_heartrate
     */
    public void setAverage_heartrate(float average_heartrate) {
        this.average_heartrate = average_heartrate;
    }

    /**
     *
     * @return
     */
    public float getMax_heartrate() {
        return max_heartrate;
    }

    /**
     *
     * @param max_heartrate
     */
    public void setMax_heartrate(float max_heartrate) {
        this.max_heartrate = max_heartrate;
    }

    /**
     *
     * @return
     */
    public int getLap_index() {
        return lap_index;
    }

    /**
     *
     * @param lap_index
     */
    public void setLap_index(int lap_index) {
        this.lap_index = lap_index;
    }
}
