package com.example.todolist.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todolist.di.ApplicationScope
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Task::class],version = 1)
abstract class TaskDatabase:RoomDatabase() {

    abstract fun taskDao():TaskDao

    class Callback @Inject constructor(               // provides the callback dependency required in the AppModule
        private val database:Provider<TaskDatabase>,  //call it lazily , so its only instantiated when .get() is called upon it
        @ApplicationScope private val applicationScope:CoroutineScope
    ):RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao=database.get().taskDao()

            applicationScope.launch {
                dao.insert(Task("Wash the dishes"))
                dao.insert(Task("Call Elon",important = true))
                dao.insert(Task("Visit Grandma",completed = true))
                dao.insert(Task("Prepare Food"))
                dao.insert(Task("Study for exams",completed = true))
            }

        }
    }
}