package mycars

class User {

    String firstName
    String lastName
    String userid

    static constraints = {
        firstName(blank: false)
        lastName(blank: false)
        userid(blank: false)
    }
}
