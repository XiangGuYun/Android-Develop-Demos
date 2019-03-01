package com.androidui

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val a = 3.0
        val b = 4.0
        val c = 5.0

        val cosA = (c*c+b*b-a*a)/(2*b*c)
        val cosB = (a*a+c*c-b*b)/(2*a*c)
        val cosC = (a*a+b*b-c*c)/(2*a*b)
        println("cosA is $cosA, cosB is $cosB, cosC os $cosC")
        println("arccosA is ${Math.acos(cosA)}, arccosB is ${ Math.acos(cosB)}, arccosC os ${ Math.acos(cosC)}")

        val all = Math.acos(cosA)+Math.acos(cosB)+Math.acos(cosC)

        println("角度A is ${Math.acos(cosA)/all*180}, 角度B is ${Math.acos(cosB)/all*180}, 角度C is ${Math.acos(cosC)/all*180}")
    }

    @Test
    fun addition_isCorrect1() {
        println(Math.pow(2.0,2.0))
        println(Math.pow(3.0,2.0))
        println(Math.pow(3.0,3.0))
    }

}
