package com.example.jose_gonzalez.popularmovies.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.jose_gonzalez.popularmovies.dbmodel.FavoriteModel;

import java.util.ArrayList;
import java.util.List;

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

    private static final String COMMA_SEP = ",";
    private static final String TEXT_TYPE = " TEXT";

    //.___ Database Version __./
    private static final int DATABASE_VERSION = 1;
    //.___ Logcat tag __./
    private static final String LOG = "DatabaseHelper";
    //.___ Database Name __./
    private static final String DATABASE_NAME = "popMoviesBasic";

    //.___ Table Names __./
    private static final String TABLE_FAVORITES = "favorites";
    //.___ Column names __./
    private static final String FAVORITE_ID = "favorite_id";
    private static final String FAVORITE_URL = "favorite_url";
    private static final String FAVORITE_NAME = "favorite_name";

    //.___ Favorite table create statement __./
    private static final String CREATE_TABLE_FAVORITES = "CREATE TABLE "
            + TABLE_FAVORITES + "("
            + FAVORITE_ID + " INTEGER PRIMARY KEY,"
            + FAVORITE_URL + TEXT_TYPE + COMMA_SEP
            + FAVORITE_NAME + TEXT_TYPE
            + ")";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //.___ creating required tables __./
        db.execSQL(CREATE_TABLE_FAVORITES);
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
        values.put(FAVORITE_ID, favoriteModel.getId());
        values.put(FAVORITE_URL, favoriteModel.getUrl());
        values.put(FAVORITE_NAME, favoriteModel.getName());

        //.___ insert row __./
        return db.insert(TABLE_FAVORITES, null, values);
    }

    /**.___
     * Delete favorite
     __.*/
    public void deleteFavorite(long favorite_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FAVORITES, FAVORITE_ID + "=" + favorite_id, null);
    }

    /**.___
     * get single favorite by id
     * remember to close DB after using
    __.*/
    public FavoriteModel getFavoriteById(long favorite_id) throws CursorIndexOutOfBoundsException {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_FAVORITES + " WHERE "
                + FAVORITE_ID + " = " + favorite_id;

        Log.e(LOG, selectQuery);

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null)
            cursor.moveToFirst();

        return new FavoriteModel(cursor.getLong(cursor.getColumnIndex(FAVORITE_ID)),
                cursor.getString(cursor.getColumnIndex(FAVORITE_URL)),
                cursor.getString(cursor.getColumnIndex(FAVORITE_NAME)));
    }

    /**.___
     * get single favorite by url
     * remember to close DB after using
     __.*/
    public FavoriteModel getFavoriteByUrl(long favorite_url) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_FAVORITES + " WHERE "
                + FAVORITE_URL + " = " + favorite_url;

        Log.e(LOG, selectQuery);

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null)
            cursor.moveToFirst();

        return new FavoriteModel(cursor.getLong(cursor.getColumnIndex(FAVORITE_ID)),
                cursor.getString(cursor.getColumnIndex(FAVORITE_URL)),
                cursor.getString(cursor.getColumnIndex(FAVORITE_NAME)));
    }

    /**.___
     * Returns a list of the favorite movies posters urls.
     __.*/
    public List<String> getFavoriteMoviesPosterUrls(){
        List<String> list = new ArrayList<>();
        Cursor cursor = this.getReadableDatabase().rawQuery("select * from table", null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String url = cursor.getString(cursor.getColumnIndex(FAVORITE_URL));
                list.add(url);
                cursor.moveToNext();
            }
        }
        return list;
    }

    //.___ closing database __./
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

}