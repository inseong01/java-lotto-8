package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
  public static String readLine(String prompt) {
    OutputView.print(prompt);
    return Console.readLine();
  }
}
