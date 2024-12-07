package sggw.wzim.czasnawypad.exception;

public class ApplicationExceptions {

    public static class AttractionNotFoundException extends RuntimeException {
        public AttractionNotFoundException(String message) {
            super(message);
        }
    }

    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }

    public static class RatingNotFoundException extends RuntimeException {
        public RatingNotFoundException(String message) {
            super(message);
        }
    }

    public static class NotYourRatingException extends RuntimeException {
        public NotYourRatingException(String message) {
            super(message);
        }
    }
}
