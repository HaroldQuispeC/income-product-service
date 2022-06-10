package com.bootcamp.incomeproductservice.model;

public  class Constant {

    public enum IncomeAccountTypeId
    {

        PERSONAL_CREDIT_ID("1"), BUSINESS_CREDIT_ID("2");

        public final String type;
        IncomeAccountTypeId(String type)
        {
            this.type = type;
        }

    }
}
