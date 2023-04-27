import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.hrapp.database.TodoItem

@Dao
interface TodoDatabaseDao {
    @Query("SELECT * from my_todo_list")
    fun getAll(): LiveData<List<TodoItem>>

    @Query("SELECT * from my_todo_list where itemId = :id")
    fun getById(id: Int): LiveData<TodoItem?>

    @Insert
    suspend fun insert(item: TodoItem)

    @Update
    suspend fun update(item: TodoItem)

    @Delete
    suspend fun delete(item: TodoItem) : Integer

    @Query("DELETE FROM my_todo_list")
    suspend fun deleteAllTodos(): Integer
}