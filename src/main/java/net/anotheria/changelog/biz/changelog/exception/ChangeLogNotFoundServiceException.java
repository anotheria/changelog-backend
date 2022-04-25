package net.anotheria.changelog.biz.changelog.exception;

public class ChangeLogNotFoundServiceException extends ChangeLogServiceException {

    /**
     * Constructor.
     *
     * @param message
     *         fail message
     */
    public ChangeLogNotFoundServiceException(final String message) {
        super(message);
    }

    /**
     * Constructor.
     *
     * @param cause
     *         fail cause
     */
    public ChangeLogNotFoundServiceException(final Throwable cause) {
        super(cause);
    }

    /**
     * Constructor.
     *
     * @param message
     *         fail message
     * @param cause
     *         fail cause
     */
    public ChangeLogNotFoundServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
