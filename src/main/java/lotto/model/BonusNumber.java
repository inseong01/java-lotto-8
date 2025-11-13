package lotto.model;

import lotto.utility.Validator;

import java.util.List;

public class BonusNumber {
  private final int number;

  public BonusNumber(int number, List<Integer> lottoNumber) {
    validate(number, lottoNumber);
    this.number = number;
  }

  private void validate(int number, List<Integer> lottoNumber) {
    Validator.bonusValidate(number, lottoNumber);
  }

  public int getNumber() {
    return number;
  }

  public boolean isMatchWith(List<Integer> numbers) {
    return numbers.contains(number);
  }
}
