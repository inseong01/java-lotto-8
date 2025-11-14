package lotto.utility.config;

public enum LottoRate {
  MATCH_6("FIRST"),
  MATCH_5_BONUS("SECOND"),
  MATCH_5("THIRD"),
  MATCH_4("FORTH"),
  MATCH_3("FIFTH"),
  MATCH_0("");

  private final String rate;

  LottoRate(String rate) {
    this.rate = rate;
  }

  public String getRate() {
    return rate;
  }
}
