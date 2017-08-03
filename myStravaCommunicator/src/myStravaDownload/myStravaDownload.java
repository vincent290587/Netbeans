/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myStravaDownload;

import Interface.myDownload;
import Interface.mySerial;
import getstrava.connector.JStravaV3;
import getstrava.entities.athlete.Athlete;
import getstrava.entities.segment.Bound;
import getstrava.entities.segment.Segment;
import getstrava.entities.segment.SegmentEffort;
import getstrava.entities.segment.SegmentLeaderBoard;
import getstrava.entities.stream.Stream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vincent
 */
public class myStravaDownload {

    int mode_date;
    mySerial printout;
    myDownload dl;

    List<mySegment> my_segments;

    private final String accessToken;

    public myStravaDownload() {
        // token api
        accessToken = "80cd2921fd74c9e497e68d66aa6d5af9ca5f9db7";
        my_segments = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public List<mySegment> getMySegments() {
        return my_segments;
    }

    /**
     * @param mode_date_ Annee pour laquelle on recherche la perf. Si < 2000 ->
     * overall
     * @param printout_
     * @param dl_
     */
    public void setParameters(int mode_date_, mySerial printout_, myDownload dl_) {
        
        mode_date = mode_date_;
        printout = printout_;
        dl = dl_;

    }

    public void download() {
        int i, j, k;
        int effort_trouve;

        Stream curStream;
        Segment curSeg;
        List<SegmentEffort> efforts;
        List<Segment> mes_segments;
        SegmentEffort curEffort;
        SegmentLeaderBoard leader;
        PrintWriter writer;
        List<Stream> mes_streams;
        List<Double> coord;
        List<String> lignes;
        String ligne, tmp_str;
        HashMap<String, String> hash = new HashMap<>();
        HashMap<String, String> hash_date = new HashMap<>();
        HashMap<String, Integer> hash_page = new HashMap<>();
        int athlete_id;
        Bound bound;
        String[] type = {"latlng", "time", "altitude"};
        String liste_noms = "";
        
        
        printout.clear();
        printout.appendLine("Paramètres de recuperation:");
        if (mode_date > 2000) {
            printout.appendLine("Année= " + mode_date);
        } else {
            printout.appendLine("Année courante sélectionnée");
        }

        my_segments.clear();
        //////////////////////////////////////////////
        //////////////      PARAM         ////////////
        //////////////////////////////////////////////
        // SW Lat lon -> NE
        bound = new Bound(43.49768, 1.4159, 43.54616, 1.50266);

        // 0 pour chercher geographiquement
        // 1 pour chercher par segments etoiles
        int mode_rech = 1;

        // 0 pour le PR
        // 1 pour obtenir le KOM
        int mode_effort = 0;

        lignes = new ArrayList<>();

        dl.setProgress(1, 100);
        //////////////////////////////////////////////
        //////////////////////////////////////////////
        //////////////////////////////////////////////
        JStravaV3 strava = new JStravaV3(accessToken);

        Athlete athlete = strava.getCurrentAthlete();

        printout.appendLine("Téléchargement...");

        // STEP 1 : telecharger la liste des segments
        if (mode_rech == 0) {
            hash.put("activity_type", "riding");
            mes_segments = strava.findSegments(bound, hash);
        } else {
            hash_page.put("page", 1);
            hash_page.put("per_page", 200);
            mes_segments = strava.getCurrentStarredSegment(hash_page);
        }

        printout.appendLine("Nombre de segments trouves: " + mes_segments.size());

        // STEP 2 : Pour chacun on telecharge le stream
        for (i = 0; i < mes_segments.size(); i++) {

            System.out.println("");

            dl.setProgress(i + 1, mes_segments.size());
            
            //if (i==15) break;

            curSeg = mes_segments.get(i);

            System.out.println("Name = " + curSeg.getName() + " --> Id = " + curSeg.getId());

            printout.appendLine("Segment: " + curSeg.getName());

            if (mode_effort == 0) {
                athlete_id = athlete.getId();
                leader = null;
            } else {
                // leader du segment
                hash_date.put("page", "1");
                hash_date.put("per_page", "1");
                if (mode_date < 2000) {
                    leader = strava.findSegmentLeaderBoard(curSeg.getId(), hash_date);
                } else {
                    hash_date.put("date_range", "" + mode_date);
                    leader = strava.findSegmentLeaderBoard(curSeg.getId(), hash_date);
                }
                if (leader.getEntries().size() < 1) {
                    System.out.println("Pas de classement 2015 pour " + curSeg.getName());
                    continue;
                }
                athlete_id = leader.getEntries().get(0).getAthlete_id();
                printout.appendLine(" Athlète retenu= " + leader.getEntries().get(0).getAthlete_name());
            }

            // il nous faut la liste des efforts de l'athlete sur ce segment
            try {
                efforts = strava.findSegmentEfforts(curSeg.getId(), "athlete_id=" + athlete_id);
            } catch (RuntimeException ex) {
                System.out.println(ex.toString());
                continue;
            }

            System.out.println("NB efforts= " + efforts.size());

            // TODO change
            if (!efforts.isEmpty()) {

                // on prend le meilleur effort du meilleur (overral) par defaut
                curEffort = efforts.get(0);
                // dans certains cas on veut le KOM d'une annee specifiee
                if (mode_date > 2000) {
                    // on veut le meilleur effort de l'annee specifiee
                    effort_trouve = 0;
                    for (j = 0; j < efforts.size(); j++) {
                        // 
                        curEffort = efforts.get(j);
                        // on teste l'annee de l'effort
                        if (curEffort.getStart_date_local().startsWith("" + mode_date)) {
                            effort_trouve = 1;
                            break;
                        }
                    }
                    if (effort_trouve == 0) {
                        // on prend quand meme quelque chose
                        curEffort = efforts.get(0);
                    }
                }

                if (curEffort != null) {

                    mes_streams = strava.findEffortStreams(curEffort.getId(), type);

                    if (!mes_streams.isEmpty()) {

                        ligne = "";
                        for (j = 0; j < mes_streams.get(0).getOriginal_size(); j++) {
                            // on ecrit un trackpoint

                            curStream = mes_streams.get(0);
                            if (curStream.getType().matches("latlng")) {

                                coord = (List<Double>) curStream.getData().get(j);

                                int lat = (int) ((coord.get(0) + 90) * 100000);
                                int lon = (int) ((coord.get(1) + 180) * 100000);

                                ligne = Integer.toString(lat, 36);
                                ligne += "#";
                                ligne += Integer.toString(lon, 36).substring(0, 2);
                                ligne += ".";
                                ligne += Integer.toString(lon, 36).substring(2, Integer.toString(lon, 36).length());
                                ligne = ligne.toUpperCase();

                                while (liste_noms.contains(ligne)) {
                                    lat++;
                                    ligne = Integer.toString(lat, 36);
                                    ligne += "#";
                                    ligne += Integer.toString(lon, 36).substring(0, 2);
                                    ligne += ".";
                                    ligne += Integer.toString(lon, 36).substring(2, Integer.toString(lon, 36).length());
                                    ligne = ligne.toUpperCase();
                                    lat -= 2;
                                    lon++;
                                    printout.appendLine("  Modification du nom");
                                }
                                break;
                            }

                        }

                        printout.appendLine("  Enregistrement sous " + ligne);
                        liste_noms += ligne;

                        lignes.clear();

                        try {
                            writer = new PrintWriter(ligne, "UTF-8");
                            System.out.println(ligne);

                            // boucle sur les points de la data du stream
                            writer.println("<Name>" + curSeg.getName() + "</Name>");
                            if (leader != null) {
                                writer.println("<Leader>" + leader.getEntries().get(0).getAthlete_name() + "</Leader>");
                                System.out.println("Leader: " + leader.getEntries().get(0).getAthlete_name());
                            }

                            if (2000 <= mode_date) {
                                System.out.println("Date record: " + curEffort.getStart_date_local() + "");
                            }

                            for (j = 0; j < mes_streams.get(0).getOriginal_size(); j++) {
                                // on ecrit un trackpoint
                                ligne = "";
                                for (k = 0; k < mes_streams.size(); k++) {

                                    curStream = mes_streams.get(k);
                                    if (curStream.getType().matches("latlng")) {

                                        coord = (List<Double>) curStream.getData().get(j);
                                        writer.print("" + coord.get(0).toString() + " ; " + coord.get(1).toString() + " ; ");
                                        ligne = "" + coord.get(0).toString() + " ; " + coord.get(1).toString() + " ; ";

                                    } else if (curStream.getType().matches("time")) {

                                        writer.print("" + curStream.getData().get(j).toString() + " ; ");
                                        tmp_str = curStream.getData().get(j).toString() + " ; ";
                                        ligne += tmp_str;

                                    } else if (curStream.getType().matches("altitude")) {

                                        writer.println("" + curStream.getData().get(j).toString());
                                        tmp_str = curStream.getData().get(j).toString();
                                        ligne += tmp_str;

                                    }
                                }
                                lignes.add(ligne);
                            }
                            // size check
                            if (lignes.size() > 0) {
                                my_segments.add(new mySegment(lignes, curSeg, leader, curEffort));
                            }
                            
                            System.out.println(lignes.size() + " points écrits");
                            
                            writer.close();
                        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                            Logger.getLogger(myStravaDownload.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    System.out.println("Variable curEffort = NULL !! ");
                }
            } // fin efforts vide

        }
        dl.setSegments(my_segments);
        dl.setProgress(100, 100);
    }

}
