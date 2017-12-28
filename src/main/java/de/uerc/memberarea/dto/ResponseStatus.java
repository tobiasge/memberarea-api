package de.uerc.memberarea.dto;

public class ResponseStatus {

    private final Object data;

    private final String statusCode;

    private final String statusMessage;

    private final boolean success;

    public ResponseStatus(final boolean success) {
        this(success, null, null, null);
    }

    public ResponseStatus(final boolean success, final Object data) {
        this(success, data, null, null);
    }

    public ResponseStatus(final boolean success, final Object data, final String statusMessage,
        final String statusCode) {
        super();
        this.success = success;
        this.data = data;
        this.statusMessage = statusMessage;
        this.statusCode = statusCode;
    }

    public Object getData() {
        return data;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public boolean isSuccess() {
        return success;
    }
}
