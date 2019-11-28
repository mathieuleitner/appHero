package com.crea.mathieu.heroapp.model

class Hero {
    var id: Long = 0
    var name: String? = null
    var power: String? = null
    var image: String? = null

    constructor(id: Long, name: String, power: String, image: String) {
        this.id = id
        this.name = name
        this.power = power
        this.image = image
    }

    constructor() {}
}
