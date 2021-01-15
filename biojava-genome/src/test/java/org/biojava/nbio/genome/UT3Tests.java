package org.biojava.nbio.genome;

import org.biojava.nbio.core.sequence.ChromosomeSequence;
import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.GeneSequence;
import org.biojava.nbio.core.sequence.ProteinSequence;
import org.biojava.nbio.core.sequence.io.FastaWriterHelper;
import org.biojava.nbio.core.sequence.io.GenbankReaderHelper;
import org.biojava.nbio.core.sequence.io.GenbankWriterHelper;
import org.biojava.nbio.genome.GeneFeatureHelper;
import org.biojava.nbio.genome.parsers.gff.GFF3Writer;
import org.junit.Test;
import static org.junit.Assert.*;


import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

public class UT3Tests {
    @Test
    public void readGBFileTest() {
        //Arrange
        File dnaFile = new File("C:\\Users\\Dr.Chruc\\Documents\\biojava" +
                                            "\\biojava-core\\src\\test\\resources\\NM_000266.gb");
        ByteArrayOutputStream fragWriter = new ByteArrayOutputStream();
        ArrayList<DNASequence> seqs = new ArrayList<>();
        //Act
        try {
            LinkedHashMap<String, DNASequence> dnaSequences = GenbankReaderHelper.readGenbankDNASequence(dnaFile);
            for (DNASequence sequence : dnaSequences.values()) {
                System.out.println(sequence.getSequenceAsString());
            }

            for(DNASequence seq : dnaSequences.values()) {
                seqs.add(seq);
            }
            GenbankWriterHelper.writeNucleotideSequence(fragWriter, seqs,
                    GenbankWriterHelper.LINEAR_DNA);
            //Assert
            try {
                assertNotNull(fragWriter);
                System.out.println(fragWriter.toString());
            }catch(AssertionError e){
                System.out.println("strings are equal, fail");
                fail();
            }
        } catch (Exception e) {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    public void writeGBFileTest(){
        //Arrange
        File dnaFile = new File("C:\\Users\\Dr.Chruc\\Documents\\biojava" +
                "\\biojava-core\\src\\test\\resources\\NM_000266.gb");
        File myFile = new File("Testfile.gb");
        ArrayList<DNASequence> seqs = new ArrayList<>();
        LinkedHashMap<String, DNASequence> dnaSequences = null;
        try {
            //Act
            if(!myFile.exists()){
                if (myFile.createNewFile()) {
                    System.out.println("File created: " + myFile.getName());
                } else {
                    System.out.println("File already exists.");
                }
            }
            dnaSequences = GenbankReaderHelper.readGenbankDNASequence(dnaFile);
            for(DNASequence seq : dnaSequences.values()) {
                seqs.add(seq);
            }
            GenbankWriterHelper.writeNucleotideSequence(myFile, seqs);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Assert
        assertTrue(myFile.exists());
    }
}
