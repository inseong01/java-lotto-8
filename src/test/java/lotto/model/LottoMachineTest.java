package lotto.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoMachineTest {
  @Test
  void 메서드를_통해_사용자_로또를_발행한다() {
    int LOTTO_NUMBER_LENGTH = 6;

    Lotto lotto = LottoMachine.generateLotto();

    assertThat(lotto).isInstanceOf(Lotto.class);
    assertThat(lotto.getNumbers().size()).isEqualTo(LOTTO_NUMBER_LENGTH);
  }

  @Test
  void 메서드를_통해서_당첨_로또를_발행한다() {
    int LOTTO_NUMBER_LENGTH = 6;
    List<Integer> JACKPOT_LOTTO_NUMBERS = List.of(1, 2, 3, 4, 5, 6);

    Lotto lotto = LottoMachine.generateJackpotLotto(JACKPOT_LOTTO_NUMBERS);

    assertThat(lotto).isInstanceOf(Lotto.class);
    assertThat(lotto.getNumbers().size()).isEqualTo(LOTTO_NUMBER_LENGTH);
  }

  @Test
  void 메서드를_통해서_보너스_번호를_발행한다() {
    List<Integer> JACKPOT_LOTTO_NUMBERS = List.of(1, 2, 3, 4, 5, 6);
    int input = 11;

    BonusNumber bonusNumber = LottoMachine.generateBonusNumber(input, JACKPOT_LOTTO_NUMBERS);


    assertThat(bonusNumber).isInstanceOf(BonusNumber.class);
    assertThat(bonusNumber.getNumber()).isEqualTo(input);
  }
}
