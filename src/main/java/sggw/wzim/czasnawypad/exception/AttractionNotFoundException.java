package sggw.wzim.czasnawypad.exception;

public class AttractionNotFoundException extends RuntimeException {

    private static final String MESSAGE_FORMAT = "Attraction of id %s not found";

    public AttractionNotFoundException(Integer attractionId) {
        super(String.format(MESSAGE_FORMAT, attractionId));
    }
}
