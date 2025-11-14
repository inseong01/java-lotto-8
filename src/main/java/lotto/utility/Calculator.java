package lotto.utility;

import lotto.model.BonusNumber;
import lotto.model.Lotto;
import lotto.utility.config.LottoPrize;
import lotto.utility.config.LottoRate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Calculator {
  public static Map<String, Integer> getMathResult(List<Lotto> myLottos, Lotto jackpotLotto, BonusNumber bonus) {
    Map<String, Integer> prizeCountMap = new HashMap<>(Map.of(
            LottoRate.MATCH_6.getRate(), 0,
            LottoRate.MATCH_5_BONUS.getRate(), 0,
            LottoRate.MATCH_5.getRate(), 0,
            LottoRate.MATCH_4.getRate(), 0,
            LottoRate.MATCH_3.getRate(), 0
    ));

    myLottos.forEach(myLotto -> {
      int count = myLotto.getMatchCountOf(jackpotLotto.getNumbers());
      boolean isBonusMatch = bonus.isMatchWith(myLotto.getNumbers());

      String rate = getRate(count, isBonusMatch);

      prizeCountMap.put(rate, prizeCountMap.get(rate) + 1);
    });

    return prizeCountMap;
  }

  private static String getRate(int count, boolean isBonusMatch) {
    return switch (count) {
      case 6 -> LottoRate.MATCH_6.getRate();
      case 5 -> {
        if (isBonusMatch) yield LottoRate.MATCH_5_BONUS.getRate();
        yield LottoRate.MATCH_5.getRate();
      }
      case 4 -> LottoRate.MATCH_4.getRate();
      case 3 -> LottoRate.MATCH_3.getRate();
      default -> LottoRate.MATCH_0.getRate();
    };
  }

  public static double getProfit(int cash, Map<String, Integer> prizeCountMap) {
    double totalPrize = prizeCountMap.keySet()
            .stream()
            .mapToDouble((key) -> getPrize(key) * prizeCountMap.get(key))
            .sum();

    return (totalPrize / cash) * 100;
  }

  private static int getPrize(String key) {
    Map<String, Integer> prizes = Map.of(
            LottoRate.MATCH_6.getRate()
            , LottoPrize.FIRST_PRIZE.getValue(),
            LottoRate.MATCH_5_BONUS.getRate()
            , LottoPrize.SECOND_PRIZE.getValue(),
            LottoRate.MATCH_5.getRate()
            , LottoPrize.THIRD_PRIZE.getValue(),
            LottoRate.MATCH_4.getRate()
            , LottoPrize.FORTH_PRIZE.getValue(),
            LottoRate.MATCH_3.getRate()
            , LottoPrize.FIFTH_PRIZE.getValue()
    );

    return prizes.get(key);
  }

}
