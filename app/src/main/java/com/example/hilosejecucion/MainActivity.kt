//package com.example.hilosejecucion
//
//import android.os.Bundle
//import android.widget.Button
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//
//class MainActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val tvResultado = findViewById<TextView>(R.id.tvResultado)
//        val btnIniciar = findViewById<Button>(R.id.btnIniciar)
//
//        btnIniciar.setOnClickListener {
//            // Desactivar el botón opcionalmente para evitar múltiples clics simultáneos
//            btnIniciar.isEnabled = false
//
//            // Crear e iniciar el hilo secundario (Thread)
//            Thread {
//                for (i in 1..10) {
//                    // Pausar el hilo secundario durante 1 segundo (1000 milisegundos)
//                    Thread.sleep(1000)
//
//                    // Actualizar la interfaz gráfica usando runOnUiThread
//                    runOnUiThread {
//                        tvResultado.text = "Número: $i"
//                    }
//                }
//
//                // Volver a activar el botón al terminar el conteo
//                runOnUiThread {
//                    btnIniciar.isEnabled = true
//                    tvResultado.text = "¡Conteo finalizado!"
//                }
//            }.start()
//        }
//    }
//}

//EJERCICIO 2

//package com.example.hilosejecucion
//
//import android.os.Bundle
//import android.widget.Button
//import android.widget.ProgressBar
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//
//class MainActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val tvProgreso = findViewById<TextView>(R.id.tvProgreso)
//        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
//        val btnIniciarCarga = findViewById<Button>(R.id.btnIniciarCarga)
//
//        btnIniciarCarga.setOnClickListener {
//            btnIniciarCarga.isEnabled = false
//            progressBar.progress = 0
//
//            // Hilo secundario para simular la carga progresiva
//            Thread {
//                for (i in 1..100) {
//                    Thread.sleep(100) // Retardo de 100 ms entre cada incremento
//
//                    // Actualizar UI de forma segura con runOnUiThread
//                    runOnUiThread {
//                        progressBar.progress = i
//                        tvProgreso.text = "Progreso: $i%"
//                    }
//                }
//
//                // Al finalizar la carga
//                runOnUiThread {
//                    btnIniciarCarga.isEnabled = true
//                    tvProgreso.text = "¡Carga Completa!"
//                }
//            }.start()
//        }
//    }
//}

//EJERCICIO 3

//package com.example.hilosejecucion
//
//import android.os.Bundle
//import android.widget.Button
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//
//class MainActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val tvPares = findViewById<TextView>(R.id.tvPares)
//        val tvImpares = findViewById<TextView>(R.id.tvImpares)
//        val tvLetras = findViewById<TextView>(R.id.tvLetras)
//        val btnIniciarHilos = findViewById<Button>(R.id.btnIniciarHilos)
//
//        btnIniciarHilos.setOnClickListener {
//            btnIniciarHilos.isEnabled = false
//
//            // Limpiar textos al iniciar
//            tvPares.text = "Pares: "
//            tvImpares.text = "Impares: "
//            tvLetras.text = "Letras: "
//
//            // Hilo 1: Números Pares (del 2 al 20)
//            Thread {
//                for (i in 2..20 step 2) {
//                    Thread.sleep(300)
//                    runOnUiThread {
//                        tvPares.text = "${tvPares.text} $i"
//                    }
//                }
//            }.start()
//
//            // Hilo 2: Números Impares (del 1 al 19)
//            Thread {
//                for (i in 1..19 step 2) {
//                    Thread.sleep(300)
//                    runOnUiThread {
//                        tvImpares.text = "${tvImpares.text} $i"
//                    }
//                }
//            }.start()
//
//            // Hilo 3: Letras del alfabeto (de la A a la J)
//            Thread {
//                val letras = listOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J")
//                for (letra in letras) {
//                    Thread.sleep(300)
//                    runOnUiThread {
//                        tvLetras.text = "${tvLetras.text} $letra"
//                    }
//                }
//
//                // Volver a habilitar el botón cuando el último hilo termine
//                runOnUiThread {
//                    btnIniciarHilos.isEnabled = true
//                }
//            }.start()
//        }
//    }
//}

