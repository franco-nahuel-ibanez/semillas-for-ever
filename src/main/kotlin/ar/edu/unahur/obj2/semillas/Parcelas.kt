package ar.edu.unahur.obj2.semillas

class Parcela (val ancho: Double, val largo: Double, val horasDeSol: Int ) {

    val plantas = mutableListOf<Planta>()

    fun superficie(): Double = ancho * largo

    fun cantMaxima(): Double = if (ancho>largo) superficie() / 5 else superficie() / 3 + largo

    fun tieneComplicaciones(): Boolean = plantas.any { p -> p.horasSolToleradas() < horasDeSol }

    fun hayLugar(): Boolean = plantas.size < cantMaxima()

    fun toleraSol(unaPlanta: Planta) = (horasDeSol - unaPlanta.horasSolToleradas()) < 2

    fun puedePlantar(unaPlanta: Planta) = if (hayLugar() && toleraSol(unaPlanta)) plantas.add(unaPlanta) else error("No puede ser plantada")

}