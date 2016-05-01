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
public class GPRMC {

    double c_latitude;
    double c_longitude;
    double _vitesse;
    int _date;

    public GPRMC(double latitude, double longitude, double vitesse, int sec_jour) {
        c_latitude = latitude;
        c_longitude = longitude;
        _vitesse = vitesse;
        _date = sec_jour;
    }
    
    public String coordToString(double coord_, int long_) {
        String format, tmp, res;
        double val1, val2;
        
        format = "%" + long_ + ".2f";
        
        val1 = (int)coord_;
        val2 = coord_ - val1;
        val1 *= 100;
        val2 *= 60;
        
        tmp = String.format(format, val1 + val2);
        res = tmp.replaceAll(",", ".").replaceAll(" ", "0");
        
        return res;
    }

    @Override
    public String toString() {
        
        // $GPRMC,220516,A,5133.82,N,00042.24,W,173.8,231.8,130694,004.2,W
        int heure, minute, sec;
        String tmp, res = "$GPRMC,";
        
        heure = _date / 3600;
        minute = (_date % 3600) / 60;
        sec = _date % 60;
        
        res += String.format("%02d", heure);
        res += String.format("%02d", minute);
        res += String.format("%02d", sec) + ",A,";
        res += coordToString(c_latitude, 8);
        res += ",N,";
        res += coordToString(c_longitude, 9);
        if (c_longitude > 0) res += ",E,";
        else res += ",W,";
        // speed in knots
        tmp = String.format("%5.1f", _vitesse * 0.539957);
        res += tmp.replaceAll(",", ".").replaceAll(" ", "0");
        
        res += ",231.8,171115,004.2,W";
        

        return Checksum.add(res);
    }

}
