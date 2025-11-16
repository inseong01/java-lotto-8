package lotto;

import lotto.controller.LottoController;

import java.util.List;

public class Application {
  public static void main(String[] args) {
    LottoController lottoController = new LottoController();

    int cash = lottoController.getUserCashInput();
    lottoController.setCash(cash);

    lottoController.setUserLotto();

    lottoController.printUserLotto();

    List<Integer> numbers = lottoController.getLottoNumbers();
    lottoController.setJackpotLotto(numbers);

    int number = lottoController.getBonusNumber();
    lottoController.setBonusNumber(number);

    lottoController.printLottoResult();
  }
}
