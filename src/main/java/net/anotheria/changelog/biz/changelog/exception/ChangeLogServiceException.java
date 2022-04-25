package net.anotheria.changelog.biz.changelog.exception;

public class ChangeLogServiceException extends Exception {

    /**
     * Constructor.
     *
     * @param message
     *         fail message
     */
    public ChangeLogServiceException(final String message) {
        super(message);
    }

    /**
     * Constructor.
     *
     * @param cause
     *         fail cause
     */
    public ChangeLogServiceException(final Throwable cause) {
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
    public ChangeLogServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
