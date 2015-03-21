/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.util.Random;

/**
 *
 * @author Karlijn
 */
public class arrayBouwen {

    Sequence[] sequencesArray = new Sequence[100];
    Random random = new Random();
    DNA dna = new DNA();
    RNA rna = new RNA();

    public static void main(String[] args) {
        arrayBouwen aB = new arrayBouwen();
        aB.fillArray();
        aB.showOutput();
    }

    public void fillArray() {
        String dOrR;
        for (int i = 0; i < 100; i++) {
            dOrR = dnaOfRna();
            if (dOrR.equals("DNA")) {
                Sequence dna = new DNA();
                dna.setSequence(maakRandomSequentieDNA());
                sequencesArray[i] = dna;

            } else {
                Sequence rna = new RNA();
                rna.setSequence(maakRandomSequentieRNA());
                sequencesArray[i] = rna;
                
            }
        }

    }
    
    public void showOutput(){
        int teller = 1;
        for (Sequence seq : sequencesArray) {
            if (seq.toString().equals( dna.toString())) {
                System.out.println(teller + ".  Sequentie DNA: "+((DNA)seq).getSequence());
                System.out.println("     Complement DNA: "+((DNA)seq).getComplement());
                System.out.println("     Transcriptie van DNA: "+((DNA)seq).getTranscript());
                System.out.println("\n");
            } else {
                System.out.println(teller + ".  Sequentie RNA: "+((RNA)seq).getSequence());
                System.out.println("     Complement RNA: "+((RNA)seq).getComplement());
                System.out.println("     Translatie van RNA: "+((RNA)seq).getTranslation());
                System.out.println("\n");
            }
            teller+=1;  
        }
    }

    public String dnaOfRna() {
        int DNARNA = random.nextInt(2 - 0);
        String dnaOfRna;
        switch (DNARNA) {
            case 0:
                dnaOfRna = "DNA";
                break;
            case 1:
                dnaOfRna = "RNA";
                break;
            default:
                dnaOfRna = "";
                break;
        }
        return dnaOfRna;
    }

    public String maakRandomSequentieDNA() {

        String seq;
        String sequentie = "";

        for (int j = 0; j < 12; j++) {
            int randOm = random.nextInt(4 - 0);
            switch (randOm) {
                case 0:
                    seq = "A";
                    break;
                case 1:
                    seq = "T";
                    break;
                case 2:
                    seq = "C";
                    break;
                case 3:
                    seq = "G";
                    break;
                default:
                    seq = "N";
                    break;
            }
            sequentie = sequentie + seq;
        }
        return sequentie;
    }

    public String maakRandomSequentieRNA() {

        String seq;
        String sequentie = "";

        for (int j = 0; j < 12; j++) {
            int randOm = random.nextInt(4 - 0);
            switch (randOm) {
                case 0:
                    seq = "A";
                    break;
                case 1:
                    seq = "U";
                    break;
                case 2:
                    seq = "C";
                    break;
                case 3:
                    seq = "G";
                    break;
                default:
                    seq = "N";
                    break;

            }
            sequentie = sequentie + seq;
        }
        return sequentie;
    }
}
