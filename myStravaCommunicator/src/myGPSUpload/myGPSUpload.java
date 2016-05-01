/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myGPSUpload;

import Interface.myConfiguration;
import Interface.myDownload;
import Interface.mySerial;
import getstrava.entities.stream.Stream;
import java.util.List;
import myStravaDownload.mySegment;

/**
 * Utile pour envoyer les segments au GPS
 *
 * @author vincent
 */
public class myGPSUpload {

    List<mySegment> _segments;
    myConfiguration _config;
    mySerial _serial;
    myDownload _parent;

    public myGPSUpload(myDownload parent) {
        _parent = parent;
        _config = parent.getConfig();
        _serial = parent.getConsole();
    }

    public void envoi(List<mySegment> my_segments) {

        int j, k;
        Stream curStream;
        List<Double> coord;
        String ligne, tmp_str;
        mySegment curSeg;

        _parent.clearConsole();

        if (my_segments == null) {
            _serial.appendLine("Pas de segment à envoyer...");
            System.out.println("Pas de segment à envoyer...");
            return;
        }

        if (my_segments.size() < 1) {
            _serial.appendLine("Pas de segment à envoyer...");
            System.out.println("Pas de segment à envoyer...");
            return;
        }

        _segments = my_segments;

        if (!_segments.isEmpty()) {

            _config.sendString("$MAJ,0," + _segments.size());

            for (j = 0; j < _segments.size(); j++) {

                curSeg = _segments.get(j);

                _serial.appendLine("Envoi du segment " + curSeg._seg.getName());
                
                tmp_str = curSeg._seg.getName();
                tmp_str.replaceAll(",", "");
                
                _config.sendString("$MAJ,1," + tmp_str);

                for (k = 0; k < curSeg._lignes.size(); k++) {

                    tmp_str = curSeg._lignes.get(k);
                    
                    _config.sendString(tmp_str);

                }

                _parent.setProgress(j + 1, _segments.size());

                _config.sendString("$MAJ,2");

            }
            _config.sendString("$MAJ,3");
            _parent.setProgress(100, 100);
        } else {
            _serial.appendLine("Pas de segment à envoyer...");
            System.out.println("Pas de segment à envoyer...");
        }

    }

}
