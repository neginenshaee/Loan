package exceptions

import java.util.function.Supplier

class UserNotFoundException extends Exception implements Supplier<UserNotFoundException> {
    private String id
    private String message

    public UserNotFoundException() {
        super()
    }

    public UserNotFoundException(String id, String message) {
        this.id = id
        this.message = message
    }

    public String getId() {
        return id
    }

    public void setId(String id) {
        this.id = id
    }

    @Override
    public String getMessage() {
        return message
    }

    public void setMessage(String message) {
        this.message = message
    }

    @Override
    public UserNotFoundException get() {
        return this;
    }
}
