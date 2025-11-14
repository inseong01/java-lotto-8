package lotto.model;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.utility.config.LottoConfig;

import java.util.List;

public class LottoMachine {
  public static Lotto generateLotto() {
    return new Lotto(getNumbers());
  }

  private static List<Integer> getNumbers() {
    int START_RANGE = LottoConfig.NUMBER_START_RANGE.getValue();
    int END_RANGE = LottoConfig.NUMBER_END_RANGE.getValue();
    int NUMBER_AMOUNT = LottoConfig.NUMBER_AMOUNT.getValue();

    return Randoms.pickUniqueNumbersInRange(START_RANGE, END_RANGE, NUMBER_AMOUNT);
  }

  public static Lotto generateJackpotLotto(List<Integer> numbers) {
    return new Lotto(numbers);
  }

  public static BonusNumber generateBonusNumber(int number, List<Integer> numbers) {
    return new BonusNumber(number, numbers);
  }
}
