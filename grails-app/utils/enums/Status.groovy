package enums

enum Status {
    REQUESTED('R'),
    CONFIRMED('F'),
    APPROVED('A'),
    CANCELLED('C'),
    REJECTED('J')

    private Status(String id) { this.id = id }
    final String id

    static Status byId(String id) {
        values().find { it.id == id }
    }
}
