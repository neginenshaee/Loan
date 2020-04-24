import java.sql.Timestamp

class VerificationTokenExpiration {

    static Date getExpiry(int expiryTimeInMinutes) {
        Calendar calendar = Calendar.getInstance()
        calendar.setTime(new Timestamp(calendar.getTime().getTime()))
        calendar.add(Calendar.MINUTE, expiryTimeInMinutes)
        new Date(calendar.getTime().getTime())
    }
}

