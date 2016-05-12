package annotationViewer;

import annotationViewer.Polynucleotide;


public class DNA extends Polynucleotide {


    public DNA() {
        super();
    }

    /**
     * Transcripts local DNA to RNA
     * @return RNA sequence
     */
    public String getTranscript() {
        String transcript = getComplement();
        transcript = transcript.replaceAll("T", "U");
        return transcript;
    }

    /**
     * Complements local DNA string
     * @return String of complemented given DNA string
     */
    @Override
    public String getComplement() {
        String seq;
        String sequentie = "";
        String DNA = getSequence();
        for (int i = 0; i < DNA.length(); i++) {
            switch (DNA.charAt(i)) {
                case 'T':
                    seq = "A";
                    break;
                case 'A':
                    seq = "T";
                    break;
                case 'G':
                    seq = "C";
                    break;
                case 'C':
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

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(this.getClass());
        return str.toString();
    }
}
