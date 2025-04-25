package com.uax.androidmaster

import kotlinx.coroutines.processNextEventInCurrentThread

fun main(){
    println("Hola")
    var nombre = "Guillermo"
    val edad:Int = 30
    println(nombre + " " + edad)

    nombre = "Sara"
    println(nombre + " y tengo " + edad)

    var numero1 = 1
    var numero2 = 5

    var final = sumar(numero1, numero2)
    println(final)

    println(sumarSinReturn(final, numero2))

    usoWhen(17)
    usoWhen2(numero1)
    usoWhen3("Siempre")

    usoNull()

    arraysKotlin()
    listasKotlin()
}

fun sumar(num1:Int, num2:Int):Int{
    return num1 + num2
}

fun sumarSinReturn(num1: Int, num2: Int){
    println(num2 + num1)
}

fun usoWhen(mes:Int){
    when(mes){
        1 -> println("Enero")
        2 -> println("Febrero")
        3 -> println("Marzo")
        4 -> println("Abril")
        5 -> println("Mayo")
        7 -> println("Junio")
        8 -> println("Enero")
        9 -> println("Enero")
        10 -> println("Enero")
        11 -> println("Enero")
        12 -> println("Enero")
        else -> println("No existe")
    }
}

fun usoWhen2(numero:Int){
    when(numero){
        in 1 .. 6 -> println("Primer Semestre")
        in 7..12 -> println("Segundo Semestre")
        else -> println("Ningun semestre encontrado")
        //!in 1..12 Para indicar que si no esta entre uno y doce
    }
}

fun usoWhen3(value: Any){
    when(value){
        is String -> println("Soy un string")
        is Boolean -> if(value) println("true") else ("false")
    }
}

fun usoNull(){
    var nombre:String? = "Dale caña"
    println(nombre?.get(2) ?: "Es nulo")
}

fun arraysKotlin(){
    val weekDays = arrayOf("Lunes", "Martes", "Miercoles", "Jueves", "Viernes")
    println(weekDays.get(2) + " " + weekDays[1])

    for (dias in weekDays.indices){
        println(dias)
        println("El mejor dia es el ${weekDays.get(dias)}")
    }

    for ((pos, valor) in weekDays.withIndex()){
        println("La posicion $pos contiene $valor")
    }
}

fun listasKotlin() {
    val soloLectura: List<String> = listOf("Lunes", "Martes", "Miercoles", "Jueves", "Viernes")

    for (dia in soloLectura){
        println(dia)
    }

    val filtrado = soloLectura.filter { it.contains("a") }

    soloLectura.forEach { dia -> println(dia) }

    var mutableList: MutableList<String> = mutableListOf("Lunes", "Martes", "Miercoles", "Jueves", "Viernes")
    println(mutableList)
    mutableList.add("Sabado")
    println(mutableList)
    mutableList.add(0, "Lunes")
    println(mutableList)

}