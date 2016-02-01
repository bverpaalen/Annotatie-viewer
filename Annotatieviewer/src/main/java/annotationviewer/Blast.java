/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package annotationviewer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.biojava.nbio.core.exceptions.*;
import org.biojava.nbio.core.sequence.*;
import org.biojava.nbio.core.sequence.io.util.IOUtils;
import org.biojava.nbio.core.util.*;
import org.biojava.nbio.ws.alignment.*;
import static org.biojava.nbio.ws.alignment.qblast.BlastAlignmentParameterEnum.ENTREZ_QUERY;
import org.biojava.nbio.ws.alignment.qblast.BlastOutputParameterEnum;
import org.biojava.nbio.ws.alignment.qblast.BlastProgramEnum;
import org.biojava.nbio.ws.alignment.qblast.NCBIQBlastAlignmentProperties;
import org.biojava.nbio.ws.alignment.qblast.NCBIQBlastOutputProperties;
import org.biojava.nbio.ws.alignment.qblast.NCBIQBlastService;

/**
 *
 * @author brent
 * met dank aan BioJava en cookbook3 url = http://biojava.org/wiki/BioJava:CookBook3:NCBIQBlastService
 */
public class Blast {
    //testje
    /*
    public static void main(String[] args) throws Exception {
        //blastp("MKWVTFISLLFLFSSAYSRGVFRRDAHKSEVAHRFKDLGEENFKALVLIAFAQYLQQCP",200); 
        //blastn("GGTGACGGAATTCGCCCGGACGCAAAAGACGGACAGCTAGGTATCCTGAGCACGGTTGCGCGTCCGAATCAAGCT",200);
    }*/
private static final String OUTPUT_FILE = "blastOutput.xml"; //file naam met output blast
static int aantalAligNumberBP = 100; //standaard aantal alignments in de blast bij blastp
static int aantalAligNumberBN = 100; //standaard aantal alignments in de blast bij blastn    
/**
     *
     * @param seq de eiwit sequentie die geblast moet worden (standaard = 100)
     * @param aantalAligNumber anntal alignments er in de blast gestopt moeten worden
     * @return returned de pathway van de file waar de resultaten van de blast in staan
     * @throws CompoundNotFoundException
     * @throws Exception
     */
    public static String blastp(String seq,int aantalAligNumber) throws CompoundNotFoundException, Exception{
    NCBIQBlastService service = new NCBIQBlastService();
    String filePath = "";
    
    //alignments options
    NCBIQBlastAlignmentProperties ncbiProp = new NCBIQBlastAlignmentProperties();
    
    //wat voor blast het is
    ncbiProp.setBlastProgram(BlastProgramEnum.blastp);
    
    //database
    ncbiProp.setBlastDatabase("swissprot");
    
    //TO DO uitzoeken wat dit doet
    //Wel fijn om te weten 
    ncbiProp.setAlignmentOption(ENTREZ_QUERY , "\"serum albumin\"[Protein name] AND mammals[Organism]");
    
    //output options
    NCBIQBlastOutputProperties outputProp = new NCBIQBlastOutputProperties();
    
    outputProp.setAlignmentNumber(aantalAligNumber);
    String aantalAligNumberString = aantalAligNumber + "";
    outputProp.setOutputOption(BlastOutputParameterEnum.ALIGNMENTS,aantalAligNumberString);
    
    String rid = null; //blast request ID
    FileWriter writer = null;
    BufferedReader reader = null;
    
    try{
        //stuurt blast request and saved request id
        rid = service.sendAlignmentRequest(seq, ncbiProp);
        
        //wacht tot het resultaat beschikbaar komt
        while(!service.isReady(rid)){
            System.out.println("Aan het wachten voor resultaat, blijf nog ff in bed liggen voor 5 seconden");
            Thread.sleep(500);
        }
        //leest resultaat als het klaar is
        InputStream in = service.getAlignmentResults(rid, outputProp);
        reader = new BufferedReader (new InputStreamReader (in));
        
        //schrijft blast output naar specifieke file
        File f = new File(OUTPUT_FILE);
        filePath = f.getAbsolutePath();
        System.out.println("ff query resultaat opslaan jwz toch in het bestandje: "+ filePath);
        writer = new FileWriter(f);
        
        String lijn;
        while((lijn = reader.readLine()) != null){
            writer.write(lijn + System.getProperty("line.separator"));
    }
    }catch (Exception e){ 
        System.out.println(e.getMessage());
        e.printStackTrace();       
    }
    //even dweilen alles schoonmaken
    IOUtils.close(writer);
    IOUtils.close(reader);
    return filePath;   
}
    
    
    //zorgt ervoor dat blastp met standaard alignumber meegegeven kan worden
    public static String blastp(String seq) throws CompoundNotFoundException, Exception{
    NCBIQBlastService service = new NCBIQBlastService();
    String filePath = "";
    
    //alignments options
    NCBIQBlastAlignmentProperties ncbiProp = new NCBIQBlastAlignmentProperties();
    
    //wat voor blast het is
    ncbiProp.setBlastProgram(BlastProgramEnum.blastp);
    
    //database
    ncbiProp.setBlastDatabase("swissprot");
    
    //TO DO uitzoeken wat dit doet
    //Wel fijn om te weten 
    ncbiProp.setAlignmentOption(ENTREZ_QUERY , "\"serum albumin\"[Protein name] AND mammals[Organism]");
    
    //output options
    NCBIQBlastOutputProperties outputProp = new NCBIQBlastOutputProperties();
    
    outputProp.setAlignmentNumber(aantalAligNumberBP);
    String aantalAligNumberString = aantalAligNumberBP + "";
    outputProp.setOutputOption(BlastOutputParameterEnum.ALIGNMENTS,aantalAligNumberString);
    
    String rid = null; //blast request ID
    FileWriter writer = null;
    BufferedReader reader = null;
    
    try{
        //stuurt blast request and saved request id
        rid = service.sendAlignmentRequest(seq, ncbiProp);
        
        //wacht tot het resultaat beschikbaar komt
        while(!service.isReady(rid)){
            System.out.println("Aan het wachten voor resultaat, blijf nog ff in bed liggen voor 5 seconden");
            Thread.sleep(500);
        }
        //leest resultaat als het klaar is
        InputStream in = service.getAlignmentResults(rid, outputProp);
        reader = new BufferedReader (new InputStreamReader (in));
        
        //schrijft blast output naar specifieke file
        File f = new File(OUTPUT_FILE);
        filePath = f.getAbsolutePath();
        System.out.println("ff query resultaat opslaan jwz toch in het bestandje: "+ filePath);
        writer = new FileWriter(f);
        
        String lijn;
        while((lijn = reader.readLine()) != null){
            writer.write(lijn + System.getProperty("line.separator"));
    }
    }catch (Exception e){ 
        System.out.println(e.getMessage());
        e.printStackTrace();       
    }
    //even dweilen alles schoonmaken
    IOUtils.close(writer);
    IOUtils.close(reader);
    return filePath;   
} 
    
    
    public static String blastn(String seq,int aantalAligNumber) throws CompoundNotFoundException, Exception{
    NCBIQBlastService service = new NCBIQBlastService();
    String filePath = "";
    
    //alignments options
    NCBIQBlastAlignmentProperties ncbiProp = new NCBIQBlastAlignmentProperties();
    
    //wat voor blast het is
    ncbiProp.setBlastProgram(BlastProgramEnum.blastn);
    
    //database
    //TO DO welke database moet er gebruikt worden bij blastn?
    //zie ftp://ftp.ncbi.nlm.nih.gov/pub/factsheets/HowTo_BLASTGuide.pdf bladzijde 3 voor meer informatie
    ncbiProp.setBlastDatabase("est");
    
    //TO DO uitzoeken wat dit doet
    //Wel fijn om te weten 
    //blastalignmentparameterenu key,string val
    //http://www.biojava.org/docs/api/index.html zoek naar NCBIQBlastAlignmentProperties
    ncbiProp.setAlignmentOption(ENTREZ_QUERY , "\"serum albumin\"[Protein name] AND mammals[Organism]");
    
    //output options
    NCBIQBlastOutputProperties outputProp = new NCBIQBlastOutputProperties();
    
    outputProp.setAlignmentNumber(aantalAligNumber);
    String aantalAligNumberString = aantalAligNumber + "";
    outputProp.setOutputOption(BlastOutputParameterEnum.ALIGNMENTS,aantalAligNumberString);
    
    String rid = null; //blast request ID
    FileWriter writer = null;
    BufferedReader reader = null;
    
    try{
        //stuurt blast request and saved request id
        rid = service.sendAlignmentRequest(seq, ncbiProp);
        
        //wacht tot het resultaat beschikbaar komt
        while(!service.isReady(rid)){
            System.out.println("Aan het wachten voor resultaat, blijf nog ff in bed liggen voor 5 seconden");
            Thread.sleep(500);
        }
        //leest resultaat als het klaar is
        InputStream in = service.getAlignmentResults(rid, outputProp);
        reader = new BufferedReader (new InputStreamReader (in));
        
        //schrijft blast output naar specifieke file
        File f = new File(OUTPUT_FILE);
        filePath = f.getAbsolutePath();
        System.out.println("ff query resultaat opslaan jwz toch in het bestandje: "+ filePath);
        writer = new FileWriter(f);
        
        String lijn;
        while((lijn = reader.readLine()) != null){
            writer.write(lijn + System.getProperty("line.separator"));
    }
    }catch (Exception e){ 
        System.out.println(e.getMessage());
        e.printStackTrace();       
    }
    //even dweilen alles schoonmaken
    IOUtils.close(writer);
    IOUtils.close(reader);
    return filePath;   
}
    
