package juniorMatador;

public abstract class ChanceBase {

    private String message;
    private String type;

    ChanceBase(String message, String type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }
}
