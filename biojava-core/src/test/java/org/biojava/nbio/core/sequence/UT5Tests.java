package org.biojava.nbio.core.sequence;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
import org.biojava.nbio.core.sequence.compound.AmbiguityDNACompoundSet;
import org.biojava.nbio.core.sequence.compound.DNACompoundSet;
import org.biojava.nbio.core.sequence.compound.NucleotideCompound;
import org.biojava.nbio.core.sequence.storage.FourBitSequenceReader;
import org.biojava.nbio.core.sequence.storage.SingleCompoundSequenceReader;
import org.biojava.nbio.core.sequence.storage.TwoBitSequenceReader;
import org.biojava.nbio.core.sequence.template.*;
import org.biojava.nbio.core.sequence.transcription.Frame;
import org.biojava.nbio.core.sequence.views.ComplementSequenceView;
import org.biojava.nbio.core.sequence.views.ReversedSequenceView;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;



public class UT5Tests {

    /***
     * CORRECT:
     * C:
     * the the case of all three tests the value of the object does indeed conform to an expected value because if it
     * doesn't it'd throw an error. This is to be expected because a DNA Sequence can only exists out of a certain couple
     * of nucleotides which in this library have been shortened to the 4 letters A,C,G,T. This is the same with RNA and
     * Protein only with different restrictions.
     * O:
     * within these tests there is no set of values. every test has 1 value so there is no need for ordering
     * R:
     * with these objects there is no minimum and maximum boundaries for the values. but if we're talking about the size
     * of the string then yes they are within the boundaries.
     * R:
     * as you can see there is sort of a reference and those are the objects that are initialized. these tests do not have
     * control over the code within the objects they only test whether an object can be successfully initialized.
     * E:
     * as seen below after initialization we do a non-null test to see whether it has succeeded.
     * C:
     * as explained above there is only a need for one value for each test so yes we've taken that as having exactly
     * enough values.
     * T:
     * these are minimal test so they do not take long an average of 1.9 seconds and as also see is that most of the time
     * is in setup because the tests themselves on average take less than 10 ms.
     */


    @Test
    public void dnaInit() throws CompoundNotFoundException {
        //arrange
        DNASequence d;

        //act
        d = new DNASequence("GTAC");

        //assert
        assertNotNull(d);
    }

    @Test
    public void proteinInit() throws CompoundNotFoundException {
        //arrange
        ProteinSequence p;

        //act
        p = new ProteinSequence("ARNDCEQGHILKMFPSTWYVBZJX");

        //assert
        assertNotNull(p);
    }

    @Test
    public void rnaInit() throws CompoundNotFoundException {
        //arrange
        RNASequence r;

        //act
        r = new RNASequence("GUAC");

        //assert
        assertNotNull(r);
    }


}
