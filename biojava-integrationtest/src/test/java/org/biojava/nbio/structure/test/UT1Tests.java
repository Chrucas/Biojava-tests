package org.biojava.nbio.structure.test;

import org.biojava.nbio.structure.Mutator;
import org.biojava.nbio.structure.Structure;
import org.biojava.nbio.structure.io.PDBFileParser;
import org.biojava.nbio.structure.io.PDBFileReader;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class UT1Tests {

    /*
    CORRECT:
    The meaning of this test is to mutate the contents of a pdb file. the results of this test will show that the contents
    have indeed been changed so in that case the results of this test would indeed be the expected result.
    In this test there's 2 important values the old structure, which is acquired from a pdb file and the new/mutated
    structure and they will always be in this a specific order: first old then new.
    the values of these files do not really adhere to a range so on that aspect can not really be commented on.
    In this test we make use of pdb files which are not really under control of this test but are necessary to complete it.
    What is also done in this test is to check if the files exist so that the errors can be caught and the test set to fail.
     */
    @Test
    public void mutateTest() {
        String outputfile = "mutated.pdb";
        try{
            //arrange
            InputStream inStream = this.getClass().getResourceAsStream("/5pti.pdb");
            assertNotNull(inStream);
            PDBFileParser pdbpars = new PDBFileParser();
            Structure structure = pdbpars.parsePDBFile(inStream) ;
            assertNotNull(structure);
            System.out.println("===================");
            System.out.println(structure);
            System.out.println("===================");
            String chainId = "A";
            String pdbResnum = "3";
            String newType = "ARG";

            // mutate the original structure and create a new one.
            // lets side chain point into the same direction, but only uses Cb atom
            //act
            Mutator m = new Mutator();
            Structure newstruc = m.mutate(structure, chainId, pdbResnum, newType);
            FileOutputStream out = new FileOutputStream(outputfile);
            System.out.println("===================");
            PrintStream p = new PrintStream(out);
            p.println(newstruc.toPDB());
            p.close();
            System.out.println(newstruc);
            System.out.println("===================");

            //assert
            assertNotEquals(structure, newstruc);
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }


}
