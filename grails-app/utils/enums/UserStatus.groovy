package enums

enum UserStatus {
    CREATED('C'),
    CONFIRMED('F'),
    BANNED('B'),
    REMOVED('R')

    private UserStatus(String id) { this.id = id }
    final String id

    static UserStatus byId(String id) {
        values().find { it.id == id }
    }
}
