/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnnotationViewer;

import java.util.ArrayList;

/**
 *
 * @author Karlijn
 */
public class NewFile {

    

    public Sequence fillArray(String seq[]) {
        ArrayList<Sequence> NewFileArray = new ArrayList();
        try {
            String dnaOrRna = checkDnaRna(seq[1]);            
            switch (dnaOrRna) {
                
                case "DNA":
                    Sequence dna = new DNA();
                    dna.setSequence(seq[1]);
                    dna.setDescription(seq[0]);
                    //NewFileArray.add(dna);
                    return dna;
                    //break;
                    
                case "RNA":
                    Sequence rna = new RNA();
                    rna.setSequence(seq[1]);
                    rna.setDescription(seq[0]);
                    //NewFileArray.add(rna);
                    return rna;
                    //break;
                    
                default:
                    return null;
                    //break;
            }
        } catch (wrongSeq err) {
            System.out.println(err.getMessage());
        }
        //return Object();
        //return NewFileArray;
    }

    public String checkDnaRna(String seq) throws wrongSeq {
        String type = "";
        if (seq == null) {
            throw new wrongSeq("De sequentie is leeg");
        } else {

            for (int i = 0; i < seq.length(); i++) {
                switch (seq) {
                    case "[ATCG]*":
                        type = "DNA";
                        break;
                    case "[AUCG]*":
                        type = "RNA";
                        break;
                    default:
                        type = null;
                        break;
                }
            }

        }
        return type;
    }
}