//EJERCICIO 4

//package com.example.hilosejecucion
//
//import android.os.Bundle
//import android.widget.Button
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//
//class MainActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val tvResultadoTarea = findViewById<TextView>(R.id.tvResultadoTarea)
//        val btnEjecutarTarea = findViewById<Button>(R.id.btnEjecutarTarea)
//
//        btnEjecutarTarea.setOnClickListener {
//            btnEjecutarTarea.isEnabled = false
//            tvResultadoTarea.text = "Calculando en segundo plano..."
//
//            val nValor = 100 // Sumaremos los primeros 100 números
//
//            // Instanciamos nuestra clase MiTarea pasando el número y el callback para el resultado
//            val tarea = MiTarea(nValor) { resultadoSuma ->
//                // Actualizamos la UI de manera segura usando runOnUiThread
//                runOnUiThread {
//                    tvResultadoTarea.text = "La suma de los primeros $nValor números es: $resultadoSuma"
//                    btnEjecutarTarea.isEnabled = true
//                }
//            }
//
//            // Creamos el hilo pasándole la tarea e iniciamos su ejecución
//            Thread(tarea).start()
//        }
//    }
//}

//EJERCICO 5

//package com.example.hilosejecucion
//
//import android.os.Bundle
//import android.util.Log
//import android.widget.Button
//import androidx.appcompat.app.AppCompatActivity
//import androidx.lifecycle.lifecycleScope
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.launch
//
//class MainActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val btnCorrutina = findViewById<Button>(R.id.btnCorrutina)
//
//        btnCorrutina.setOnClickListener {
//            // Lanzamos una corrutina ligada al ciclo de vida de la Actividad
//            lifecycleScope.launch {
//                Log.d("Corrutina", "--- Inicio de la corrutina ---")
//
//                for (i in 1..5) {
//                    Log.d("Corrutina", "Mensaje número $i impreso en consola")
//                    // Espera 1 segundo (1000 ms) sin bloquear el hilo principal
//                    delay(1000)
//                }
//
//                Log.d("Corrutina", "--- Fin de la corrutina ---")
//            }
//        }
//    }
//}

//EJERCICIO 6

//package com.example.hilosejecucion
//
//import android.os.Bundle
//import android.widget.Button
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//import androidx.lifecycle.lifecycleScope
//import kotlinx.coroutines.Job
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.isActive
//import kotlinx.coroutines.launch
//import kotlin.random.Random
//
//class MainActivity : AppCompatActivity() {
//
//    private var sensorJob: Job? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val tvSensor = findViewById<TextView>(R.id.tvSensor)
//        val btnIniciarSensor = findViewById<Button>(R.id.btnIniciarSensor)
//        val btnDetenerSensor = findViewById<Button>(R.id.btnDetenerSensor)
//
//        // Al iniciar, el botón de detener está desactivado
//        btnDetenerSensor.isEnabled = false
//
//        btnIniciarSensor.setOnClickListener {
//            btnIniciarSensor.isEnabled = false
//            btnDetenerSensor.isEnabled = true
//
//            // Lanzamos la corrutina y guardamos la referencia en sensorJob
//            sensorJob = lifecycleScope.launch {
//                while (isActive) {
//                    // Generar un valor aleatorio simulando un sensor (ej: temperatura entre 20 y 40 °C)
//                    val valorAleatorio = Random.nextInt(20, 41)
//
//                    // Actualizar la interfaz periódicamente
//                    tvSensor.text = "Valor del Sensor: $valorAleatorio °C"
//
//                    // Esperar 500 ms antes de la siguiente lectura
//                    delay(500)
//                }
//            }
//        }
//
//        btnDetenerSensor.setOnClickListener {
//            // Cancelamos la corrutina en curso
//            sensorJob?.cancel()
//            sensorJob = null
//
//            tvSensor.text = "Sensor Detenido"
//            btnIniciarSensor.isEnabled = true
//            btnDetenerSensor.isEnabled = false
//        }
//    }
//}

