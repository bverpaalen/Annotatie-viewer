/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package annotationViewerBrent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    static ArrayList<Polynucleotide> NewFileArray = new ArrayList();

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
    
    public void makeObjectArrayList(String bestandsnaam) {
        String[][] newEntries = fileToString(bestandsnaam);
        for (int i = 1; i < newEntries.length; i++) {
            NewFileArray.add(stringToObject(newEntries[i]));
        }
    }

    public Polynucleotide stringToObject(String seq[]) {
        Polynucleotide o = null;
        try {
            String dnaOrRna = checkDnaRna(seq[1]);
            switch (dnaOrRna) {

                case "DNA":
                    o = new DNA();
                    o.setSequence(seq[1]);
                    o.setDescription(seq[0]);
                    break;

                case "RNA":
                    o = new RNA();
                    o.setSequence(seq[1]);
                    o.setDescription(seq[0]);
                    break;

                default:
                    break;
            }
        } catch (wrongSeq err) {
            System.out.println(err.getMessage());
        }
        return o;
    }

    /**
     * Checks if given sequence is DNA or RNA sequence
     * @param seq sequence to check
     * @return String with type of sequence
     * @throws wrongSeq Sequence contains non RNA or DNA characters
     */
    public String checkDnaRna(String seq) throws wrongSeq {
        String type = "";
        if (seq == null) {
            throw new wrongSeq("De sequentie is leeg");
        } else {
                if (seq.matches("[ATCG]*")) {
                    type = "DNA";
                } else if (seq.matches("[AUCG]*")) {
                    type = "RNA";
                } else {
                    throw new wrongSeq("De sequentie is niet correct");
                }
            
            return type;
        }
    }
}
