package com.bootcamp.incomeproductservice.model;

public class Constant {

  public enum IncomeAccountTypeId {
    PERSONAL_CREDIT_ID("1"), BUSINESS_CREDIT_ID("2");
    public final String type;

    IncomeAccountTypeId(String type) {
      this.type = type;
    }
  }

  public enum ClientType {
    NATURAL_PERSON("1"), BUSINESS("2");
    public final String type;

    ClientType(String type) {
      this.type = type;
    }
  }

  public enum FinancialCompany {
    VISA("VISA"), MASTERCARD("MASTERCARD");
    public final String type;

    FinancialCompany(String type) {
      this.type = type;
    }
  }

  public enum CreditSnPrefix {
    PERSONAL_CREDIT("011"), BUSINESS_CREDIT("012");
    public final String type;

    CreditSnPrefix(String type) {
      this.type = type;
    }
  }

  public enum CreditCardSnPrefix {
    VISA("4285"), MASTERCARD("5254");
    public final String type;

    CreditCardSnPrefix(String type) {
      this.type = type;
    }
  }
}
