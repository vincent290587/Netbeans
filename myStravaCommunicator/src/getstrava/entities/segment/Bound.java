package getstrava.entities.segment;

/**
 * Created by roberto on 1/14/14.
 */
public class Bound {


    private double southwestLatitude;
    private double southwestlongitude;
    private double northeastLatitude;
    private double northeastlongitude;

    /**
     *
     * @param southwestLatitude
     * @param southwestlongitude
     * @param northeastLatitude
     * @param northeastlongitude
     */
    public Bound(double southwestLatitude, double southwestlongitude, double northeastLatitude, double northeastlongitude) {
        this.southwestLatitude = southwestLatitude;
        this.southwestlongitude = southwestlongitude;
        this.northeastLatitude = northeastLatitude;
        this.northeastlongitude = northeastlongitude;
    }

    public Bound() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
     */
    public double getSouthwestLatitude() {
        return southwestLatitude;
    }

    /**
     *
     * @param southwestLatitude
     */
    public void setSouthwestLatitude(double southwestLatitude) {
        this.southwestLatitude = southwestLatitude;
    }

    /**
     *
     * @return
     */
    public double getSouthwestlongitude() {
        return southwestlongitude;
    }

    /**
     *
     * @param southwestlongitude
     */
    public void setSouthwestlongitude(double southwestlongitude) {
        this.southwestlongitude = southwestlongitude;
    }

    /**
     *
     * @return
     */
    public double getNortheastLatitude() {
        return northeastLatitude;
    }

    /**
     *
     * @param northeastLatitude
     */
    public void setNortheastLatitude(double northeastLatitude) {
        this.northeastLatitude = northeastLatitude;
    }

    /**
     *
     * @return
     */
    public double getNortheastlongitude() {
        return northeastlongitude;
    }

    /**
     *
     * @param northeastlongitude
     */
    public void setNortheastlongitude(double northeastlongitude) {
        this.northeastlongitude = northeastlongitude;
    }

    @Override
    public String toString() {
        return southwestLatitude +
                "," + southwestlongitude +
                "," + northeastLatitude +
                "," + northeastlongitude;
    }
}
