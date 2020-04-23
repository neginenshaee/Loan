package exceptions

import java.util.function.Supplier

class LoanRequestNotFoundException extends Exception implements Supplier<LoanRequestNotFoundException> {
    private String id
    private String message

    public LoanRequestNotFoundException() {
        super()
    }

    public LoanRequestNotFoundException(String id, String message) {
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
    public LoanRequestNotFoundException get() {
        return this;
    }
}
