package com.customer.customersrewardrestapi.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UtilTest {
    @Test
    void calculateRewardPoints() {
        Assertions.assertEquals(90L, Util.calculateRewardPoints(120d));
        Assertions.assertEquals(25L, Util.calculateRewardPoints(75d));
        Assertions.assertEquals(450L, Util.calculateRewardPoints(300d));
        Assertions.assertEquals(150L, Util.calculateRewardPoints(150d));
        Assertions.assertEquals(0L, Util.calculateRewardPoints(25d));
    }
}