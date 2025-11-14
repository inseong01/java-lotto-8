package lotto.utility.config;

public enum LottoConfig {
  LOTTO_PRICE(1_000);

  private final int value;

  LottoConfig(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
