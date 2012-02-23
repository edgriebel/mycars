package mycars

class Model {

    String model
    static belongsTo = [make: Make]

    static constraints = {
        model(blank: false)
    }
}
