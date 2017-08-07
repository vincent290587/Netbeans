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

        for (String l : _lignes) {

            try {
                try (FileWriter writer = new FileWriter(file, false)) {
                    writer.append(l);
                    writer.flush();
                }
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        }


    }
    
    
    
}
