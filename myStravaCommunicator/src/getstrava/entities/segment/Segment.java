package getstrava.entities.segment;

import getstrava.entities.activity.Polyline;

/**
 * Created by roberto on 12/26/13.
 */
public class Segment {

    private long id;
    private int resource_state;
    private String name;
    private String activity_type;
    private float distance;
    private float average_grade;
    private float maximum_grade;
    private float elevation_high;
    private float elevation_low;
    private String[] start_latlng;
    private String[] end_latlng;
    private int climb_category;
    private String city;
    private String state;
    private boolean PRIVATE;
    private String created_at;
    private String updated_at;
    private float total_elevation_gain;
    private Polyline map;
    private int effort_count;
    private int athlete_count;
    private boolean hazardous;
    private int pr_time;
    private float pr_distance;
    private boolean starred;
    private String climb_category_desc;

    /**
     *
     * @param id
     */
    public Segment(long id) {
        this.id = id;
    }

    /**
     *
     */
    public Segment() {
    }

    @Override
    public String toString() {
        return name;
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
    public String getActivity_type() {
        return activity_type;
    }

    /**
     *
     * @param activity_type
     */
    public void setActivity_type(String activity_type) {
        this.activity_type = activity_type;
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
    public float getAverage_grade() {
        return average_grade;
    }

    /**
     *
     * @param average_grade
     */
    public void setAverage_grade(float average_grade) {
        this.average_grade = average_grade;
    }

    /**
     *
     * @return
     */
    public float getMaximum_grade() {
        return maximum_grade;
    }

    /**
     *
     * @param maximum_grade
     */
    public void setMaximum_grade(float maximum_grade) {
        this.maximum_grade = maximum_grade;
    }

    /**
     *
     * @return
     */
    public float getElevation_high() {
        return elevation_high;
    }

    /**
     *
     * @param elevation_high
     */
    public void setElevation_high(float elevation_high) {
        this.elevation_high = elevation_high;
    }

    /**
     *
     * @return
     */
    public float getElevation_low() {
        return elevation_low;
    }

    /**
     *
     * @param elevation_low
     */
    public void setElevation_low(float elevation_low) {
        this.elevation_low = elevation_low;
    }

    /**
     *
     * @return
     */
    public String[] getStart_latlng() {
        return start_latlng;
    }

    /**
     *
     * @param start_latlng
     */
    public void setStart_latlng(String[] start_latlng) {
        this.start_latlng = start_latlng;
    }

    /**
     *
     * @return
     */
    public String[] getEnd_latlng() {
        return end_latlng;
    }

    /**
     *
     * @param end_latlng
     */
    public void setEnd_latlng(String[] end_latlng) {
        this.end_latlng = end_latlng;
    }

    /**
     *
     * @return
     */
    public int getClimb_category() {
        return climb_category;
    }

    /**
     *
     * @param climb_category
     */
    public void setClimb_category(int climb_category) {
        this.climb_category = climb_category;
    }

    /**
     *
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     */
    public String getState() {
        return state;
    }

    /**
     *
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @return
     */
    public boolean getPRIVATE() {
        return PRIVATE;
    }

    /**
     *
     * @param PRIVATE
     */
    public void setPRIVATE(boolean PRIVATE) {
        this.PRIVATE = PRIVATE;
    }

    /**
     *
     * @return
     */
    public String getCreated_at() {
        return created_at;
    }

    /**
     *
     * @param created_at
     */
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    /**
     *
     * @return
     */
    public String getUpdated_at() {
        return updated_at;
    }

    /**
     *
     * @param updated_at
     */
    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
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
    public Polyline getMap() {
        return map;
    }

    /**
     *
     * @param map
     */
    public void setMap(Polyline map) {
        this.map = map;
    }

    /**
     *
     * @return
     */
    public int getEffort_count() {
        return effort_count;
    }

    /**
     *
     * @param effort_count
     */
    public void setEffort_count(int effort_count) {
        this.effort_count = effort_count;
    }

    /**
     *
     * @return
     */
    public int getAthlete_count() {
        return athlete_count;
    }

    /**
     *
     * @param athlete_count
     */
    public void setAthlete_count(int athlete_count) {
        this.athlete_count = athlete_count;
    }

    /**
     *
     * @return
     */
    public boolean getHazardous() {
        return hazardous;
    }

    /**
     *
     * @param hazardous
     */
    public void setHazardous(boolean hazardous) {
        this.hazardous = hazardous;
    }

    /**
     *
     * @return
     */
    public int getPr_time() {
        return pr_time;
    }

    /**
     *
     * @param pr_time
     */
    public void setPr_time(int pr_time) {
        this.pr_time = pr_time;
    }

    /**
     *
     * @return
     */
    public float getPr_distance() {
        return pr_distance;
    }

    /**
     *
     * @param pr_distance
     */
    public void setPr_distance(float pr_distance) {
        this.pr_distance = pr_distance;
    }

    /**
     *
     * @return
     */
    public boolean getStarred() {
        return starred;
    }

    /**
     *
     * @param starred
     */
    public void setStarred(boolean starred) {
        this.starred = starred;
    }

    /**
     *
     * @return
     */
    public String getClimb_category_desc() {
        return climb_category_desc;
    }

    /**
     *
     * @param climb_category_desc
     */
    public void setClimb_category_desc(String climb_category_desc) {
        this.climb_category_desc = climb_category_desc;
    }
}
