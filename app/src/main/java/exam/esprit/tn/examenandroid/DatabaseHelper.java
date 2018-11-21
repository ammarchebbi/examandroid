package exam.esprit.tn.examenandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "film";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOM = "nom";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_TYPE_PROJECTION = "typeprojection";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "examen5GL";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL("CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NOM + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_TYPE_PROJECTION + " TEXT"
                + ")");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertFilm(Film film) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(COLUMN_NOM, film.getNom());
        values.put(COLUMN_DESCRIPTION, film.getDescription());
        values.put(COLUMN_TYPE_PROJECTION, film.getTypeProjection());

        // insert row
        long id = db.insert(TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public List<Film> getAllFilms() {
        List<Film> Films = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Film voiture = new Film();
                voiture.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
                voiture.setNom(cursor.getString(cursor.getColumnIndex(COLUMN_NOM)));
                voiture.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
                voiture.setTypeProjection(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE_PROJECTION)));

                Films.add(voiture);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return Films;
    }

    public void deleteFilms() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME);
        db.close();
    }

    public void deleteFilm(Film Film) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME + " where id=" + Film.getId());
        db.close();
    }
}