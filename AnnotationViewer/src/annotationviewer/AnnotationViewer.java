/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package annotationviewer;

import java.util.ArrayList;

public class AnnotationViewer {

    static ArrayList<Polynucleotide> NewFileArray = new ArrayList();

    public void bla() {
        AV.makeArrayList();
        for (int i = 0; i < NewFileArray.size(); i++) {
            if (NewFileArray.get(i) == null) {
            } else {
                System.out.println("i: " + i);
                System.out.println(NewFileArray.get(i).getDescription());
                System.out.println(NewFileArray.get(i).getComplement());
            }
        }

    }

    public static void makeArrayList() {
        Parser parse = new Parser();
        NewFile newFile = new NewFile();
        String[][] newEntries = parse.fileToString("c:\\homosapiens chromosome X.txt");
        for (int i = 1; i < newEntries.length; i++) {
            NewFileArray.add(newFile.stringToObject(newEntries[i]));
        }
    }
}
