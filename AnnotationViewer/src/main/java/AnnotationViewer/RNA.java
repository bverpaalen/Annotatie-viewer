package AnnotationViewer;

public class RNA extends Polynucleotide {

    public Protein protein;

    public RNA() {
        super();
    }

    public String getTranslation() {
        StringBuilder sb = new StringBuilder();
        String rna = getComplement();
        for (int i = 0; i < rna.length()-2; i+=3) {
            String triplet = rna.substring(i, i + 3);
            switch (triplet) {
                case "UUU":
                case "UUC":
                    sb.append("F");
                    break;
                case "UUA":
                case "UUG":
                case "CUU":
                case "CUC":
                case "CUA":
                case "CUG":
                    sb.append("L");
                    break;
                case "AUU":
                case "AUC":
                case "AUA":
                    sb.append("I");
                    break;
                case "AUG":
                    sb.append("M");
                    break;
                case "GUU":
                case "GUC":
                case "GUA":
                case "GUG":
                    sb.append("V");
                    break;
                case "UCU":
                case "UCC":
                case "UCA":
                case "UCG":
                case "AGU":
                case "AGC":
                    sb.append("S");
                    break;
                case "CCU":
                case "CCC":
                case "CCA":
                case "CCG":
                    sb.append("P");
                    break;
                case "ACU":
                case "ACC":
                case "ACA":
                case "ACG":
                    sb.append("T");
                    break;
                case "GCU":
                case "GCC":
                case "GCA":
                case "GCG":
                    sb.append("A");
                    break;
                case "UAU":
                case "UAC":
                    sb.append("Y");
                    break;
                case "UAA":
                case "UGA":
                case "UAG":
                    sb.append("*");
                    break;
                case "CAU":
                case "CAC":
                    sb.append("H");
                    break;
                case "CAA":
                case "CAG":
                    sb.append("Q");
                    break;
                case "AAU":
                case "AAC":
                    sb.append("N");
                    break;
                case "AAA":
                case "AAG":
                    sb.append("K");
                    break;
                case "GAU":
                case "GAC":
                    sb.append("D");
                    break;
                case "GAA":
                case "GAG":
                    sb.append("E");
                    break;
                case "UGU":
                case "UGC":
                    sb.append("C");
                    break;
                case "UGG":
                    sb.append("W");
                    break;
                case "CGU":
                case "CGC":
                case "CGA":
                case "CGG":
                case "AGA":
                case "AGG":
                    sb.append("R");
                    break;
                case "GGU":
                case "GGC":
                case "GGA":
                case "GGG":
                    sb.append("G");
                    break;
                default:
                    sb.append("X");
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(this.getClass());
        return str.toString();
    }

    @Override
    public String getComplement() {
        String seq;
        String sequentie = "";
        String RNA = getSequence();
        for (int i = 0; i < RNA.length(); i++) {
            switch (RNA.charAt(i)) {
                case 'U':
                    seq = "A";
                    break;
                case 'A':
                    seq = "U";
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
}
