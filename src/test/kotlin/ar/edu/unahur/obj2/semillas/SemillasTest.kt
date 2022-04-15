package ar.edu.unahur.obj2.semillas

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class SemillasTest : DescribeSpec({
    // hay una clase Planta que tiene por atributos
    // anioSemilla, altura
    describe("Creación de las plantas") {
        val menta = Menta(1.0, 2021)
        val mentita = Menta(0.3, 2021)
        val soja = Soja(0.6, 2009)
        val soja3 = Soja(1.2, 2009)
        val quinoa = Quinoa(0.2, 2010, 0.2)
        val quinoa2 = Quinoa(0.1, 2006, 0.9)
        val sojaTrans= SojaTransgenica (0.8, 2009 )
        val peperina = Peperina (1.0, 2021)

        it("probamos los atributos altura  y anioSemilla") {
            menta.altura.shouldBe(1.0)
            menta.anioSemilla.shouldBe(2021)
        }

        it ("Probamos tolerancia al sol"){
             soja.horasSolToleradas()shouldBe(8)
             soja3.horasSolToleradas()shouldBe(12)
        }

        it("probamos si una planta es fuerte"){
            menta.esFuerte().shouldBeFalse()
            quinoa.esFuerte().shouldBeTrue()
        }


        it("verificar si da semillas") {
            menta.daSemillas().shouldBeTrue()
            mentita.daSemillas().shouldBeFalse()
            soja.daSemillas().shouldBeFalse()
            quinoa.daSemillas().shouldBeTrue()
            quinoa2.daSemillas().shouldBeTrue()
            sojaTrans.daSemillas().shouldBeFalse()
        }

        it("es fuerte") {
            menta.esFuerte().shouldBeFalse()
            soja.esFuerte().shouldBeFalse()
        }

        it("espacio") {
            menta.espacio().shouldBe(2.0)
            mentita.espacio().shouldBe(1.3)
            soja.espacio().shouldBe(0.3)
            peperina.espacio().shouldBe(4.0)
        }

        it("verifico la suma de varias") {
            val superficie = mutableListOf(
                soja.espacio(),
                menta.espacio(),
                mentita.espacio()
            ).sum()
            Math.ceil(superficie).shouldBe(4)

        }

        it("error"){
            val exception = shouldThrow<IllegalAccessException> {
                error("algo salio mal")
            }
            exception.message.shouldBe("algo salio mal")
        }
    }

    describe("Probando parcelas ideales") {
        val menta = Menta(1.0, 2021)
        val soja = Soja(1.3, 2009)
        val quinoa = Quinoa(0.2, 2010, 0.2)
        val sojaTrans= SojaTransgenica (0.8, 2009 )
        val parcela1 = ParcelaEcologica(20.0, 1.0, 12)
        val parcela3 = ParcelaIndustrial(5.0, 1.0, 10)
        val parcela2 = ParcelaEcologica(6.0, 7.0, 10)
        val soja2 = Soja(1.8, 2009)

        parcela1.puedePlantar(menta)
        parcela1.puedePlantar(soja)
        parcela1.puedePlantar(quinoa)
        parcela1.puedePlantar(sojaTrans)

        parcela2.puedePlantar(menta)
        parcela2.puedePlantar(soja)
        parcela2.puedePlantar(quinoa)
        parcela2.puedePlantar(sojaTrans)
        parcela2.puedePlantar(soja2)


        it ("Probar menta") {
            menta.resultaIdeal(parcela1).shouldBeTrue()
            menta.resultaIdeal(parcela3).shouldBeFalse()

        }
        it ("Probar soja") {
            soja.resultaIdeal(parcela1).shouldBeTrue()
            soja.resultaIdeal(parcela2).shouldBeFalse()

        }
        it ("Probar quinoa") {
            quinoa.resultaIdeal(parcela1).shouldBeTrue()
            quinoa.resultaIdeal(parcela2).shouldBeFalse()
        }
        it ("Probar sojaTrans") {
            sojaTrans.resultaIdeal(parcela1).shouldBeFalse()
            sojaTrans.resultaIdeal(parcela3).shouldBeTrue()
        }
    }

    describe("Probando si una planta se asocia bien a una parcela"){
        val menta = Menta(1.0, 2021)

        val soja = Soja(0.3, 2009)  // 6 horas
        val quinoa = Quinoa(0.2, 2010, 0.2) //sol:
        val sojaTrans= SojaTransgenica (0.8, 2009 )
        val parcelaEcologica = ParcelaEcologica(20.0, 1.0, 6)
        val parcelaIndustrial = ParcelaIndustrial(6.0, 7.0, 10)
        val soja2 = Soja(1.8, 2009)

        parcelaEcologica.puedePlantar(menta)
        parcelaEcologica.puedePlantar(soja)
        parcelaEcologica.puedePlantar(quinoa)

        parcelaIndustrial.puedePlantar(sojaTrans)


        it("probando parcelas ecologicas"){
            parcelaEcologica.seAsociaBien(menta).shouldBeTrue()
            parcelaEcologica.seAsociaBien(soja).shouldBeTrue()
            parcelaEcologica.seAsociaBien(quinoa).shouldBeTrue()
        }

        it("la quinoa no debe asociarse bien si hay una planta de altura mayor a 1.5"){
            parcelaEcologica.puedePlantar(soja2)
            parcelaEcologica.seAsociaBien(quinoa).shouldBeFalse()
        }

        it("probando parcelas industriales"){
            parcelaIndustrial.seAsociaBien(menta).shouldBeFalse()
            parcelaIndustrial.seAsociaBien(quinoa).shouldBeTrue()
        }

        it("si hay mas de 2 plantas, la quiona no se asocia bien "){
            parcelaIndustrial.puedePlantar(quinoa)
            parcelaIndustrial.puedePlantar(soja)

            parcelaIndustrial.seAsociaBien(quinoa).shouldBeFalse()
        }
    }
})

