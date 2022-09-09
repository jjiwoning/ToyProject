package jiwon.board.exception;

public class LoginFailException extends RuntimeException{
    public LoginFailException() {
        super();
    }

    public LoginFailException(String message) {
        super(message);
    }
}
