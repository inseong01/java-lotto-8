package lotto.utility.message;

public enum LottoMessage {
  NUMBER_LENGTH_IS_WRONG("[ERROR] 로또 숫자는 6개이어야 합니다.\n"),
  NUMBER_IS_OUT_OF_RANGE("[ERROR] 로또 번호는 1~45 범위 내 숫자로 구성해야 합니다.\n"),
  NUMBER_IS_DUPLICATED("[ERROR] 숫자는 중복되어서는 안 됩니다.\n");

  private final String description;

  LottoMessage(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
