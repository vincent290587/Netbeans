package getstrava.entities.activity;

/**
 * Created by roberto on 12/26/13.
 */
public class Polyline {

    private String id;
    private String polyline;
    private String summary_polyline;
    private String resource_state;


    @Override
    public String toString() {
        return id;
    }

    /**
     *
     * @param id
     */
    public Polyline(String id) {
        this.id = id;
    }

    /**
     *
     */
    public Polyline() {
    }

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getPolyline() {
        return polyline;
    }

    /**
     *
     * @param polyline
     */
    public void setPolyline(String polyline) {
        this.polyline = polyline;
    }

    /**
     *
     * @return
     */
    public String getSummary_polyline() {
        return summary_polyline;
    }

    /**
     *
     * @param summary_polyline
     */
    public void setSummary_polyline(String summary_polyline) {
        this.summary_polyline = summary_polyline;
    }

    /**
     *
     * @return
     */
    public String getResource_state() {
        return resource_state;
    }

    /**
     *
     * @param resource_state
     */
    public void setResource_state(String resource_state) {
        this.resource_state = resource_state;
    }
}
