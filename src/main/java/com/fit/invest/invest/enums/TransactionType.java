package com.fit.invest.invest.enums;


import java.math.BigDecimal;
import java.math.RoundingMode;

import com.fit.invest.invest.domain.Account;

public enum TransactionType {

    DEPOSIT {
        @Override
        public void doTransactionOperation(Account account, BigDecimal cashValue) {
            BigDecimal taxPercentageValue = calculateTax(BigDecimal.valueOf(0.0005), cashValue);
            cashValue = cashValue.add(taxPercentageValue.negate());
            account.addMoney(cashValue);
        }
    };

    public abstract void doTransactionOperation(Account account, BigDecimal cashValue);

    BigDecimal calculateTax(BigDecimal percentage, BigDecimal cashValue) {
        return cashValue
                .multiply(percentage)
                .divide(new BigDecimal(100), RoundingMode.HALF_DOWN);
    }

}