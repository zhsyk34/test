import lombok.Getter;

public enum ActionType {
    FP("fp", "指纹开锁"),
    PWD("pwd", "密码开锁"),
    KEY("key", "钥匙开锁"),
    FPERR("fpErr", "指纹错误"),
    PWDERR("pwdErr", "密码错误"),
    LOWBAT("lowBat", "低电量"),
    BROKEN("broken", "撬门"),
    NOCLOSE("noClose", "长时未关门"),
    HELP("help", "胁迫报警"),
    CLOSE("close", "闭锁");

    @Getter
    private String value;
    @Getter
    private String description;

    ActionType(String value, String description) {
        this.value = value;
        this.description = description;
    }
}
