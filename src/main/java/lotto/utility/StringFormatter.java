package lotto.utility;

import lotto.utility.config.LottoPrize;
import lotto.utility.config.LottoRate;

import java.text.NumberFormat;
import java.util.Objects;

public class StringFormatter {
  public static String numberToPrice(String rate) {
    String prize = getPrize(rate);
    return String.format("(%s원)", prize);
  }

  private static String getPrize(String rate) {
    if (Objects.equals(rate, LottoRate.MATCH_6.getRate())) {
      return nf(LottoPrize.FIRST_PRIZE.getValue());
    }
    if (Objects.equals(rate, LottoRate.MATCH_5_BONUS.getRate())) {
      return nf(LottoPrize.SECOND_PRIZE.getValue());
    }
    if (Objects.equals(rate, LottoRate.MATCH_5.getRate())) {
      return nf(LottoPrize.THIRD_PRIZE.getValue());
    }
    if (Objects.equals(rate, LottoRate.MATCH_4.getRate())) {
      return nf(LottoPrize.FORTH_PRIZE.getValue());
    }
    if (Objects.equals(rate, LottoRate.MATCH_3.getRate())) {
      return nf(LottoPrize.FIFTH_PRIZE.getValue());
    }
    return "";
  }

  private static String nf(int value) {
    NumberFormat nf = NumberFormat.getNumberInstance();
    return nf.format(value);
  }

  public static String rateToMatchCountPrompt(String rate) {
    switch (rate) {
      case "FIRST":
        return "6개 일치 ";
      case "SECOND":
        return "5개 일치, 보너스 볼 일치 ";
      case "THIRD":
        return "5개 일치 ";
      case "FORTH":
        return "4개 일치 ";
      case "FIFTH":
        return "3개 일치 ";
    }
    ;
    return "";
  }
}
