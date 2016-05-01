package getstrava.entities.activity;

/**
 * Created by roberto on 3/17/14.
 */
public class UploadStatus {


    private String id;
    private String external_id;
    private int activity_id;
    private String status;
    private String error;

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
    public String getExternal_id() {
        return external_id;
    }

    /**
     *
     * @param external_id
     */
    public void setExternal_id(String external_id) {
        this.external_id = external_id;
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
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     */
    public String getError() {
        return error;
    }

    /**
     *
     * @param error
     */
    public void setError(String error) {
        this.error = error;
    }


    @Override
    public String toString() {
        return  status;
    }
}
