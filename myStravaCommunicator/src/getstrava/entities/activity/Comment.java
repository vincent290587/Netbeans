package getstrava.entities.activity;

import getstrava.entities.athlete.Athlete;

/**
 * Created by roberto on 12/27/13.
 */
public class Comment {

    private int id;
    private int resource_state;
    private String text;
    private Athlete athlete;
    private String created_at;


    @Override
    public String toString() {
        return text;
    }

    /**
     *
     * @param id
     */
    public Comment(int id) {
        this.id = id;
    }

    /**
     *
     */
    public Comment() {
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
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
    public String getText() {
        return text;
    }

    /**
     *
     * @param text
     */
    public void setText(String text) {
        this.text = text;
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
}
