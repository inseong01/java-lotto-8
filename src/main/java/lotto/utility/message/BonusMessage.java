package lotto.utility.message;

public enum BonusMessage {
  NUMBER_IS_OUT_OF_RANGE("[ERROR] 로또 번호는 1~45 범위 내 숫자로 구성해야 합니다.\n"),
  NUMBER_IS_DUPLICATED("[ERROR] 로또 번호와 보너스 번호는 중복되어서는 안 됩니다.\n");

  private final String description;

  BonusMessage(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
