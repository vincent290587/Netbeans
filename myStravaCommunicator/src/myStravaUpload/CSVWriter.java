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

        String tbw = "";
        
        for (String l : _lignes) {

            tbw += l;
            tbw += "\n";
            
        }

        try {
            try (FileWriter writer = new FileWriter(file, false)) {
                writer.append(tbw);
                writer.flush();
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }

    }
    
    
    
}
