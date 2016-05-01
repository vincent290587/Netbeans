package myStravaUpload;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static java.util.Calendar.*;
import java.util.Date;
import java.util.TimeZone;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author vincent
 */
class MonPoint {

    private final double lat_;
    private final double lon_;
    private final double ele_;
    private final double secjour_;
    private final int bpm_;
    private final int cad_;
    private final double temp_;

    MonPoint(double lati, double longi, double elev, double sec_jour) {
        lat_ = lati;
        lon_ = longi;
        ele_ = elev;
        secjour_ = sec_jour;
        bpm_ = -1;
        cad_ = -1;
        temp_ = -50;
    }
    
    MonPoint(double lati, double longi, double elev, double sec_jour, int bpm) {
        lat_ = lati;
        lon_ = longi;
        ele_ = elev;
        secjour_ = sec_jour;
        bpm_ = bpm;
        cad_ = -1;
        temp_ = -50.;
    }
    
    MonPoint(double lati, double longi, double elev, double sec_jour, int bpm, int cad) {
        lat_ = lati;
        lon_ = longi;
        ele_ = elev;
        secjour_ = sec_jour;
        bpm_ = bpm;
        cad_ = cad;
        temp_ = -50.;
    }

    MonPoint(double lati, double longi, double elev, double sec_jour, int bpm, int cad, double temp) {
        lat_ = lati;
        lon_ = longi;
        ele_ = elev;
        secjour_ = sec_jour;
        bpm_ = bpm;
        cad_ = cad;
        temp_ = temp;
    }

    double getLatitude() {
        return lat_;
    }

    double getLongitude() {
        return lon_;
    }

    double getElevation() {
        return ele_;
    }

    double getTime() {
        return secjour_;
    }

    public String getFormattedTime() {

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT"));
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

        calendar.set(HOUR_OF_DAY, 0);
        calendar.set(MINUTE, 0);
        calendar.set(SECOND, 0);
        calendar.set(MILLISECOND, 0);
        long millis_a0 = calendar.getTimeInMillis();

        long tempsPoint = (long) this.getTime();
        String retour_ = df.format(new Date(tempsPoint * 1000 + millis_a0)) + "Z";

        return retour_;
    }

    @Override
    public String toString() {

        String retour_ = "<trkpt lat=\"" + this.getLatitude() + "\" lon=\"" + this.getLongitude() + "\">\n";
        retour_ += "<time>" + this.getFormattedTime() + "</time>";
        retour_ += "<ele>" + this.getElevation() + "</ele>\n";

        if (this.bpm_ > 0 || this.cad_ > 0 || this.temp_ > -40) {
            retour_ += "<extensions>\n"
                    + "<gpxtpx:TrackPointExtension>";
            if (this.bpm_ > 0) {
                retour_ += "<gpxtpx:hr>" + this.bpm_ + "</gpxtpx:hr>";
            }
            if (this.cad_ > -1) {
                retour_ += "<gpxtpx:cad>" + this.cad_ + "</gpxtpx:cad>";
            }
            if (this.temp_ > -40.) {
                retour_ += "<gpxtpx:atemp>" + this.temp_ + "</gpxtpx:atemp>";
            }
            retour_ += "</gpxtpx:TrackPointExtension>\n"
                    + "</extensions>\n";
        }

        retour_ += "</trkpt>\n";

        return retour_;
    }

}
