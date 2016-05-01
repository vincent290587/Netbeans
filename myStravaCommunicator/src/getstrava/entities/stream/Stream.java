package getstrava.entities.stream;

import java.util.List;

/**
 * Created by roberto on 1/27/14.
 */
public class Stream {

    private String type;
    private List<Object> data;
    private String series_type;
    private int original_size;
    private String resolution;

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
    public List<Object> getData() {
        return data;
    }

    /**
     *
     * @param data
     */
    public void setData(List<Object> data) {
        this.data = data;
    }

    /**
     *
     * @return
     */
    public String getSeries_type() {
        return series_type;
    }

    /**
     *
     * @param series_type
     */
    public void setSeries_type(String series_type) {
        this.series_type = series_type;
    }

    /**
     *
     * @return
     */
    public int getOriginal_size() {
        return original_size;
    }

    /**
     *
     * @param original_size
     */
    public void setOriginal_size(int original_size) {
        this.original_size = original_size;
    }

    /**
     *
     * @return
     */
    public String getResolution() {
        return resolution;
    }

    /**
     *
     * @param resolution
     */
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }
}
