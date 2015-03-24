package AnnotationViewer;

public class DNA extends Polynucleotide {

    public DNA() {
        super();
    }

    public String getTranscript() {
        String transcript = getComplement();
        transcript = transcript.replaceAll("T", "U");
        return transcript;
    }

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
