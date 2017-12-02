package mivors.ntaslapplication.sqlLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import mivors.ntaslapplication.model.item.Item;

import static mivors.ntaslapplication.sqlLite.ItemContract.ItemEntry.TABLE_NAME;

/**
 * Created by Ahmed shaban on 12/2/2017.
 */

public class ItemDbHelper  extends SQLiteOpenHelper {

    // The name of the database
    private static final String DATABASE_NAME = "Db.db";

    // If you change the database schema, you must increment the database version
    private static final int VERSION = 1;


    // Constructor
    public ItemDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    /**
     * Called when the items database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create items table (careful to follow SQL formatting rules)
        final String CREATE_TABLE = "CREATE TABLE "  + TABLE_NAME + " (" +
                ItemContract.ItemEntry._ID                + " INTEGER PRIMARY KEY, " +
                ItemContract.ItemEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                ItemContract.ItemEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
                ItemContract.ItemEntry.COLUMN_PHOTOURL    + " TEXT NOT NULL);";

        db.execSQL(CREATE_TABLE);
    }

    public void addItems(ArrayList<Item> items) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (Item item : items) {
                values.put(ItemContract.ItemEntry.COLUMN_NAME, item.getName());
                values.put(ItemContract.ItemEntry.COLUMN_DESCRIPTION, item.getDescription());
                values.put(ItemContract.ItemEntry.COLUMN_PHOTOURL, item.getPhotoUrl());
                db.insert(ItemContract.ItemEntry.TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public ArrayList<Item>  getAllItems() {
        ArrayList<Item> items = new ArrayList<>();
        // Get access to underlying database (read-only for query)
        final SQLiteDatabase db = this.getReadableDatabase();

        Cursor retCursor;
        retCursor =  db.query(TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);


        if(retCursor!=null){
            while (retCursor.moveToNext()) {
                Item item = new Item();
                item.setId(retCursor.getInt(retCursor.getColumnIndex(ItemContract.ItemEntry._ID)));
                item.setName( retCursor.getString(retCursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_NAME)));
                item.setDescription( retCursor.getString(retCursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_DESCRIPTION)));
                item.setPhotoUrl( retCursor.getString(retCursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_PHOTOURL)));
                items.add(item);
            }
        }

        db.close();


        return  items;

    }


    /**
     * This method discards the old table of data and calls onCreate to recreate a new one.
     * This only occurs when the version number for this database (DATABASE_VERSION) is incremented.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ItemContract.ItemEntry.TABLE_NAME);
        onCreate(db);
    }

    public void deleteSavedData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from  " + TABLE_NAME);
        db.close();
    }
}