package com.example.week2lab.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.Nullable;

public class MyContentProvider extends ContentProvider {
    public MyContentProvider() {
    }

    ItemDatabase db;
    public static final String CONTENT_AUTHORITY = "fit2081.app.yanming";
    public static final Uri CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final int MULTIPLE_ITEMS = 1;
    public static final int SINGLE_ITEM = 2;

    private static final UriMatcher itemMatcher = createItemsMatcher();

    private static UriMatcher createItemsMatcher(){
        final UriMatcher urimatcher = new UriMatcher(UriMatcher.NO_MATCH);
        urimatcher.addURI(CONTENT_AUTHORITY, Item.TABLE_NAME, MULTIPLE_ITEMS);
        urimatcher.addURI(CONTENT_AUTHORITY, Item.TABLE_NAME + "/#", SINGLE_ITEM);

        return urimatcher;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int deletionCount;

        deletionCount = db.getOpenHelper().getWritableDatabase().delete(Item.TABLE_NAME,selection,selectionArgs);

        return deletionCount;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        String tableName;

        int uriType = itemMatcher.match(uri);

        if(uriType==MULTIPLE_ITEMS){
            tableName = Item.TABLE_NAME;
        }else{
            throw new UnsupportedOperationException("Unsupported Operation");
        }

        long newID = db.getOpenHelper().getWritableDatabase().insert(tableName,0,values);

        return ContentUris.withAppendedId(CONTENT_URI, newID);
    }

    @Override
    public boolean onCreate() {

        db = ItemDatabase.getDatabase(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {


        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(Item.TABLE_NAME);
        String query = builder.buildQuery(projection,selection,null,null,sortOrder,null);
        final Cursor cursor = db.getOpenHelper().getReadableDatabase().query(query,selectionArgs);

        return cursor;
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        int updateCount;

        updateCount = db.getOpenHelper().getWritableDatabase().update(Item.TABLE_NAME,
                0,values,selection,selectionArgs);

        return updateCount;
    }
}
