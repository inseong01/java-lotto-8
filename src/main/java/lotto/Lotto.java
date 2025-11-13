package lotto;

import lotto.utility.Validator;

import java.util.List;

public class Lotto {
  private final List<Integer> numbers;

  public Lotto(List<Integer> numbers) {
      validate(numbers);
      this.numbers = numbers;
  }

  private void validate(List<Integer> numbers) {
    Validator.lottoValidate(numbers);
  }

  public List<Integer> getNumbers() {
      return numbers;
  }

  public  int getMatchCountOf(List<Integer> jackpotNumbers) {

    return numbers.stream().reduce(0, (subTotal, num) -> {
      if (jackpotNumbers.contains(num)) {
        return subTotal + 1;
      }
      return subTotal;
    });
  }
}
