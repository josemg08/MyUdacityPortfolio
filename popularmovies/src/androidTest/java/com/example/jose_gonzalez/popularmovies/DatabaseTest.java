package com.example.jose_gonzalez.popularmovies;

import android.database.CursorIndexOutOfBoundsException;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.example.jose_gonzalez.popularmovies.data.DBHelper;
import com.example.jose_gonzalez.popularmovies.dbmodel.FavoriteModel;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**.___
 * Created by Jose Gonzalez 22/12/2015
 __.*/
public class DatabaseTest extends AndroidTestCase {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private DBHelper db;
    private RenamingDelegatingContext context;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        context = new RenamingDelegatingContext(getContext(), "test_");
        db = new DBHelper(context);
    }

    @Override
    public void tearDown() throws Exception {
        db.close();
        super.tearDown();
    }

    @Test
    public void testAddEntry() {
        db.createFavorite(new FavoriteModel(1, "www1", "Fab1"));
        db.createFavorite(new FavoriteModel(2, "www2", "Fab2"));
        db.createFavorite(new FavoriteModel(3, "www3", "Fab3"));
        assertEquals(db.getFavoriteById(2).getName(), "Fab2");
        db.createFavorite(new FavoriteModel(5, "www5", "Fab5"));
        db.createFavorite(new FavoriteModel(6, "www6", "Fab6"));
        db.close();
        context.deleteDatabase(db.getDatabaseName());

        /*db = new DBHelper(context);
        db.createFavorite(new FavoriteModel(4, "www4", "Fab4"));
        assertEquals(db.getFavoriteById(2).getName(), "Fab2");

        db.close();
        db = new DBHelper(context);
        assertEquals(db.getFavoriteById(2).getName(), "Fab2");

        db.close();
        context.deleteDatabase(db.getDatabaseName());
        assertEquals(db.getFavoriteById(2).getName(), "Fab2");*/
    }

    @Test
    public void testDelete() {
        db = new DBHelper(context);
        db.createFavorite(new FavoriteModel(1, "www1", "Fab1"));
        db.createFavorite(new FavoriteModel(2, "www2", "Fab2"));
        db.createFavorite(new FavoriteModel(3, "www3", "Fab3"));
        db.createFavorite(new FavoriteModel(4, "www4", "Fab4"));
        db.deleteFavorite(3);
        assertEquals(db.getFavoriteById(2).getName(), "Fab2");
        exception.expect(CursorIndexOutOfBoundsException.class);
        try {
            db.getFavoriteById(3);
        }
        catch (CursorIndexOutOfBoundsException exception){
            //yey
        }
        db.close();
    }

}