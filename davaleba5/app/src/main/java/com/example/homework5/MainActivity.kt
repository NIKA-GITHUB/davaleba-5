package com.example.homework5

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var db: AppDatabase
    private lateinit var dao: UserDao

    private lateinit var ranRange: EditText
    private lateinit var swimRange: EditText
    private lateinit var calorie: EditText

    private lateinit var saveBtn: Button

    private lateinit var run: TextView
    private lateinit var avgStat: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "user"
        ).build()

        dao = db.userDao()

        ranRange = findViewById(R.id.editTextNumber2)
        swimRange = findViewById(R.id.editTextNumber3)
        calorie = findViewById(R.id.editTextNumber4)

        saveBtn = findViewById(R.id.saveBtn)

        run = findViewById(R.id.run)
        avgStat = findViewById(R.id.avgStat)

        saveBtn.setOnClickListener {
            if (ranRange.text.isEmpty() || swimRange.text.isEmpty() || calorie.text.isEmpty())
                Toast.makeText(this, "fill the fields", Toast.LENGTH_SHORT).show()
            else {
                lifecycleScope.launch {
                    withContext(Dispatchers.Default) { dao.insertAll(getData()) }
                    run.text = "Total Distance Run: ${dao.getTotalDistanceRun()}"
                    avgStat.text = getAvgData()
                }
            }
        }
    }

    private fun getData(): User {
        val run: Double = ranRange.text.toString().toDouble()
        val swim: Double = swimRange.text.toString().toDouble()
        val calories: Double = calorie.text.toString().toDouble()

        return User(
            runRange = run,
            swimRange = swim,
            calorie = calories
        )
    }

    private suspend fun getAvgData(): String {
        val avgRun: Double = dao.getAvgDistanceRun()
        val avgSwim: Double = dao.getAvgDistanceSwim()
        val avgCalories: Double = dao.getAvgCalories()

        return "Avg run: $avgRun\nAvg Swim: $avgSwim\n Avg Calories: $avgCalories"
    }
}
