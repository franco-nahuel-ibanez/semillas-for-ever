package ar.edu.unahur.obj2.semillas

import kotlin.math.ceil

abstract class Planta(var altura: Double, val anioSemilla: Int) {

    object Constates {
        val UMBRAL_HORAS_SOL = 9
    }

    open fun esFuerte(): Boolean = horasSolToleradas() > Planta.Constates.UMBRAL_HORAS_SOL

    open fun horasSolToleradas(): Int = 0

    open fun daSemillas(): Boolean = esFuerte()

    abstract fun resultaIdeal(unaParcela: Parcela): Boolean

}


open class Menta(altura: Double, anioSemilla: Int): Planta(altura, anioSemilla){

    override fun horasSolToleradas(): Int = 7

    open fun espacio()= altura + 1.0

    override fun daSemillas() =  super.daSemillas() or (altura > 0.4)

    override fun resultaIdeal(unaParcela: Parcela) = unaParcela.superficie() > 6

}

open class Soja(altura: Double, anioSemilla: Int): Planta (altura, anioSemilla){

    fun espacio(): Double = altura/2

    override fun daSemillas() = super.daSemillas() or (anioSemilla > 2007 && altura > 0.75 && altura < 0.9)

    override fun horasSolToleradas() = if (altura < 0.5) (6) else if (altura < 1) (8) else(12)

    override fun resultaIdeal(unaParcela: Parcela) = unaParcela.horasDeSol == horasSolToleradas()

}

class Quinoa (altura: Double, anioSemilla: Int, val espacio: Double): Planta (altura, anioSemilla) {

    override fun daSemillas() = super.daSemillas() or (anioSemilla > 2000 && anioSemilla < 2009)

    override fun horasSolToleradas() = if(espacio < 0.3) 10 else super.horasSolToleradas()

    override fun resultaIdeal(unaParcela: Parcela) = !unaParcela.plantas.any { p -> p.altura > 1.5 }
}

class SojaTransgenica (altura: Double, anioSemilla: Int): Soja (altura, anioSemilla){

    override fun daSemillas() = false

    override fun resultaIdeal(unaParcela: Parcela) = unaParcela.cantMaxima().toInt() == 1

}

class Peperina (altura: Double, anioSemilla: Int): Menta (altura, anioSemilla){

    override fun espacio()= super.espacio()*2
}




 
