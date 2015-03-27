/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package annotationviewer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Parser {

    public String[][] fileToString(String fileName) {
        String line;
        File file = new File(fileName);

        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bf = new BufferedReader(new FileReader(file));
            while ((line = bf.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            System.out.println("Bestand bestaat niet of is corrupt:  " + e.getLocalizedMessage());
        } 
        String tempList[][] = parseFastaString(sb.toString());
        return tempList;
    }

    public String[][] parseFastaString(String fileString) {        
        String[] lijst = (fileString.split(">"));
        String descriptionSequence[][] = new String[lijst.length][2];
        for (int i = 1; i < lijst.length; i++) {
            String[] lijst2 = (lijst[i]).split("ENm006");
            for (int j = 0; j < lijst2.length; j++) {
                descriptionSequence[i][j] = lijst2[j];

            }
        }
        return descriptionSequence;
    }
}
