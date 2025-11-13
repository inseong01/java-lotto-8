package lotto.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

public class BonusNumberTest {
  List<Integer> JACKPOT_LOTTO_NUMBERS = List.of(1, 2, 3, 4, 5, 6);

  @Nested
  class 인스턴스_생성 {

    @Test
    void 잘못된_입력을_한_경우_예외를_발생한다() {
      int OUT_OF_RANGE_1 = 0;
      int OUT_OF_RANGE_2 = 46;
      int DUPLICATED_NUMBER = 1;

      List<Integer> inputs = List.of(OUT_OF_RANGE_1, OUT_OF_RANGE_2, DUPLICATED_NUMBER);

      for (int input : inputs) {
        assertThatThrownBy(() -> {
          new BonusNumber(input, JACKPOT_LOTTO_NUMBERS);
        }).isInstanceOf(IllegalArgumentException.class);
      }
    }
  }

  @Test
  void 메서드를_통해서_보너스_번호를_반환한다() {
    int input = 31;

    BonusNumber bonusNumber = new BonusNumber(input, JACKPOT_LOTTO_NUMBERS);

    assertThat(bonusNumber.getNumber()).isEqualTo(input);
  }

  @Test
  void 메서드를_통해서_보너스_일치_여부를_반환한다() {
    List<Integer> MY_LOTTO_NUMBERS = List.of(1, 22, 3, 4, 5, 6);
    int[] bonusNumbers = {11, 22, 33};
    boolean[] outputs = {false, true, false};

    for (int i = 0; i < outputs.length; i++) {
      BonusNumber bonusNumber = new BonusNumber(bonusNumbers[i], JACKPOT_LOTTO_NUMBERS);

      assertThat(bonusNumber.isMatchWith(MY_LOTTO_NUMBERS)).isEqualTo(outputs[i]);
    }
  }
}
