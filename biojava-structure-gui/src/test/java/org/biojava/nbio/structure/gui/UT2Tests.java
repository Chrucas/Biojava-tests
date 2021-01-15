package org.biojava.nbio.structure.gui;

import org.biojava.nbio.structure.align.gui.AlignmentGui;
import org.junit.Assert;
import org.junit.Test;

public class UT2Tests {

    /***
     * CORRECT:
     * In this test we test the posibility of calling the gui this is done with one line of code so it's simple and this
     * was also the expected format as it's hard to unit test a gui.
     * In this test there is no need for ordering values as there aren't any values to be ordered.
     * Same as above there isn't really minimum and maximum for the values.
     * this test doesn't really refer to outside of its scope it only tests the class/part it is supposed to test the gui.
     * So it does refer to the gui that is instanced in the AlignmentGui class but that is all it does.
     */
    @Test
    public void GUITest(){
        //arrange
        boolean guiCalled = false;

        //act
        try{
            AlignmentGui.getInstance();
            guiCalled = true;
        }catch(Exception e){
            guiCalled=false;
        }

        //assert
        Assert.assertTrue(guiCalled);

    }
}
