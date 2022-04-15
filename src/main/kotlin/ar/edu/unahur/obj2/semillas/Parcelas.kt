package ar.edu.unahur.obj2.semillas

abstract open class Parcela (val ancho: Double, val largo: Double, val horasDeSol: Int ) {
    val plantas = mutableListOf<Planta>()

    fun superficie(): Double = ancho * largo

    fun cantMaxima(): Double = if (ancho>largo) superficie() / 5 else superficie() / 3 + largo

    fun tieneComplicaciones(): Boolean = plantas.any { p -> p.horasSolToleradas() < horasDeSol }

    fun hayLugar(): Boolean = plantas.size < cantMaxima()

    fun toleraSol(unaPlanta: Planta) = (horasDeSol - unaPlanta.horasSolToleradas()) < 2

    fun puedePlantar(unaPlanta: Planta) = if (hayLugar() or toleraSol(unaPlanta)) plantas.add(unaPlanta) else throw Exception ("No puede ser plantada")

    fun cantPlantas(): Int = plantas.size

    abstract open fun seAsociaBien(unaPlanta: Planta): Boolean
}

class ParcelaEcologica(ancho: Double, largo: Double, horasDeSol: Int): Parcela(ancho, largo, horasDeSol) {
    override fun seAsociaBien(unaPlanta: Planta) = !this.tieneComplicaciones() && unaPlanta.resultaIdeal(this)
}

class ParcelaIndustrial(ancho: Double, largo: Double, horasDeSol: Int): Parcela(ancho, largo, horasDeSol) {
    override fun seAsociaBien(unaPlanta: Planta) = this.plantas.size <= 2 && unaPlanta.esFuerte()
}






