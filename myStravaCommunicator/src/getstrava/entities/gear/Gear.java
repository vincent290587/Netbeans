package getstrava.entities.gear;

/**
 * Created by roberto on 12/26/13.
 */
public class Gear {


    private String id;
    private boolean primary;
    private String name;
    private float distance;
    private String brand_name;
    private String model_name;
    private String frame_type;
    private String description;
    private int resource_state;

    @Override
    public String toString() {
        return name;
    }

    /**
     *
     * @param id
     */
    public Gear(String id) {
        this.id = id;
    }

    /**
     *
     */
    public Gear() {
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
    public boolean getPrimary() {
        return primary;
    }

    /**
     *
     * @param primary
     */
    public void setPrimary(boolean primary) {
        this.primary = primary;
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
    public String getBrand_name() {
        return brand_name;
    }

    /**
     *
     * @param brand_name
     */
    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    /**
     *
     * @return
     */
    public String getModel_name() {
        return model_name;
    }

    /**
     *
     * @param model_name
     */
    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    /**
     *
     * @return
     */
    public String getFrame_type() {
        return frame_type;
    }

    /**
     *
     * @param frame_type
     */
    public void setFrame_type(String frame_type) {
        this.frame_type = frame_type;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
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
