package www.nabil.keenam.database;


import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDAO {

    @Insert
    void insertMovie(MovieModelDb movieModelDb);

    @Query("SELECT * FROM favorite_movie")
    List<MovieModelDb> getMovieDb();

    @Delete
    void deleteMovie(MovieModelDb movieModelDb);

    @Query("SELECT * FROM favorite_movie WHERE id = :idMovie LIMIT 1")
    List<MovieModelDb> getById(int idMovie);

    @Query("SELECT * FROM favorite_movie WHERE category = :category")
    List<MovieModelDb> getByCategory(String category);

    //query dibawah untuk contentprovider

    @Query("SELECT COUNT(*) FROM " + "favorite_movie")
    int count();

    @Insert
    long[] insertAll(MovieModelDb[] movie);

    @Insert
    long insert(MovieModelDb movieModelDb);

    @Query("SELECT * FROM favorite_movie")
    Cursor getAllMovie();

    @Query("SELECT * FROM favorite_movie WHERE id = :idMovie LIMIT 1")
    Cursor getMovieById(long idMovie);
}
