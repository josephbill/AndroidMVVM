package com.example.hrapp

import TodoDatabase
import TodoDatabaseDao
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.hrapp.database.TodoItem
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class TodoDatabaseTest {

    private lateinit var todoDao: TodoDatabaseDao
    private lateinit var db: TodoDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        db = Room.inMemoryDatabaseBuilder(context, TodoDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        todoDao = db.todoDao()
    }

    @After
    @Throws(IOException::class)
    fun deleteDb() {
        if (::db.isInitialized) {
            db.close()
        }
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetTodo() = runBlocking {
        val todoItem = TodoItem(itemId = 1, itemName = "Dummy Item", isDone = false)
        todoDao.insert(todoItem)
        val oneItem = todoDao.getById(1)
        assertEquals(oneItem, 1)
    }
}