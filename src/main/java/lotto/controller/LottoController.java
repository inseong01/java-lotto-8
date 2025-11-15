package lotto.controller;

import lotto.model.BonusNumber;
import lotto.model.Cash;
import lotto.model.Lotto;
import lotto.model.LottoMachine;
import lotto.utility.Calculator;
import lotto.utility.StringFormatter;
import lotto.utility.Validator;
import lotto.utility.config.LottoConfig;
import lotto.utility.config.LottoPrize;
import lotto.utility.config.LottoRate;
import lotto.view.InputView;
import lotto.view.OutputView;


import java.text.NumberFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LottoController {
  private Cash cash;
  private List<Lotto> userLotto;
  private Lotto jackpot;
  private BonusNumber bonus;

  public LottoController() {
  }

  //  흐름 작성
  public int getUserCashInput() {
    String SET_CASH_PROMPT = "금액을 입력해주세요";

    while (true) {
      String cashInput = InputView.readLine(SET_CASH_PROMPT);
      try {
        Validator.inputNumberValidate(cashInput);
        return Integer.parseInt(cashInput);
      } catch (Exception e) {
        OutputView.print(e.getMessage());
      }
    }
  }

  public void setCash(int userCash) {
    try {
      cash = new Cash(userCash);
    } catch (Exception e) {
      OutputView.print(e.getMessage());
      getUserCashInput();
    }
  }

  public void setUserLotto() {
    int PRODUCT_PRICE = LottoConfig.LOTTO_PRICE.getValue();
    int lottoAmount = cash.getAmountOf(PRODUCT_PRICE);
    userLotto = IntStream.range(0, lottoAmount)
            .mapToObj((i) -> LottoMachine.generateLotto())
            .toList();
  }

  public void printUserLotto() {
    int PRODUCT_PRICE = LottoConfig.LOTTO_PRICE.getValue();
    int lottoAmount = cash.getAmountOf(PRODUCT_PRICE);
    String message = String.format("%d개를 구매했습니다.", lottoAmount);
    OutputView.print(message);

    userLotto.forEach((lotto) -> {
      OutputView.print(lotto.getNumbers().toString());
    });
  }

  public List<Integer> getLottoNumbers() {
    String ENTER_LOTTO_NUMBERS = "당첨 번호를 입력해 주세요.";

    while (true) {
      String input = InputView.readLine(ENTER_LOTTO_NUMBERS);
      try {
        Validator.inputLottoValidate(input);
        return Stream.of(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
      } catch (Exception e) {
        OutputView.print(e.getMessage());
      }
    }
  }

  public void setJackpotLotto(List<Integer> numbers) {
    try {
      jackpot = LottoMachine.generateJackpotLotto(numbers);
    } catch (Exception e) {
      OutputView.print(e.getMessage());
      getLottoNumbers();
    }
  }

  public int getBonusNumber() {
    String ENTER_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";

    while (true) {
      String input = InputView.readLine(ENTER_BONUS_NUMBER);
      try {
        Validator.inputNumberValidate(input);
        return Integer.parseInt(input);
      } catch (Exception e) {
        OutputView.print(e.getMessage());
      }
    }
  }

  public void setBonusNumber(int number) {
    List<Integer> jackpotNumbers = jackpot.getNumbers();
    try {
      bonus = LottoMachine.generateBonusNumber(number, jackpotNumbers);
    } catch (Exception e) {
      OutputView.print(e.getMessage());
      getBonusNumber();
    }
  }

  public void printLottoResult() {
    OutputView.print("당첨 통계");
    OutputView.print("---");

    Map<String, Integer> resultMap = Calculator.getMathResult(userLotto, jackpot, bonus);
    resultMap.forEach((rate, count) -> {
      String prize = StringFormatter.numberToPrice(rate);
      String matchAmountPrompt = StringFormatter.rateToMatchCountPrompt(rate);
      String matchAmount = String.format(" - %d개", count);
      OutputView.print(matchAmountPrompt + prize + matchAmount);
    });

    double profit = Calculator.getProfit(cash.getCash(), resultMap);
    OutputView.print("총 수익률은 " + String.format("%.1f%%", profit) + "입니다.");
  }
}