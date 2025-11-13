package lotto.model;

import lotto.utility.Validator;

public class Cash {
  private final int cash;

  public Cash(int number) {
    validate(number);
    this.cash = number;
  }

  private void validate(int number) {
    Validator.cashValidate(number);
  }

  public int getCash() {
    return cash;
  }

  public int getAmountOf(int price) {
    return cash / price;
  }
}
