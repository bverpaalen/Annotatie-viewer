/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AnnotationViewer;

import java.util.ArrayList;

public class AV {

    public static void main(String[] args) {
        makeArrayList();
    }

    public static ArrayList makeArrayList() {
        ArrayList<Sequence> NewFileArray = new ArrayList();
        Parser parse = new Parser();
        NewFile newFile = new NewFile();
        String[][] newEntries = parse.fileToString("c:\\voorbeeld1.txt");
        for (int i = 0; i < newEntries.length; i++) {
            NewFileArray.add(newFile.fillArray(newEntries[i]));
        }
        return NewFileArray;
    }
}
