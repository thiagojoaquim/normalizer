package com.feefo.normalizer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LevenshteinDistanceNormalizerTest {

    private Normalizer normalizer;


    @BeforeEach
    public void setUp() {
        normalizer = new LevenshteinDistanceNormalizer();
    }

    @Test
    void testWithSuccess() {
        Assertions.assertEquals("Software Engineer",
                normalizer.normalize("Java Engineer"));
        Assertions.assertEquals("Software Engineer",
                normalizer.normalize("C# Engineer"));
        Assertions.assertEquals("Accountant", normalizer.normalize("Chief Accountant"));
    }

}
