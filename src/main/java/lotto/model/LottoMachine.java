package lotto.model;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class LottoMachine {
  public static Lotto generateLotto() {
    return new Lotto(getNumbers());
  }

  private static List<Integer> getNumbers() {
    int START_RANGE = 1;
    int END_RANGE = 45;
    int NUMBER_AMOUNT = 6;

    return Randoms.pickUniqueNumbersInRange(START_RANGE, END_RANGE, NUMBER_AMOUNT);
  }

  public static Lotto generateJackpotLotto(List<Integer> numbers) {
    return new Lotto(numbers);
  }

  public static BonusNumber generateBonusNumber(int number, List<Integer> numbers) {
    return new BonusNumber(number, numbers);
  }
}
