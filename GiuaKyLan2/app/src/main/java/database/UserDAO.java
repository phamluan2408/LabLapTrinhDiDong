package database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.giuakylan2.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM user")
    List<User> getListUser();
}
