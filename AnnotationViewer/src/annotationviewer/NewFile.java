/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnnotationViewer;

/**
 *
 * @author Karlijn
 */
public class NewFile {

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
