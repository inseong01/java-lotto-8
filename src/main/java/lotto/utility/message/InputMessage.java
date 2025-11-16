package lotto.utility.message;

public enum InputMessage {
  INPUT_IS_EMPTY_STRING("[ERROR] 다시 입력해주세요."),
  INPUT_IS_NOT_A_NUMBER("[ERROR] 양의 정수로 입력해주세요."),
  INPUT_NOT_SUPPORTED_FORMAT("[ERROR] 6개 숫자를 , 구분자로 나눠서 입력해주세요.");

  private final String template;

  InputMessage(String template) {
    this.template = template;
  }

  public String getDescription() {
    return template;
  }
}