class ParcelaTest : DescribeSpec({
    describe("Creación Plantas y Parcelas") {
        val soja = Soja(0.6, 2009)

        val soja2 = Soja (1.2, 2010 )
        val menta = Menta(1.0, 2021)
        val quinoa = Quinoa(0.2, 2010, 0.2)
        val parcela1 = ParcelaIndustrial(20.0, 1.0, 10)

        parcela1.puedePlantar(soja)
        parcela1.puedePlantar(menta)
        parcela1.puedePlantar(quinoa)


        //it("Debe emitir un error si se intenta superar el maximo de plantas"){
        //    shouldThrowAny{
        //        parcela1.puedePlantar(soja2)
        //    }
        //}

        it ("Probamos maximo de plantas en la parcelas"){
            parcela1.cantMaxima().shouldBe(4)

        }
        it ("Probamos superficie de parcela"){
            parcela1.superficie().shouldBe(20)

        }
    }
})

class IntaTest : DescribeSpec({
    describe("INTA") {
        val soja = Soja(0.6, 2009)
        val menta = Menta(1.0, 2021)
        val quinoa = Quinoa(0.2, 2010, 0.2)
        val parcela1 = ParcelaIndustrial(20.0, 1.0, 10)
        val parcela2 = ParcelaIndustrial(20.0, 1.0, 10)
        val parcela3 = ParcelaIndustrial(20.0, 1.0, 10)
        val inta = INTA

        parcela1.puedePlantar(soja)
        parcela1.puedePlantar(menta)
        parcela1.puedePlantar(quinoa)

        parcela2.puedePlantar(soja)
        parcela2.puedePlantar(menta)
        parcela2.puedePlantar(quinoa)

        inta.agregarParcela(parcela1)
        inta.agregarParcela(parcela2)


        it ("Prueba Plantas Promedio"){
            inta.promedioPlantas()shouldBe(3)


        }
    }
})

