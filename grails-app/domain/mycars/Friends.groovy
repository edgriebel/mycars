package mycars

class Friends {

    User user
    static hasMany = [friendList: User]

    static constraints = {
    }
}