//EJERCICIO 7

//package com.example.hilosejecucion
//
//import android.annotation.SuppressLint
//import android.os.Bundle
//import android.view.MotionEvent
//import android.widget.LinearLayout
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//
//class MainActivity : AppCompatActivity() {
//
//    @SuppressLint("ClickableViewAccessibility")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val tvTactil = findViewById<TextView>(R.id.tvTactil)
//        val mainLayout = findViewById<LinearLayout>(R.id.mainLayout)
//
//        // Asignamos el listener táctil al contenedor principal
//        mainLayout.setOnTouchListener { _, event ->
//            when (event.action) {
//                MotionEvent.ACTION_DOWN -> {
//                    tvTactil.text = "Pulsación iniciada"
//                    true
//                }
//                MotionEvent.ACTION_MOVE -> {
//                    tvTactil.text = "Desplazando el dedo"
//                    true
//                }
//                MotionEvent.ACTION_UP -> {
//                    tvTactil.text = "Pulsación finalizada"
//                    true
//                }
//                else -> false
//            }
//        }
//    }
//}

//EJERCICIO 8

//package com.example.hilosejecucion
//
//import android.annotation.SuppressLint
//import android.os.Bundle
//import android.view.MotionEvent
//import android.widget.LinearLayout
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//
//class MainActivity : AppCompatActivity() {
//
//    @SuppressLint("ClickableViewAccessibility")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val tvCoordenadas = findViewById<TextView>(R.id.tvCoordenadas)
//        val mainLayout = findViewById<LinearLayout>(R.id.mainLayout)
//
//        mainLayout.setOnTouchListener { _, event ->
//            when (event.action) {
//                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
//                    // Extraemos las coordenadas X e Y del toque
//                    val x = event.x
//                    val y = event.y
//
//                    // Mostramos las coordenadas formateadas (con 1 o 2 decimales si se prefiere)
//                    tvCoordenadas.text = "X: ${String.format("%.1f", x)}\nY: ${String.format("%.1f", y)}"
//                    true
//                }
//                MotionEvent.ACTION_UP -> {
//                    tvCoordenadas.text = "Toca la pantalla para ver X e Y"
//                    true
//                }
//                else -> false
//            }
//        }
//    }
//}

//EJERCICIO 9

//package com.example.hilosejecucion
//
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//
//class MainActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//    }
//}

//EJERCICIO 10

//package com.example.hilosejecucion
//
//import android.annotation.SuppressLint
//import android.os.Bundle
//import android.view.MotionEvent
//import android.widget.ImageView
//import androidx.appcompat.app.AppCompatActivity
//
//class MainActivity : AppCompatActivity() {
//
//    private var dX = 0f
//    private var dY = 0f
//
//    @SuppressLint("ClickableViewAccessibility")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val ivPelota = findViewById<ImageView>(R.id.ivPelota)
//
//        ivPelota.setOnTouchListener { view, event ->
//            when (event.action) {
//                MotionEvent.ACTION_DOWN -> {
//                    // Guardamos la diferencia inicial entre la posición de la vista y la posición del toque
//                    dX = view.x - event.rawX
//                    dY = view.y - event.rawY
//                    true
//                }
//                MotionEvent.ACTION_MOVE -> {
//                    // Actualizamos la posición de la vista conforme el dedo se desplaza por la pantalla
//                    view.animate()
//                        .x(event.rawX + dX)
//                        .y(event.rawY + dY)
//                        .setDuration(0)
//                        .start()
//                    true
//                }
//                else -> false
//            }
//        }
//    }
//}

//EJERCICIO 11

//package com.example.hilosejecucion
//
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//
//class MainActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//    }
//}

//EJERCICIO 12