   public static String blastn(String seq) throws CompoundNotFoundException, Exception{
    NCBIQBlastService service = new NCBIQBlastService();
    String filePath = "";
    
    //alignments options
    NCBIQBlastAlignmentProperties ncbiProp = new NCBIQBlastAlignmentProperties();
    
    //wat voor blast het is
    ncbiProp.setBlastProgram(BlastProgramEnum.blastn);
    
    //database
    //TO DO welke database moet er gebruikt worden bij blastn?
    //zie ftp://ftp.ncbi.nlm.nih.gov/pub/factsheets/HowTo_BLASTGuide.pdf bladzijde 3 voor meer informatie
    ncbiProp.setBlastDatabase("est");
    
    //TO DO uitzoeken wat dit doet
    //Wel fijn om te weten 
    //blastalignmentparameterenu key,string val
    //http://www.biojava.org/docs/api/index.html zoek naar NCBIQBlastAlignmentProperties
    ncbiProp.setAlignmentOption(ENTREZ_QUERY , "\"serum albumin\"[Protein name] AND mammals[Organism]");
    
    //output options
    NCBIQBlastOutputProperties outputProp = new NCBIQBlastOutputProperties();
    
    outputProp.setAlignmentNumber(aantalAligNumberBN);
    String aantalAligNumberString = aantalAligNumberBN + "";
    outputProp.setOutputOption(BlastOutputParameterEnum.ALIGNMENTS,aantalAligNumberString);
    
    String rid = null; //blast request ID
    FileWriter writer = null;
    BufferedReader reader = null;
    
    try{
        //stuurt blast request and saved request id
        rid = service.sendAlignmentRequest(seq, ncbiProp);
        
        //wacht tot het resultaat beschikbaar komt
        while(!service.isReady(rid)){
            System.out.println("Aan het wachten voor resultaat, blijf nog ff in bed liggen voor 5 seconden");
            Thread.sleep(500);
        }
        //leest resultaat als het klaar is
        InputStream in = service.getAlignmentResults(rid, outputProp);
        reader = new BufferedReader (new InputStreamReader (in));
        
        //schrijft blast output naar specifieke file
        File f = new File(OUTPUT_FILE);
        filePath = f.getAbsolutePath();
        System.out.println("ff query resultaat opslaan jwz toch in het bestandje: "+ filePath);
        writer = new FileWriter(f);
        
        String lijn;
        while((lijn = reader.readLine()) != null){
            writer.write(lijn + System.getProperty("line.separator"));
    }
    }catch (Exception e){ 
        System.out.println(e.getMessage());
        e.printStackTrace();       
    }
    //even dweilen alles schoonmaken
    IOUtils.close(writer);
    IOUtils.close(reader);
    return filePath;   
} 
}
