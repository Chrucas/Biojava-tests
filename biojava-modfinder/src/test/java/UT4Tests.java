
import org.biojava.nbio.protmod.ProteinModificationRegistry;
import org.biojava.nbio.protmod.structure.ModifiedCompound;
import org.biojava.nbio.protmod.structure.ProteinModificationIdentifier;
import org.biojava.nbio.protmod.structure.StructureGroup;
import org.biojava.nbio.structure.ResidueNumber;
import org.biojava.nbio.structure.Structure;
import org.biojava.nbio.structure.io.PDBFileReader;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.Assert.assertNotNull;

public class UT4Tests {

    Set<ModifiedCompound> identifyAllModfications(Structure struc) {
        ProteinModificationIdentifier parser = new ProteinModificationIdentifier();
        parser.identify(struc);
        Set<ModifiedCompound> mcs = parser.getIdentifiedModifiedCompound();
        return mcs;
    }

    List identifyPhosphosites(Structure struc) {
        List<ResidueNumber> phosphosites = new ArrayList<>();
        ProteinModificationIdentifier parser = new ProteinModificationIdentifier();
        parser.identify(struc, ProteinModificationRegistry.getByKeyword("phosphoprotein"));
        Set<ModifiedCompound> mcs = parser.getIdentifiedModifiedCompound();
        for (ModifiedCompound mc : mcs) {
            Set<StructureGroup> groups = mc.getGroups(true);
            for (StructureGroup group : groups) {
                phosphosites.add(group.getPDBResidueNumber());
            }
        }
        return phosphosites;
    }

    /***
     CORRECT:
     Because we are software developers and not biologists it is not possible for us to say that the result of this test
     could be an expected format. But if there are no errors then it should be good because the logic is sound.
     There is an order in the values that are reported if that order is ordered as needed is not able to be found out.
     As this is a test to get all possible modifications I'd say there is not really a necessary minimum and maximum as
     long as it does not take too much computation.
     As seen in the test code there are some references that are outside of the method. while 2 methods where remade
     in the class.
     There has been made some use of external pdb files but without those this test would be impossible to do.
     As seen in the code the printen values are also given a non-null test to show that they do exist.
     As mentioned above seeing as we wanted to find all of the modifcations this should exactly enough values.
     */

    @Test
    public void modfinderTest(){
        try {
            //arrange
            PDBFileReader reader = new PDBFileReader();
            // identify all modificaitons from PDB:1CAD and print them
            String pdbId = "1CAD";
            Structure struc = reader.getStructureById(pdbId);
            Set<ModifiedCompound> mcs = identifyAllModfications(struc);

            //act
            System.out.println("=================");
            for (ModifiedCompound mc : mcs) {
                //assert
                assertNotNull(mc);
                System.out.println(mc.toString());
            }
            System.out.println("=================");


        } catch(IOException e) {
            System.out.println("failed");
            fail();
            
        }
    }

    @Test
    public void phosphoFinderTest(){
        try{
            // identify all phosphosites from PDB:3MVJ and print them
            //arrange
            PDBFileReader reader = new PDBFileReader();
            String pdbId = "3MVJ";
            Structure struc = reader.getStructureById(pdbId);
            List<ResidueNumber> psites = identifyPhosphosites(struc);

            //act
            System.out.println("=================");
            for (ResidueNumber psite : psites) {
                //assert
                assertNotNull(psite);
                System.out.println(psite.toString());
            }
            System.out.println("=================");
        }catch(IOException e) {
            System.out.println("failed");
            fail();
            

        }
    }


}
