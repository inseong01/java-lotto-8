package lotto.utility.message;

public enum CashMessage {
  CASH_HAS_CHANGE("[ERROR] %d원 단위로 입력해주세요."),
  CASH_IS_ZERO("[ERROR] %d원 이상으로 입력해주세요.");

  private final String template;

  CashMessage(String template) {
    this.template = template;
  }

  public String format() {
    int PRODUCE_PRICE = 1_000;
    return String.format(template, PRODUCE_PRICE);
  }
}