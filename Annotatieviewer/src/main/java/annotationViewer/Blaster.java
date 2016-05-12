/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package annotationViewer;
   
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.biojava.nbio.core.sequence.io.util.IOUtils;
import org.biojava.nbio.ws.alignment.qblast.BlastProgramEnum;
import org.biojava.nbio.ws.alignment.qblast.NCBIQBlastAlignmentProperties;
import org.biojava.nbio.ws.alignment.qblast.NCBIQBlastOutputProperties;
import org.biojava.nbio.ws.alignment.qblast.NCBIQBlastService;
/**
 *
 * @author brent
 */
public class Blaster {    
    private static final String BLAST_OUTPUT_FILE = "blastOutput.xml";

    /**
     * Blasting protein sequence against given database
     * @param seq Protein sequence to blast
     * @param database database to blast against
     */
    
    
    BufferedReader blastP(String seq,String database){
        String rid = null;
        FileWriter writer = null;
        BufferedReader reader = null;        
        NCBIQBlastService blastService = new NCBIQBlastService();
        NCBIQBlastAlignmentProperties props = new NCBIQBlastAlignmentProperties();
        
        
        props.setBlastProgram(BlastProgramEnum.blastp);
        props.setBlastDatabase(database);
        
        NCBIQBlastOutputProperties outputProps = new NCBIQBlastOutputProperties();
        try{
                        // send blast request and save request id
			rid = blastService.sendAlignmentRequest(seq, props);
 
			// wait until results become available. Alternatively, one can do other computations/send other alignment requests
			while (!blastService.isReady(rid)) {
				System.out.println("Waiting for results. Sleeping for 5 seconds");
				Thread.sleep(5000);
                                blastService.isReady(rid);
			}
 
			// read results when they are ready
			InputStream in = blastService.getAlignmentResults(rid, outputProps);
			reader = new BufferedReader(new InputStreamReader(in));
                        System.out.println(reader.readLine());
                        return reader;			
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
        finally{
            IOUtils.close(writer);
            
            blastService.sendDeleteRequest(rid);
        }
        
    }

    /**
     * Blasting nucleotide(DNA) sequence against given database
     * @param seq DNA sequence
     * @param database database to blast against
     */
    BufferedReader blastN(String seq,String database){
        String rid = null;
        FileWriter writer = null;
        BufferedReader reader = null;        
        NCBIQBlastService blastService = new NCBIQBlastService();
        NCBIQBlastAlignmentProperties props = new NCBIQBlastAlignmentProperties();
        
        props.setBlastProgram(BlastProgramEnum.blastn);
        props.setBlastDatabase(database);
        
        System.out.println("Using database: "+ database);
        
        try{
        //databases
        
        NCBIQBlastOutputProperties outputProps = new NCBIQBlastOutputProperties();
        
                        // send blast request and save request id
			rid = blastService.sendAlignmentRequest(seq, props);
                        
 
			// wait until results become available. Alternatively, one can do other computations/send other alignment requests
			while (!blastService.isReady(rid)) {
				System.out.println("Waiting for results. Sleeping for 5 seconds");                               
				Thread.sleep(5000);
                                System.out.println(blastService.isReady(rid));
			}
 
			// read results when they are ready
			InputStream in = blastService.getAlignmentResults(rid, outputProps);
			reader = new BufferedReader(new InputStreamReader(in));
 
			return reader;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
        finally{
            IOUtils.close(writer);
            
            blastService.sendDeleteRequest(rid);
        }
    }
    void writeFileBlast(BufferedReader reader,String nameFile){
        FileWriter writer = null;
        try {
            // write blast output to specified file
            File f = new File(nameFile);
            System.out.println("Saving query results in file " + f.getAbsolutePath());
            writer = new FileWriter(f);
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line + System.getProperty("line.separator"));
            }
        } catch (IOException ex) {
            Logger.getLogger(Blaster.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
                IOUtils.close(reader);
                
                
            } catch (IOException ex) {
                Logger.getLogger(Blaster.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
