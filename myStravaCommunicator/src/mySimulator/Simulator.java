/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mySimulator;

import Interface.myConfiguration;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vincent
 */
public class Simulator {

    myConfiguration _config;
    InputStream fis;

    public Simulator() {
        _config = null;
        fis = null;
    }

    public String simulatedSentence(String line) {

        String morceaux[] = line.split(";");
        double lat, lon, speed = 0;
        int secjour;

        lat = Double.parseDouble(morceaux[0]);
        lon = Double.parseDouble(morceaux[1]);
        secjour = Integer.parseInt(morceaux[2]);

        GPRMC sent = new GPRMC(lat, lon, speed, secjour);

        return sent.toString();
    }

    public void stopSimu() {
        _config.sendString("#");
        if (fis != null) {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void startSimu(myConfiguration config_, String filename) {

        String line;

        _config = config_;

        try {

            fis = new FileInputStream(filename);

            InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
            BufferedReader br = new BufferedReader(isr);

            // on passe en mode simulation
            _config.appendLine("Début de simulation");
            _config.sendString("$DWN,2");
            Thread.sleep(1000);

            while ((line = br.readLine()) != null) {

                _config.sendString(simulatedSentence(line));
                Thread.sleep(800);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
