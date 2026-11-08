package com.example.hilosejecucion

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class VistaDeDibujo @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val listaDeRutas = mutableListOf<Pair<Path, Paint>>()
    private var rutaActual = Path()

    private val pincel = Paint().apply {
        color = Color.BLUE
        isAntiAlias = true
        strokeWidth = 12f
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Redibujar todos los trazos almacenados previamente
        for ((ruta, dibujoPincel) in listaDeRutas) {
            canvas.drawPath(ruta, dibujoPincel)
        }
        // Dibujar la ruta actual en tiempo real
        canvas.drawPath(rutaActual, pincel)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                rutaActual = Path()
                rutaActual.moveTo(x, y)
                // Guardamos la ruta con una copia de la configuración del pincel
                listaDeRutas.add(Pair(rutaActual, Paint(pincel)))
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                rutaActual.lineTo(x, y)
                invalidate() // Forzar a que la vista se repinte (llama a onDraw)
                return true
            }
            else -> return false
        }
    }
}