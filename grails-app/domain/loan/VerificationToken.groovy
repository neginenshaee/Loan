package loan

import auth.User

import java.sql.Timestamp

class VerificationToken {

    static final int EXPIRATION = 60 * 24;
    Calendar calendar = Calendar.getInstance()
    String token
    static belongsTo = [user: User]
    Date dateCreated = new Date(calendar.getTime().getTime())
    Date expirationDate = getExpiry(EXPIRATION)
    Date lastUpdated



     Date getExpiry(int expiryTimeInMinutes) {
        Calendar calendar = Calendar.getInstance()
        calendar.setTime(new Timestamp(calendar.getTime().getTime()))
        calendar.add(Calendar.MINUTE, expiryTimeInMinutes)
        new Date(calendar.getTime().getTime())
    }

    static transients = ['expiry','calendar']

    static constraints = {

    }
}
