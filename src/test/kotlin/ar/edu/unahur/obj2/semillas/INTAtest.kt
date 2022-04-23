package ar.edu.unahur.obj2.semillas

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.doubles.shouldBeBetween
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf

class IntaTest : DescribeSpec({
    describe("INTA") {
        val soja = Soja(0.6, 2009) //
        val menta = Menta(1.0, 2021)
        val quinoa = Quinoa(0.2, 2010, 0.2)
        val sojaTrans= SojaTransgenica (0.8, 2009 )
        val parcela1 = ParcelaIndustrial(20.0, 1.0, 10)
        val parcela2 = ParcelaEcologica(20.0, 1.0, 10)
        val parcela3 = ParcelaEcologica(20.0, 1.0, 10)
        val inta = INTA


        it ("Una si no hay ninguna parcela deberia retornar 0"){
            inta.promedioPlantas()shouldBe(0.0)
        }

        it("Si hay solo una parcela debe retornar la cantidad de plantas que esta tenga"){
            parcela1.puedePlantar(menta);
            parcela1.puedePlantar(quinoa);

            inta.agregarParcela(parcela1);
            inta.promedioPlantas().shouldBe(2.0)
        }

        it("Si hay 3 parcelas con 3, 2 y 1 planta respectivamente, deberia retornar 2"){
            inta.parcelas.clear()
            parcela1.plantas.clear()

            parcela1.puedePlantar(menta)
            parcela1.puedePlantar(quinoa)
            parcela1.puedePlantar(soja)

            parcela2.puedePlantar(menta)
            parcela2.puedePlantar(quinoa)

            parcela3.puedePlantar(menta)

            inta.agregarParcela(parcela1)
            inta.agregarParcela(parcela2)
            inta.agregarParcela(parcela3)

            inta.promedioPlantas().shouldBe(2.0)
        }
    }
    describe("Probamos la mas autosustentable"){

        val menta = Menta(0.9, 2019) // es ideal con todo
        val peperina = Peperina (1.0, 2017) // es ideal con todo
        val quinoa = Quinoa(0.5, 2011, 0.3)
        val sojaTrans = SojaTransgenica (1.1, 2009 )
        val parcela1 = ParcelaIndustrial(20.0, 1.0, 10)
        val inta = INTA


        it ("Probamos obtener parcela mas autosustentable, deber dar error porque hay menos de 4 plantas ") {

            parcela1.puedePlantar(menta)
            parcela1.puedePlantar(quinoa)

            inta.agregarParcela(parcela1)
            shouldThrowAny{ inta.masSustentable()}


        }

        it ("Creamos parcelas con porcentajes de asociadas de 50, 60 y 83 % para probar porcentaje de asociadas") {

            val parcela2 = ParcelaEcologica(20.0, 1.0, 5)
            val parcela3 = ParcelaEcologica(17.0, 1.0, 7)
            val parcela4 = ParcelaEcologica(15.0, 1.0, 4)
            val menta2 = Menta(0.9, 2019) // es ideal con todo
            val peperina2 = Peperina(1.0, 2017) // es ideal con todo

            // 83%
            parcela2.puedePlantar(menta2)
            parcela2.puedePlantar(menta2)
            parcela2.puedePlantar(peperina2)
            parcela2.puedePlantar(peperina2)
            parcela2.puedePlantar(peperina2)
            parcela2.puedePlantar(sojaTrans)

            // 60%
            parcela3.puedePlantar(menta2)
            parcela3.puedePlantar(menta2)
            parcela3.puedePlantar(peperina2)
            parcela3.puedePlantar(sojaTrans)
            parcela3.puedePlantar(sojaTrans)

            // 50%
            parcela4.puedePlantar(menta2)
            parcela4.puedePlantar(menta2)
            parcela4.puedePlantar(peperina2)
            parcela4.puedePlantar(sojaTrans)
            parcela4.puedePlantar(sojaTrans)
            parcela4.puedePlantar(sojaTrans)


            inta.agregarParcela(parcela2)
            inta.agregarParcela(parcela3)
            inta.agregarParcela(parcela4)

            val resultado = inta.masSustentable()
            resultado.porcentajeDeAsociadas().shouldBeBetween (83.0,84.0,0.1)
        }
    }
})
