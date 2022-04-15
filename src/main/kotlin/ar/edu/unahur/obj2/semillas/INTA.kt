package ar.edu.unahur.obj2.semillas

object INTA {

    val coleccParcela = mutableListOf<Parcela>()

    fun agregarParcela(unaParcela: Parcela)  = coleccParcela.add(unaParcela)

    fun promedioPlantas() : Int = totalPlantas() / coleccParcela.size

    fun totalPlantas() : Int {

        val planta = coleccParcela.map { p -> p.plantas.size }
        return planta.sum()

    }

    fun parcelaMasSustentable()
}