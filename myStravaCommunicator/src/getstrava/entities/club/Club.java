package getstrava.entities.club;

/**
 * Created by roberto on 12/26/13.
 */
public class Club {

    private int id;
    private int resource_state;
    private String name;


    @Override
    public String toString() {
        return name;
    }

    /**
     *
     * @param id
     */
    public Club(int id) {
        this.id = id;
    }

    /**
     *
     */
    public Club() {
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
}
