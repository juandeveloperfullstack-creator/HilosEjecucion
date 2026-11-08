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
import kotlin.random.Random

class VistaMultitadibujo @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    // Estructuras para almacenar rutas activas e históricas por cada dedo (pointerId)
    private val trazosActivos = mutableMapOf<Int, Pair<Path, Paint>>()
    private val listaHistorica = mutableListOf<Pair<Path, Paint>>()

    private val coloresDisponibles = listOf(
        Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA,
        Color.CYAN, Color.parseColor("#FF9800"), Color.parseColor("#9C27B0")
    )

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Dibujar trazos anteriores ya finalizados
        for ((ruta, pincel) in listaHistorica) {
            canvas.drawPath(ruta, pincel)
        }
        // Dibujar trazos que se están realizando en tiempo real con los dedos en pantalla
        for ((_, par) in trazosActivos) {
            canvas.drawPath(par.first, par.second)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val actionMasked = event.actionMasked
        val pointerIndex = event.actionIndex
        val pointerId = event.getPointerId(pointerIndex)

        when (actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                val x = event.getX(pointerIndex)
                val y = event.getY(pointerIndex)

                val nuevaRuta = Path().apply { moveTo(x, y) }
                val colorAleatorio = coloresDisponibles[Random.nextInt(coloresDisponibles.size)]

                val nuevoPincel = Paint().apply {
                    color = colorAleatorio
                    isAntiAlias = true
                    strokeWidth = 16f
                    style = Paint.Style.STROKE
                    strokeJoin = Paint.Join.ROUND
                    strokeCap = Paint.Cap.ROUND
                }

                trazosActivos[pointerId] = Pair(nuevaRuta, nuevoPincel)
            }
            MotionEvent.ACTION_MOVE -> {
                // Actualizar todas las rutas activas de todos los dedos en pantalla
                for (i in 0 until event.pointerCount) {
                    val id = event.getPointerId(i)
                    val par = trazosActivos[id]
                    if (par != null) {
                        par.first.lineTo(event.getX(i), event.getY(i))
                    }
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP, MotionEvent.ACTION_CANCEL -> {
                val par = trazosActivos.remove(pointerId)
                if (par != null) {
                    listaHistorica.add(par)
                }
            }
        }
        invalidate()
        return true
    }
}