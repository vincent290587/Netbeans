/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myStravaUpload;

import Interface.myIHM;
import getstrava.authenticator.Authentification;
import getstrava.authenticator.OAuth2Credentials;
import getstrava.authenticator.StravaAPI;
import java.util.ArrayList;
import java.util.List;

/**
 * Utile pour envoyer l'activite reçue du GPS
 *
 * @author vincent
 */
public class myStravaUpload {

    List<MonPoint> _points;
    myIHM _parent;
    String accessToken;

    public myStravaUpload(List<String> lignes_, myIHM parent_) {

        int j;
        String ligne;
        String morceaux[];
        double lat, lon, ele, secjour, temp;
        int bpm, cad;

        accessToken = "80cd2921fd74c9e497e68d66aa6d5af9ca5f9db7";

        _points = new ArrayList<>();
        _parent = parent_;

        if (lignes_ != null) {
            for (j = 0; j < lignes_.size(); j++) {

                ligne = lignes_.get(j);

                if (ligne.contains("a")) {
                    // @TODO
                    //_points.clear();
                    continue;
                }

                morceaux = ligne.split(",");

                if (morceaux.length < 8) {
                    System.out.println("Mauvaise longueur: " + morceaux.length);
                    continue;
                }

                // on recupere les donnees dans le CSV
                lat = Double.parseDouble(morceaux[0]);
                lon = Double.parseDouble(morceaux[1]);
                ele = Double.parseDouble(morceaux[2]);
                secjour = Float.parseFloat(morceaux[3]);
                bpm = Integer.parseInt(morceaux[4]);
                cad = Integer.parseInt(morceaux[5]);
                temp = Double.parseDouble(morceaux[6]);
                _points.add(new MonPoint(lat, lon, ele, secjour, bpm, cad, temp));

            }
        }

    }

    public void sauvegarder() {
        GPXWriter monGPX = new GPXWriter();

        _parent._serial.appendLine("Sauvegarde du GPX");
        monGPX.writePath("C:\\Users\\vincent\\Desktop\\today.gpx", "Training", _points);
    }

    public void envoyer() {

        OAuth2Credentials.errorIfNotSpecified();
        String token = null;

        OAuth2Credentials credentials = OAuth2Credentials.Read();
        if (credentials != null) {
            // see if we can use them to talk to server
            System.out.println("retrieved credentials "
                    + credentials.getClientToken());
            token = Authentification.getBearerToken(credentials);
            System.out.println("bearer token.1 " + token);
        }
        if (credentials == null || token == null) {
            String code = Authentification.getOAuth2Credentials();
            System.out.println("code" + code);
            credentials = new OAuth2Credentials();
            credentials.setClientToken(code);
            credentials.Store();
            token = Authentification.getBearerToken(credentials);
            System.out.println("bearer token.2 " + token);
        }

        long athleteId = StravaAPI.getCurrentAtheleteID(token);
        System.out.println("athleteId " + athleteId);
        StravaAPI.uploadActivity(token, "C:\\Users\\vincent\\Desktop\\today.gpx");
    }

}
