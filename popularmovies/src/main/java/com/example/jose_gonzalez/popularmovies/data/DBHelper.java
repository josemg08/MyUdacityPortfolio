package com.example.jose_gonzalez.popularmovies.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.jose_gonzalez.popularmovies.dbmodel.FavoriteModel;

/**.___
 * Created by jose-gonzalez on 14/12/15.
 __.*/
public class DBHelper extends SQLiteOpenHelper {

    private static DBHelper instance;

    public static synchronized DBHelper getHelper(Context context){
        if (instance == null) {
            instance = new DBHelper(context);
        }

        return instance;
    }

    //.___ Logcat tag __./
    private static final String LOG = "DatabaseHelper";

    //.___ Database Version __./
    private static final int DATABASE_VERSION = 1;

    //.___ Database Name __./
    private static final String DATABASE_NAME = "popMoviesBasic";

    //.___ Table Names __./
    private static final String TABLE_FAVORITES = "favorites";

    //.___ Column names __./
    private static final String FAVORITES_ID = "badge_id";
    private static final String FAVORITES_NAME = "badge_name";

    // Table Create Statements TODO is this necessary?
    // Badge table create statement
    private static final String CREATE_TABLE_BADGE = "CREATE TABLE "
            + TABLE_FAVORITES + "(" + FAVORITES_ID + " INTEGER PRIMARY KEY," + FAVORITES_NAME + ")";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //.___ creating required tables __./
        db.execSQL(CREATE_TABLE_BADGE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //.___ on upgrade drop older tables __./
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITES);

        //.___ create new tables __./
        onCreate(db);
    }

    /**.___
    * Creating a favoriteModel
    __.*/
    public long createFavorite(FavoriteModel favoriteModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FAVORITES_ID, favoriteModel.getId());
        values.put(FAVORITES_NAME, favoriteModel.getName());

        //.___ insert row __./
        return db.insert(TABLE_FAVORITES, null, values);
    }

    /**.___
     * get single favorite by id
     * remember to close DB after using
    __.*/
    public FavoriteModel getBadge(long favorite_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_FAVORITES + " WHERE "
                + FAVORITES_ID + " = " + favorite_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        return new FavoriteModel(c.getLong(c.getColumnIndex(FAVORITES_ID)),
                c.getString(c.getColumnIndex(FAVORITES_NAME)));
    }

    //.___ closing database __./
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

}