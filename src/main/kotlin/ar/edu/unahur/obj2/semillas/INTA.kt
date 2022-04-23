package ar.edu.unahur.obj2.semillas

import kotlin.math.max

object INTA {
    val parcelas = mutableListOf<Parcela>()

    fun agregarParcela(unaParcela: Parcela)  = parcelas.add(unaParcela)

    fun promedioPlantas(): Double {
        val cantidadPlantas = parcelas.sumBy { p -> p.plantas.size }
        return cantidadPlantas / max((parcelas.size).toDouble(), 1.0)
    }


    fun masSustentable(): Parcela{
        val parcelasConMasDe4Plantas = parcelas.filter { it.plantas.size > 4}
        val parcela = parcelasConMasDe4Plantas.maxByOrNull { it.porcentajeDeAsociadas() }
        if( parcela != null) {
            return parcela
        }
        throw java.lang.RuntimeException("No hay parcelas que satisfagan el criterio.")
    }
}


