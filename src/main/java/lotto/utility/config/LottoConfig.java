package lotto.utility.config;

public enum LottoConfig {
  LOTTO_PRICE(1_000),
  NUMBER_START_RANGE(1),
  NUMBER_END_RANGE(45),
  NUMBER_AMOUNT(6);

  private final int value;

  LottoConfig(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
