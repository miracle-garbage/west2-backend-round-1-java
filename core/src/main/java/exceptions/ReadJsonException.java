package exceptions;

public class ReadJsonException extends RuntimeException {
    private final String info;

    public ReadJsonException(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
