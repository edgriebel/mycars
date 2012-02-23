package mycars

class Make {

    String make
    static hasMany = [models: Model]
    static constraints = {
        make(blank: false)
        
    }
}
