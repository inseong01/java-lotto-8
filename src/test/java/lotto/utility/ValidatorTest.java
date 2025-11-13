package lotto.utility;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ValidatorTest {
  @Nested
  class 현금_로또_보너스_번호_입력_검증 {

    @Test
    void 비어_있는_경우_예외_발생() {
      String[] inputs = {"", "   "};

      for (String input : inputs) {
        assertThatThrownBy(() -> {
          Validator.inputValidate(input);
        }).isInstanceOf(IllegalArgumentException.class);
      }
    }

    @Test
    void 문자가_포함_되어_있는_경우_예외_발생() {
      String[] inputs = {"ad", "0x123"};

      for (String input : inputs) {
        assertThatThrownBy(() -> {
          Validator.inputValidate(input);
        }).isInstanceOf(IllegalArgumentException.class);
      }
    }
  }

  @Nested
  class 현금_검증 {

    @Test
    void 상품_금액으로_나누어_떨어지지_않는_경우_예외_발생() {
      List<Integer> inputs = List.of(1_200, 2_002);

      for (int input : inputs) {
        assertThatThrownBy(() -> {
          Validator.cashValidate(input);
        }).isInstanceOf(IllegalArgumentException.class);
      }
    }

    @Test
    void 금액이_0인_경우_예외_발생() {
      List<Integer> inputs = List.of(0);

      for (int input : inputs) {
        assertThatThrownBy(() -> {
          Validator.cashValidate(input);
        }).isInstanceOf(IllegalArgumentException.class);
      }
    }
  }

  @Nested
  class 로또_검증 {

    @Test
    void 숫자가_6개가_아닌_경우_예외_발생() {
      List<Integer> LESS_NUMBERS_LENGTH_1 = List.of(1);
      List<Integer> LESS_NUMBERS_LENGTH_2 = List.of(1, 2, 3);
      List<Integer> LESS_NUMBERS_LENGTH_3 = List.of(1, 2, 3, 4, 5, 6, 7);

      List<List<Integer>> inputs = List.of(LESS_NUMBERS_LENGTH_1, LESS_NUMBERS_LENGTH_2, LESS_NUMBERS_LENGTH_3);

      for (List<Integer> input : inputs) {
        assertThatThrownBy(() -> {
          Validator.lottoValidate(input);
        }).isInstanceOf(IllegalArgumentException.class);
      }
    }

    @Test
    void 숫자_범위_밖인_경우_예외_발생() {
      List<Integer> OUT_OF_RANGE_NUMBERS_1 = List.of(0, 1, 2, 3, 4, 5);
      List<Integer> OUT_OF_RANGE_NUMBERS_2 = List.of(1, 2, 3, 4, 5, 46);
      List<Integer> OUT_OF_RANGE_NUMBERS_3 = List.of(-1, 2, 3, 4, 5, 6);

      List<List<Integer>> inputs = List.of(OUT_OF_RANGE_NUMBERS_1, OUT_OF_RANGE_NUMBERS_2, OUT_OF_RANGE_NUMBERS_3);

      for (List<Integer> input : inputs) {
        assertThatThrownBy(() -> {
          Validator.lottoValidate(input);
        }).isInstanceOf(IllegalArgumentException.class);
      }
    }

    @Test
    void 숫자가_중복되는_경우_예외_발생() {
      List<Integer> DUPLICATED_NUMBERS_1 = List.of(0, 0, 2, 3, 4, 5);
      List<Integer> DUPLICATED_NUMBERS_2 = List.of(1, 2, 3, 3, 5, 6);
      List<Integer> DUPLICATED_NUMBERS_3 = List.of(1, 2, 3, 4, 5, 5);

      List<List<Integer>> inputs = List.of(DUPLICATED_NUMBERS_1, DUPLICATED_NUMBERS_2, DUPLICATED_NUMBERS_3);

      for (List<Integer> input : inputs) {
        assertThatThrownBy(() -> {
          Validator.lottoValidate(input);
        }).isInstanceOf(IllegalArgumentException.class);
      }
    }
  }

  @Nested
  class 보너스_번호_검증 {
    List<Integer> JACKPOT_LOTTO_NUMBERS = List.of(1, 2, 3, 4, 5, 6);

    @Test
    void 숫자_범위_밖인_경우_예외_발생() {
      List<Integer> inputs = List.of(0, 46, -1);

      for (int input : inputs) {
        assertThatThrownBy(() -> {
          Validator.bonusValidate(input, JACKPOT_LOTTO_NUMBERS);
        }).isInstanceOf(IllegalArgumentException.class);
      }
    }

    @Test
    void 당첨_번호와_중복되는_경우_예외_발생() {
      List<Integer> inputs = List.of(1, 2, 3, 4, 5, 6);

      for (int input : inputs) {
        assertThatThrownBy(() -> {
          Validator.bonusValidate(input, JACKPOT_LOTTO_NUMBERS);
        }).isInstanceOf(IllegalArgumentException.class);
      }
    }
  }
}
