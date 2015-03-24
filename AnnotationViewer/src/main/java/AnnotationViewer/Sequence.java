package AnnotationViewer;


public abstract class Sequence {

    private String sequence;
    private String description;

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Sequence() {
        super();
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String newSeq) {
        sequence = newSeq;
    }

    public int getLength() {
        return sequence.length();
    }
}
