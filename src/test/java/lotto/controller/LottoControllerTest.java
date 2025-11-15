package lotto.controller;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.model.BonusNumber;
import lotto.model.Lotto;
import lotto.model.LottoMachine;
import lotto.utility.config.LottoConfig;
import lotto.utility.message.BonusMessage;
import lotto.utility.message.CashMessage;
import lotto.utility.message.InputMessage;
import lotto.utility.message.LottoMessage;
import lotto.view.InputView;

import lotto.view.OutputView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class LottoControllerTest {
  @Mock
  MockedStatic<InputView> inputView = Mockito.mockStatic(InputView.class);
  MockedStatic<OutputView> outputView = Mockito.mockStatic(OutputView.class);

  @AfterEach
  void cleanup() {
    inputView.close();
    outputView.close();
  }

  @Nested
  class 구입_금액_입력 {

    @Test
    void 잘못된_입력을_한_경우_다시_입력() {
      String SET_CASH_PROMPT = "금액을 입력해주세요";
      Mockito.when(InputView.readLine(SET_CASH_PROMPT))
              .thenReturn(" ")
              .thenReturn("1000");

      LottoController lottoController = new LottoController();
      int result = lottoController.getUserCashInput();

      outputView.verify(() -> OutputView.print(InputMessage.INPUT_IS_EMPTY_STRING.getDescription()));
      assertThat(result).isEqualTo(1000);
    }
  }

  @Nested
  class 현금_생성 {

    @Test
    void 잘못된_금액인_경우_다시_입력() {
      String SET_CASH_PROMPT = "금액을 입력해주세요";
      String CORRECT_CASH_INPUT = "1000";
      int WRONG_CASH_INPUT = 1001;
      Mockito.when(InputView.readLine(SET_CASH_PROMPT))
              .thenReturn(CORRECT_CASH_INPUT);

      LottoController lottoController = new LottoController();
      lottoController.setCash(WRONG_CASH_INPUT);

      outputView.verify(() -> OutputView.print(CashMessage.CASH_HAS_CHANGE.format()));
      assertThat(lottoController.getUserCashInput()).isEqualTo(1000);
    }
  }

  @Nested
  class 사용자_로또_생성 {

    @Mock
    MockedStatic<LottoMachine> lottoMachine = Mockito.mockStatic(LottoMachine.class);

    @AfterEach
    void cleanup() {
      lottoMachine.close();
    }

    @Test
    void 금액_만큼_로또_생성() {
      int userCash = 2000;
      int tryCount = (userCash / LottoConfig.LOTTO_PRICE.getValue());
      List<Lotto> userLotto = List.of(
              new Lotto(List.of(1, 2, 3, 4, 5, 6)),
              new Lotto(List.of(11, 12, 13, 14, 15, 16))
      );
      Mockito.when(LottoMachine.generateLotto())
              .thenReturn(userLotto.get(0))
              .thenReturn(userLotto.get(1));

      LottoController lottoController = new LottoController();
      lottoController.setCash(userCash);
      lottoController.setUserLotto();

      lottoMachine.verify(LottoMachine::generateLotto, Mockito.times(tryCount));
    }
  }

  @Nested
  class 사용자_로또_출력 {

    @Mock
    MockedStatic<LottoMachine> lottoMachine = Mockito.mockStatic(LottoMachine.class);

    @AfterEach
    void cleanup() {
      lottoMachine.close();
    }

    @Test
    void 로또_수_만큼_출력() {
      int USER_CASH = 2000;
      List<Lotto> userLotto = List.of(
              new Lotto(List.of(1, 2, 3, 4, 5, 6)),
              new Lotto(List.of(11, 12, 13, 14, 15, 16))
      );
      String[] logs = {
              "2개를 구매했습니다.",
              "[1, 2, 3, 4, 5, 6]",
              "[11, 12, 13, 14, 15, 16]",
      };
      Mockito.when(LottoMachine.generateLotto())
              .thenReturn(userLotto.get(0))
              .thenReturn(userLotto.get(1));

      LottoController lottoController = new LottoController();
      lottoController.setCash(USER_CASH);
      lottoController.setUserLotto();
      lottoController.printUserLotto();

      for (String log : logs) {
        outputView.verify(() -> OutputView.print(log));
      }
    }
  }

  @Nested
  class 당첨_로또_입력 {

    @Test
    void 잘못된_번호인_경우_다시_입력() {
      String ENTER_LOTTO_NUMBERS = "당첨 번호를 입력해 주세요.";
      String userLottoInput = "1, 2, 3, 4, 5, 6";
      List<Integer> lottoNumbers = List.of(1, 2, 3, 4, 5, 6);
      Mockito.when(InputView.readLine(ENTER_LOTTO_NUMBERS))
              .thenReturn(" ")
              .thenReturn(userLottoInput);

      LottoController lottoController = new LottoController();
      List<Integer> numbers = lottoController.getLottoNumbers();

      outputView.verify(() -> OutputView.print(
              InputMessage.INPUT_IS_EMPTY_STRING.getDescription()
      ));
      assertThat(numbers).isEqualTo(lottoNumbers);
    }
  }

  @Nested
  class 당첨_로또_생성 {

    @Test
    void 잘못된_로또인_경우_다시_입력() {
      String ENTER_LOTTO_NUMBERS = "당첨 번호를 입력해 주세요.";
      List<Integer> WRONG_LOTTO_NUMBERS = List.of(1, 1, 1, 1, 1, 1);
      String userLottoInput = "1, 2, 3, 4, 5, 6";
      Mockito.when(InputView.readLine(ENTER_LOTTO_NUMBERS))
              .thenReturn(userLottoInput);

      LottoController lottoController = new LottoController();
      lottoController.setJackpotLotto(WRONG_LOTTO_NUMBERS);

      outputView.verify(() -> OutputView.print(
              LottoMessage.NUMBER_IS_DUPLICATED.getDescription()
      ));
    }
  }

  @Nested
  class 보너스_번호_입력 {

    @Test
    void 잘못된_번호인_경우_다시_입력() {
      String ENTER_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";
      String userBonusInput = "20";
      int bonusNumber = 20;
      Mockito.when(InputView.readLine(ENTER_BONUS_NUMBER))
              .thenReturn("  ")
              .thenReturn(userBonusInput);

      LottoController lottoController = new LottoController();
      int number = lottoController.getBonusNumber();

      outputView.verify(() -> OutputView.print(
              InputMessage.INPUT_IS_EMPTY_STRING.getDescription()
      ));
      assertThat(number).isEqualTo(bonusNumber);
    }
  }

  @Nested
  class 보너스_번호_생성 {

    @Test
    void 잘못된_보너스_숫자인_경우_다시_입력() {
      String ENTER_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";
      List<Integer> JACKPOT_NUMBERS = List.of(1, 2, 3, 4, 5, 6);
      int DUPLICATED_BONUS_NUMBER = 1;
      String userBonusInput = "20";
      Mockito.when(InputView.readLine(ENTER_BONUS_NUMBER))
              .thenReturn(userBonusInput);

      LottoController lottoController = new LottoController();
      lottoController.setJackpotLotto(JACKPOT_NUMBERS);
      lottoController.setBonusNumber(DUPLICATED_BONUS_NUMBER);

      outputView.verify(() -> OutputView.print(
              BonusMessage.NUMBER_IS_DUPLICATED.getDescription()
      ));
    }
  }

  @Nested
  class 결과_출력 {

    @Mock
    MockedStatic<LottoMachine> lottoMachine = Mockito.mockStatic(LottoMachine.class);

    @AfterEach
    void cleanup() {
      lottoMachine.close();
    }

    @Test
    void 사용자_로또_당첨_결과_출력() {
      int USER_CASH = 8_000;
      int BONUS_NUMBER = 7;
      List<Integer> JACKPOT_NUMBERS = List.of(1, 2, 3, 4, 5, 6);
      List<String> logs = List.of(
              "당첨 통계",
              "---",
              "3개 일치 (5,000원) - 1개",
              "4개 일치 (50,000원) - 0개",
              "5개 일치 (1,500,000원) - 0개",
              "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
              "6개 일치 (2,000,000,000원) - 0개",
              "총 수익률은 62.5%입니다."
      );
      List<Lotto> userLotto = List.of(
              new Lotto(List.of(1, 2, 3, 24, 25, 26)),
              new Lotto(List.of(11, 12, 13, 14, 15, 16))
      );
      Mockito.when(LottoMachine.generateLotto())
              .thenReturn(userLotto.get(0))
              .thenReturn(userLotto.get(1));
      Mockito.when(LottoMachine.generateJackpotLotto(JACKPOT_NUMBERS))
              .thenReturn(new Lotto(JACKPOT_NUMBERS));
      Mockito.when(LottoMachine.generateBonusNumber(BONUS_NUMBER, JACKPOT_NUMBERS))
              .thenReturn(new BonusNumber(BONUS_NUMBER, JACKPOT_NUMBERS));

      LottoController lottoController = new LottoController();
      lottoController.setCash(USER_CASH);
      lottoController.setUserLotto();
      lottoController.setJackpotLotto(JACKPOT_NUMBERS);
      lottoController.setBonusNumber(BONUS_NUMBER);
      lottoController.printLottoResult();

      for (String log : logs) {
        outputView.verify(() -> OutputView.print(log));
      }
    }
  }
}
