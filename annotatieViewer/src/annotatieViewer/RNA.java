package model;

import java.util.HashMap;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc --> @generated
 */
public class RNA extends Polynucleotide {

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated @ordered
     */
    public Protein protein;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    public RNA() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated @ordered
     */
    public boolean checkSequence() {
        // TODO implement me
        return false;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated @ordered
     */
    public String getTranslation() {
        
        StringBuilder sb = new StringBuilder();
        String rna = getComplement();
        for (int i = 0; i < rna.length() - 2; i+=2) {
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
                case "TCT":
                case "TCC":
                case "TCA":
                case "TCG":
                case "AGT":
                case "AGC":
                    sb.append("S");
                    break;
                case "CCT":
                case "CCC":
                case "CCA":
                case "CCG":
                    sb.append("P");
                    break;
                case "ACT":
                case "ACC":
                case "ACA":
                case "ACG":
                    sb.append("T");
                    break;
                case "GCT":
                case "GCC":
                case "GCA":
                case "GCG":
                    sb.append("A");
                    break;
                case "TAT":
                case "TAC":
                    sb.append("Y");
                    break;
                case "TAA":
                case "TGA":
                case "TAG":
                    sb.append("*");
                    break;
                case "CAT":
                case "CAC":
                    sb.append("H");
                    break;
                case "CAA":
                case "CAG":
                    sb.append("Q");
                    break;
                case "AAT":
                case "AAC":
                    sb.append("N");
                    break;
                case "AAA":
                case "AAG":
                    sb.append("K");
                    break;
                case "GAT":
                case "GAC":
                    sb.append("D");
                    break;
                case "GAA":
                case "GAG":
                    sb.append("E");
                    break;
                case "TGT":
                case "TGC":
                    sb.append("C");
                    break;

                case "TGG":
                    sb.append("W");
                    break;
                case "CGT":
                case "CGC":
                case "CGA":
                case "CGG":
                case "AGA":
                case "AGG":
                    sb.append("R");
                    break;
                case "GGT":
                case "GGC":
                case "GGA":
                case "GGG":
                    sb.append("G");
                    break;
                default:
                // do nothing;
            }

        }
        return sb.toString();
    }

    public String getComplement(String DNA) {
        String seq;
        String sequentie = "";
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
