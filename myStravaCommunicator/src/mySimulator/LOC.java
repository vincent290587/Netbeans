/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mySimulator;

/**
 *
 * @author vincent
 */
public class LOC {

    int c_latitude;
    int c_longitude;
    int c_altitude;
    int c_vitesse;
    int c_secjour;

    public LOC(double latitude, double longitude, double altitude, double vitesse, int sec_jour) {
        c_latitude = (int)(latitude * 10000000.);
        c_longitude = (int)(longitude * 10000000.);
        c_altitude = (int)(altitude * 100.);
        c_vitesse = (int)(vitesse * 100. / 3.6);
        c_secjour = sec_jour;
    }

    @Override
    public String toString() {
        
        String res = "$LOC," + c_secjour + ",";
        
        if (c_latitude < 0) res += "-";

        res += c_latitude;
        res += ",";
        
        if (c_longitude < 0) res += "-";
        
        res += c_longitude;
        res += ",";
        
        res += c_altitude;
        res += ",";
        
        res += c_vitesse;

        return Checksum.add(res);
    }

}
