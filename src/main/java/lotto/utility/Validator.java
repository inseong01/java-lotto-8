package lotto.utility;

import lotto.utility.config.LottoConfig;
import lotto.utility.message.BonusMessage;
import lotto.utility.message.CashMessage;
import lotto.utility.message.InputMessage;
import lotto.utility.message.LottoMessage;

import java.util.List;
import java.util.regex.Pattern;

public class Validator {
  public static void inputValidate(String input) {
    if (input.trim().isEmpty()) {
      throw new IllegalArgumentException(
              InputMessage.INPUT_IS_EMPTY_STRING.getDescription()
      );
    }

    Pattern noneNumberPattern = Pattern.compile("\\D");

    if (noneNumberPattern.matcher(input).find()) {
      throw new IllegalArgumentException(
              InputMessage.INPUT_IS_NOT_A_NUMBER.getDescription()
      );
    }
  }

  public static void cashValidate(int cash) {
    int PRODUCE_PRICE = LottoConfig.LOTTO_PRICE.getValue();

    if (cash % PRODUCE_PRICE != 0) {
      throw new IllegalArgumentException(
              CashMessage.CASH_HAS_CHANGE.format()
      );
    }

    if (cash == 0) {
      throw new IllegalArgumentException(
              CashMessage.CASH_IS_ZERO.format()
      );
    }
  }

  public static void lottoValidate(List<Integer> numbers) {
    if (numbers.size() != 6) {
      throw new IllegalArgumentException(
              LottoMessage.NUMBER_LENGTH_IS_WRONG.getDescription()
      );
    }

    if (numbers.stream().anyMatch(number -> number < 1 || number > 45)) {
      throw new IllegalArgumentException(
              LottoMessage.NUMBER_IS_OUT_OF_RANGE.getDescription()
      );
    }

    if (numbers.stream().distinct().count() != 6) {
      throw new IllegalArgumentException(
              LottoMessage.NUMBER_IS_DUPLICATED.getDescription()
      );
    }
  }

  public static void bonusValidate(int number, List<Integer> lottoNumbers) {
    if (number < 1 || number > 45) {
      throw new IllegalArgumentException(
              BonusMessage.NUMBER_IS_OUT_OF_RANGE.getDescription()
      );
    }

    if (lottoNumbers.stream().anyMatch(num -> num == number)) {
      throw new IllegalArgumentException(
              BonusMessage.NUMBER_IS_DUPLICATED.getDescription()
      );
    }
  }
}
