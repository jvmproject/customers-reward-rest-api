package com.customer.customersrewardrestapi.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Util {

    public Long calculateRewardPoints(Double amount) {

        Double totalPoints = 0d;

        if (amount > 50 && amount <= 100) {
            totalPoints += (amount.longValue() - 50);

        }

        if (amount > 100) {
            totalPoints += (amount.longValue() - 100) * 2;
            totalPoints += 50;
        }

        return totalPoints.longValue();
    }
}
