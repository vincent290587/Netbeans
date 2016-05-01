package getstrava.entities.activity;

/**
 * Created by roberto on 12/28/13.
 */
public class Photo {

    private int id;
    private int activity_id;
    private int resource_state;
    private String ref;
    private String uid;
    private String caption;
    private String type;
    private String uploaded_at;
    private String created_at;
    private String[] location;


    @Override
    public String toString() {
        return ref;
    }

    /**
     *
     */
    public Photo() {
    }

    /**
     *
     * @param id
     */
    public Photo(int id) {
        this.id = id;
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
    public String getRef() {
        return ref;
    }

    /**
     *
     * @param ref
     */
    public void setRef(String ref) {
        this.ref = ref;
    }

    /**
     *
     * @return
     */
    public String getUid() {
        return uid;
    }

    /**
     *
     * @param uid
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     *
     * @return
     */
    public String getCaption() {
        return caption;
    }

    /**
     *
     * @param caption
     */
    public void setCaption(String caption) {
        this.caption = caption;
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
    public String getUploaded_at() {
        return uploaded_at;
    }

    /**
     *
     * @param uploaded_at
     */
    public void setUploaded_at(String uploaded_at) {
        this.uploaded_at = uploaded_at;
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
    public String[] getLocation() {
        return location;
    }

    /**
     *
     * @param location
     */
    public void setLocation(String[] location) {
        this.location = location;
    }
}
