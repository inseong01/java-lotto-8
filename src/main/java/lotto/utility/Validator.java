package lotto.utility;

import net.bytebuddy.implementation.bytecode.Throw;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Validator {
  public static void inputValidate(String input) {
    if (input.trim().isEmpty()) {
      throw new IllegalArgumentException("[ERROR] 다시 입력해주세요.");
    }

    Pattern noneNumberPattern = Pattern.compile("\\D");

    if (noneNumberPattern.matcher(input).find()) {
      throw new IllegalArgumentException("[ERROR] 양의 정수로 입력해주세요.");
    }
  }

  public static void cashValidate(int cash) {
    int PRODUCE_PRICE = 1_000;

    if (cash % PRODUCE_PRICE != 0) {
      String message = String.format("[ERROR] %d원 단위로 작성해주세요.", PRODUCE_PRICE);
      throw new IllegalArgumentException(message);
    }

    if (cash == 0) {
      String message = String.format("[ERROR] %d원 이상으로 작성해주세요.", PRODUCE_PRICE);
      throw new IllegalArgumentException(message);
    }
  }

  public static void lottoValidate(List<Integer> numbers) {
    if (numbers.size() != 6) {
      throw new IllegalArgumentException("[ERROR] 로또 숫자는 6개이어야 합니다.");
    }

    if (numbers.stream().anyMatch(number -> number < 1 || number > 45)) {
      throw new IllegalArgumentException("[ERROR] 로또 번호는 1~45 범위 내 숫자로 구성해야 합니다.");
    }

    if (numbers.stream().distinct().count() != 6) {
      throw new IllegalArgumentException("[ERROR] 숫자는 중복되어서는 안 됩니다.");
    }
  }

  public static void bonusValidate(int number, List<Integer> lottoNumbers) {
    if (number < 1 || number > 45) {
      throw new IllegalArgumentException("[ERROR] 보너스 번호는 1~45 범위 내 숫자로 구성해야 합니다.");
    }

    if (lottoNumbers.stream().anyMatch(num -> num == number)) {
      throw new IllegalArgumentException("[ERROR] 로또 번호와 보너스 번호는 중복되어서는 안 됩니다.");
    }
  }
}
