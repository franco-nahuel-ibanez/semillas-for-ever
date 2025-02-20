package ar.edu.unahur.obj2.semillas

import kotlin.math.max

abstract open class Parcela (val ancho: Double, val largo: Double, val horasDeSol: Int ) {
    val plantas = mutableListOf<Planta>()

    fun superficie(): Double = ancho * largo

    fun cantMaxima(): Double = if (ancho>largo) superficie() / 5 else superficie() / 3 + largo

    fun tieneComplicaciones(): Boolean = plantas.any { p -> p.horasSolToleradas() < horasDeSol }

    fun hayLugar(): Boolean = plantas.size < cantMaxima()

    fun toleraSol(unaPlanta: Planta) = (horasDeSol - unaPlanta.horasSolToleradas()) < 2

    fun puedePlantar(unaPlanta: Planta) = if (hayLugar() or toleraSol(unaPlanta)) plantas.add(unaPlanta) else  throw java.lang.RuntimeException ("No puede ser plantada")

    abstract fun seAsociaBien(unaPlanta: Planta): Boolean


    fun porcentajeDeAsociadas (): Double {

        val plantasAsociadas = plantas.filter { p -> this.seAsociaBien(p) }
        return (plantasAsociadas.size / max( (plantas.size).toDouble(), 1.0)) * 100.0
    }


}

class ParcelaEcologica(ancho: Double, largo: Double, horasDeSol: Int): Parcela(ancho, largo, horasDeSol) {
    override fun seAsociaBien(unaPlanta: Planta) = !this.tieneComplicaciones() && unaPlanta.resultaIdeal(this)
}

class ParcelaIndustrial(ancho: Double, largo: Double, horasDeSol: Int): Parcela(ancho, largo, horasDeSol) {
    override fun seAsociaBien(unaPlanta: Planta) = this.plantas.size <= 2 && unaPlanta.esFuerte()
}






