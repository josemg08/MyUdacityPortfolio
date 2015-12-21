package com.example.jose_gonzalez.popularmovies.data;

/**
 * Created by jose-gonzalez on 21/12/15.
 */
public class DBTableCreator {
    private static final String COMMA_SEP = ",";
    private static final String TEXT_TYPE = " TEXT";

    public DBTableCreator(){

    }

    public void createFavoriteTable(){
        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + "FAVORITE" + " (" +      //.___ Table Name __./
                        "ID" + " INTEGER PRIMARY KEY," +   //.___ Primary key, ID column __./
                        "URL" + TEXT_TYPE + COMMA_SEP +    //.___ URL column __./
                        " )";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;
    }

}
