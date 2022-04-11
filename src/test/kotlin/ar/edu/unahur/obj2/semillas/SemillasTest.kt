package ar.edu.unahur.obj2.semillas

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.assertions.throwables.shouldThrowMessage
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
        val quinoa = Quinoa(0.2, 2010, 0.2)
        val quinoa2 = Quinoa(0.1, 2006, 0.9)
        val sojaTrans= SojaTransgenica (0.8, 2009 )
        val peperina = Peperina (1.0, 2021)

        it("probamos los atributos altura  y anioSemilla") {
            menta.altura.shouldBe(1.0)
            menta.anioSemilla.shouldBe(2021)
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
})

class ParcelaTesdt : DescribeSpec({
    describe("Creación Plantas y Parcelas") {
        val soja = Soja(0.6, 2009)
        val soja2 = Soja (1.2, 2010 )
        val menta = Menta(1.0, 2021)
        val quinoa = Quinoa(0.2, 2010, 0.2)
        val parcela1 = Parcela(20.0, 1.0, 10)

        parcela1.puedePlantar(soja)
        parcela1.puedePlantar(menta)
        parcela1.puedePlantar(quinoa)


        it("Debe emitir un error si se intenta superar el maximo de plantas"){
            shouldThrowAny{
                parcela1.puedePlantar(soja2)
            }
        }

        it ("Probamos maximo de plantas en la parcelas"){
            parcela1.cantMaxima().shouldBe(4)

        }
        it ("Probamos superficie de parcela"){
            parcela1.superficie().shouldBe(20)

        }
    }
})