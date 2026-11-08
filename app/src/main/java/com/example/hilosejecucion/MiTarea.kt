package com.example.hilosejecucion

// Clase que implementa Runnable para ejecutarse en un hilo secundario
class MiTarea(
    private val n: Int,
    private val onResultado: (Int) -> Unit
) : Runnable {

    override fun run() {
        // Operación matemática: sumar los primeros N números
        var suma = 0
        for (i in 1..n) {
            suma += i
            // Simulamos un pequeño retraso para notar el proceso en segundo plano
            Thread.sleep(50)
        }

        // Devolvemos el resultado mediante un callback (lambda)
        onResultado(suma)
    }
}