//package com.example.hilosejecucion
//
//import android.annotation.SuppressLint
//import android.os.Bundle
//import android.view.GestureDetector
//import android.view.MotionEvent
//import android.widget.LinearLayout
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.GestureDetectorCompat
//
//class MainActivity : AppCompatActivity() {
//
//    private lateinit var detectorGestos: GestureDetectorCompat
//
//    @SuppressLint("ClickableViewAccessibility")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val tvGesto = findViewById<TextView>(R.id.tvGesto)
//        val mainLayout = findViewById<LinearLayout>(R.id.mainLayout)
//
//        // Inicializamos el GestureDetector con sus callbacks
//        detectorGestos = GestureDetectorCompat(this, object : GestureDetector.SimpleOnGestureListener() {
//
//            override fun onDown(e: MotionEvent): Boolean {
//                return true
//            }
//
//            override fun onSingleTapUp(e: MotionEvent): Boolean {
//                tvGesto.text = "Gesto detectado: Toque simple (Tap)"
//                return true
//            }
//
//            override fun onLongPress(e: MotionEvent) {
//                tvGesto.text = "Gesto detectado: Toque prolongado (Long Press)"
//            }
//
//            override fun onFling(
//                e1: MotionEvent?,
//                e2: MotionEvent,
//                velocityX: Float,
//                velocityY: Float
//            ): Boolean {
//                tvGesto.text = "Gesto detectado: Deslizamiento rápido (Fling)"
//                return true
//            }
//        })
//
//        // Conectamos el detector de gestos al listener táctil del layout
//        mainLayout.setOnTouchListener { _, event ->
//            detectorGestos.onTouchEvent(event)
//            true
//        }
//    }
//}

//EJERCICIO 13

//package com.example.hilosejecucion
//
//import android.annotation.SuppressLint
//import android.os.Bundle
//import android.view.MotionEvent
//import android.widget.LinearLayout
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//
//class MainActivity : AppCompatActivity() {
//
//    @SuppressLint("ClickableViewAccessibility")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val tvMultitouch = findViewById<TextView>(R.id.tvMultitouch)
//        val mainLayout = findViewById<LinearLayout>(R.id.mainLayout)
//
//        mainLayout.setOnTouchListener { _, event ->
//            val cantidadDedos = event.pointerCount
//            val sb = StringBuilder()
//
//            sb.append("Dedos en pantalla: $cantidadDedos\n\n")
//
//            // Recorremos cada dedo activo (puntero)
//            for (i in 0 until cantidadDedos) {
//                val x = event.getX(i)
//                val y = event.getY(i)
//                sb.append("Dedo ${i + 1} -> X: ${String.format("%.1f", x)}, Y: ${String.format("%.1f", y)}\n")
//            }
//
//            tvMultitouch.text = sb.toString()
//            true
//        }
//    }
//}

//EJERCICIO 14

//package com.example.hilosejecucion
//
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//
//class MainActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        // Carga la interfaz XML que contiene nuestra VistaMultitadibujo
//        setContentView(R.layout.activity_main)
//    }
//}

//EJERCICIO 15

package com.example.hilosejecucion

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.max
import kotlin.math.min

class MainActivity :AppCompatActivity() {

    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var factorEscala = 1f
    private lateinit var ivZoom: ImageView

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivZoom = findViewById(R.id.ivZoom)

        // Inicializamos el ScaleGestureDetector
        scaleGestureDetector = ScaleGestureDetector(
            this,
            object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
                override fun onScale(detector: ScaleGestureDetector): Boolean {
                    factorEscala *= detector.scaleFactor

                    // Limitamos el zoom entre 0.5x (alejar) y 4.0x (acercar)
                    factorEscala = max(0.5f, min(factorEscala, 4.0f))

                    // Aplicamos la escala a la ImageView
                    ivZoom.scaleX = factorEscala
                    ivZoom.scaleY = factorEscala

                    return true
                }
            })

        // Conectamos el detector al evento táctil de la imagen
        ivZoom.setOnTouchListener { _, event ->
            scaleGestureDetector.onTouchEvent(event)
            true
        }
    }
}

