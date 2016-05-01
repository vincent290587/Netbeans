package myStravaUpload;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GPXWriter {

    /**
     *
     * @param file
     * @param n
     * @param points
     */
    public void writePath(String file, String n, List<MonPoint> points) {

        boolean first = false;
        String name, segments;

        String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        header += "<gpx creator=\"Stravaminator with barometer\" version=\"1.1\" xmlns=\"http://www.topografix.com/GPX/1/1\" xmlns:xsi=\"http://www.w3.org/";
        header += "2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.topografix.com/GPX/1/1 http://www.topografix.com/GPX/1/1/gpx.xsd http://www.garmin.com";
        header += "/xmlschemas/GpxExtensions/v3 http://www.garmin.com/xmlschemas/GpxExtensionsv3.xsd http://www.garmin.com/xmlschemas/TrackPointExtension/v1 http://";
        header += "www.garmin.com/xmlschemas/TrackPointExtensionv1.xsd http://www.garmin.com/xmlschemas/GpxExtensions/v3 http://www.garmin.com/xmlschemas/GpxExtensionsv3.xsd";
        header += " http://www.garmin.com/xmlschemas/TrackPointExtension/v1 http://www.garmin.com/xmlschemas/TrackPointExtensionv1.xsd http://www.garmin.com/xmlschemas/GpxExtensions/v3";
        header += " http://www.garmin.com/xmlschemas/GpxExtensionsv3.xsd http://www.garmin.com/xmlschemas/TrackPointExtension/v1";
        header += " http://www.garmin.com/xmlschemas/TrackPointExtensionv1.xsd\" xmlns:gpxtpx=\"http://www.garmin.com/xmlschemas/TrackPointExtension/v1\"";
        header += " xmlns:gpxx=\"http://www.garmin.com/xmlschemas/GpxExtensions/v3\">\n";

        String meta = "<metadata>\n";
        meta += "<name>" + n + "</name>\n";
        
        segments = "";
        name = "";

        for (MonPoint l : points) {

            if (first == false) {
                meta += "<time>" + l.getFormattedTime() + "</time>\n";
                meta += "</metadata>\n<trk>\n";

                name = "<name>";
                name += n;
                name += "</name>\n";

                segments = "<trkseg>\n";

                first = true;
            }

            segments += l.toString();
        }

        String footer = "</trkseg>\n</trk>\n</gpx>\n";

        try {
            try (FileWriter writer = new FileWriter(file, false)) {
                writer.append(header);
                writer.append(meta);
                writer.append(name);
                writer.append(segments);
                writer.append(footer);
                writer.flush();
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    
    
    
}
