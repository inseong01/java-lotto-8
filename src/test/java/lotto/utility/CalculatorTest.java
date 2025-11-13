package lotto.utility;

import lotto.model.BonusNumber;
import lotto.model.Lotto;
import lotto.model.LottoMachine;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class CalculatorTest {
  @Test
  void 로또_결과_집계를_반환한다() {
    List<Lotto> MY_LOTTO = List.of(new Lotto(List.of(1, 2, 3, 4, 5, 15)));
    Lotto JACKPOT_LOTTO = LottoMachine.generateJackpotLotto(List.of(1, 2, 3, 4, 5, 6));
    BonusNumber BONUS_NUMBER = new BonusNumber(15, JACKPOT_LOTTO.getNumbers());

    Map<String, Integer> output = new HashMap<>(Map.of(
            "FIRST", 0,
            "SECOND", 1,
            "THIRD", 0,
            "FORTH", 0,
            "FIFTH", 0
    ));


    assertThat(Calculator.getMathResult(MY_LOTTO, JACKPOT_LOTTO, BONUS_NUMBER)).isEqualTo(output);
  }

  @Test
  void 로또_수익률을_반환한다() {
    int cash = 8_000;
    Map<String, Integer> prizeCountMap = new HashMap<>(Map.of(
            "FIRST", 0,
            "SECOND", 0,
            "THIRD", 0,
            "FORTH", 0,
            "FIFTH", 1
    ));

    assertThat(Calculator.getProfit(cash, prizeCountMap)).isEqualTo(62.5);
  }
}
