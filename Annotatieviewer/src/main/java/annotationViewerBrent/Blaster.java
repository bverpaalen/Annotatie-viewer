/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package annotationViewerBrent;
   
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    
    void blastP(String seq,String database){
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
			}
 
			// read results when they are ready
			InputStream in = blastService.getAlignmentResults(rid, outputProps);
			reader = new BufferedReader(new InputStreamReader(in));
 
			// write blast output to specified file
			File f = new File(BLAST_OUTPUT_FILE);
			System.out.println("Saving query results in file " + f.getAbsolutePath());
			writer = new FileWriter(f);
 
			String line;
			while ((line = reader.readLine()) != null) {
				writer.write(line + System.getProperty("line.separator"));
			}
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        finally{
            IOUtils.close(writer);
            IOUtils.close(reader);
            
            blastService.sendDeleteRequest(rid);
        }
        
    }
    
    void blastN(String seq,String database){
        String rid = null;
        FileWriter writer = null;
        BufferedReader reader = null;        
        NCBIQBlastService blastService = new NCBIQBlastService();
        NCBIQBlastAlignmentProperties props = new NCBIQBlastAlignmentProperties();
        
        props.setBlastProgram(BlastProgramEnum.blastn);
        props.setBlastDatabase(database);
        
        NCBIQBlastOutputProperties outputProps = new NCBIQBlastOutputProperties();
        try{
                        // send blast request and save request id
			rid = blastService.sendAlignmentRequest(seq, props);
 
			// wait until results become available. Alternatively, one can do other computations/send other alignment requests
			while (!blastService.isReady(rid)) {
				System.out.println("Waiting for results. Sleeping for 5 seconds");
				Thread.sleep(5000);
			}
 
			// read results when they are ready
			InputStream in = blastService.getAlignmentResults(rid, outputProps);
			reader = new BufferedReader(new InputStreamReader(in));
 
			// write blast output to specified file
			File f = new File(BLAST_OUTPUT_FILE);
			System.out.println("Saving query results in file " + f.getAbsolutePath());
			writer = new FileWriter(f);
 
			String line;
			while ((line = reader.readLine()) != null) {
				writer.write(line + System.getProperty("line.separator"));
			}
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        finally{
            IOUtils.close(writer);
            IOUtils.close(reader);
            
            blastService.sendDeleteRequest(rid);
        }
    }
}
