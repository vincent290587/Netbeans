package myStravaUpload;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter {

    /**
     * 
     * @param file
     * @param _lignes 
     */
    public void writePath(String file, List<String> _lignes) {
        
        String segments = "";

        for (String l : _lignes) {
            segments += l.toString();
        }

        try {
            try (FileWriter writer = new FileWriter(file, false)) {
                writer.append(segments);
                writer.flush();
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }

    }
    
    
    
}
