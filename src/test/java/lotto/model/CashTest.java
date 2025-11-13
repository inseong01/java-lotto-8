package lotto.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CashTest {
  @Nested
  class 인스턴스_생성 {

    @Test
    void 잘못된_입력을_한_경우_예외를_발생한다() {
      int REST_PRICE = 1002;
      int ZERO_PRICE = 0;

      List<Integer> inputs = List.of(REST_PRICE, ZERO_PRICE);

      for (int input : inputs) {
        assertThatThrownBy(() -> {
          new Cash(input);
        }).isInstanceOf(IllegalArgumentException.class);
      }
    }
  }

  @Test
  void 메서드를_통해서_금액을_반환한다() {
    List<Integer> inputs = List.of(1_000, 2_000, 3_000);

    for (int input : inputs) {
      Cash cash = new Cash(input);

      assertThat(cash.getCash()).isEqualTo(input);
    }
  }

  @Test
  void 메서드를_통해서_구입_상품_갯수를_반환한다() {
    int PRODUCT_PRICE = 1_000;
    List<Integer> inputs = List.of(1_000, 2_000, 3_000);
    List<Integer> outputs = List.of(1, 2, 3);

    for (int i = 0; i < inputs.size(); i++) {
      Cash cash = new Cash(inputs.get(i));

      assertThat(cash.getAmountOf(PRODUCT_PRICE)).isEqualTo(outputs.get(i));
    }
  }
}
