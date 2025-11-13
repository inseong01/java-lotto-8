package lotto.utility;

import lotto.model.BonusNumber;
import lotto.model.Lotto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Calculator {
  public static Map<String, Integer> getMathResult(List<Lotto> myLottos, Lotto jackpotLotto, BonusNumber bonus) {
    Map<String, Integer> prizeCountMap = new HashMap<>(Map.of(
            "FIRST", 0,
            "SECOND", 0,
            "THIRD", 0,
            "FORTH", 0,
            "FIFTH", 0
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
      case 6 -> "FIRST";
      case 5 -> {
        if (isBonusMatch) yield "SECOND";
        yield "THIRD";
      }
      case 4 -> "FORTH";
      case 3 -> "FIFTH";
      default -> "";
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
            "FIRST", 2_000_000_000,
            "SECOND", 30_000_000,
            "THIRD", 1_500_000,
            "FORTH", 50_000,
            "FIFTH", 5_000
    );

    return prizes.get(key);
  }

}
