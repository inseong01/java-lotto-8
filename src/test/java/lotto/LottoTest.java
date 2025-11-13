package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
  @Test
  void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
      assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
              .isInstanceOf(IllegalArgumentException.class);
  }

  @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
  @Test
  void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
      assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
              .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void 메서드를_통해서_로또_번호를_반환한다() {
    Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

    List<Integer> numbers = lotto.getNumbers();

    assertThat(numbers).containsExactly(1, 2, 3, 4, 5, 6);
  }

  @Test
  void 메서드를_통해서_로또_번호_일치_개수를_반환한다() {
    List<Integer> JACKPOT_LOTTO_NUMBERS = List.of(1, 2, 3, 4, 5, 6);
    List<Integer> MY_LOTTO_NUMBERS = List.of(1, 12, 33, 41, 5, 6);

    Lotto lotto = new Lotto(MY_LOTTO_NUMBERS);

    assertThat(lotto.getMatchCountOf(JACKPOT_LOTTO_NUMBERS)).isEqualTo(3);
  }
}
