package getstrava.entities.segment;

/**
 * Created by roberto on 1/9/14.
 */
public class LeaderBoardEntry {

    private String athlete_name;
    private int athlete_id;
    private String athlete_gender;
    private float average_hr;
    private float average_watts;
    private float distance;
    private int elapsed_time;
    private int moving_time;
    private String start_date;
    private String start_date_local;
    private int activity_id;
    private long effort_id;
    private int rank;
    private String athlete_profile;


    @Override
    public String toString() {
        return athlete_name;
    }

    /**
     *
     */
    public LeaderBoardEntry() {
    }

    /**
     *
     * @return
     */
    public String getAthlete_name() {
        return athlete_name;
    }

    /**
     *
     * @param athlete_name
     */
    public void setAthlete_name(String athlete_name) {
        this.athlete_name = athlete_name;
    }

    /**
     *
     * @return
     */
    public int getAthlete_id() {
        return athlete_id;
    }

    /**
     *
     * @param athlete_id
     */
    public void setAthlete_id(int athlete_id) {
        this.athlete_id = athlete_id;
    }

    /**
     *
     * @return
     */
    public String getAthlete_gender() {
        return athlete_gender;
    }

    /**
     *
     * @param athlete_gender
     */
    public void setAthlete_gender(String athlete_gender) {
        this.athlete_gender = athlete_gender;
    }

    /**
     *
     * @return
     */
    public float getAverage_hr() {
        return average_hr;
    }

    /**
     *
     * @param average_hr
     */
    public void setAverage_hr(float average_hr) {
        this.average_hr = average_hr;
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
    public int getActivity_id() {
        return activity_id;
    }

    /**
     *
     * @param activity_id
     */
    public void setActivity_id(int activity_id) {
        this.activity_id = activity_id;
    }

    /**
     *
     * @return
     */
    public long getEffort_id() {
        return effort_id;
    }

    /**
     *
     * @param effort_id
     */
    public void setEffort_id(long effort_id) {
        this.effort_id = effort_id;
    }

    /**
     *
     * @return
     */
    public int getRank() {
        return rank;
    }

    /**
     *
     * @param rank
     */
    public void setRank(int rank) {
        this.rank = rank;
    }

    /**
     *
     * @return
     */
    public String getAthlete_profile() {
        return athlete_profile;
    }

    /**
     *
     * @param athlete_profile
     */
    public void setAthlete_profile(String athlete_profile) {
        this.athlete_profile = athlete_profile;
    }
}
