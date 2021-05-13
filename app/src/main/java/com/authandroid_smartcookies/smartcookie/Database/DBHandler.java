package com.authandroid_smartcookies.smartcookie.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.authandroid_smartcookies.smartcookie.DataClasses.CocktailRecipe;

import java.io.File;

public class DBHandler extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SmartCookie.db";
    public static String DB_PATH;

    public DBHandler(Context context, String name,
                     SQLiteDatabase.CursorFactory factory, int DATA) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        DB_PATH = context.getPackageName() + "/databases/SmartCookie.db";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String q1 = "CREATE TABLE RECIPES (\n" +
                "    id integer primary key,\n" +
                "    title varchar,\n" +
                "    description varchar,\n" +
                "    steps varchar,\n" +
                "    drink varchar,\n" +
                "    imageid varchar,\n" +
                "    color varchar,\n" +
                "    timer int)";
        String q2 = "CREATE TABLE RECIPE_INGREDIENTS (\n" +
                "    id integer primary key,\n" +
                "    recipeId integer,\n" +
                "    ingredients varchar,\n" +
                "    measure varchar,\n" +
                "    ingredients_values varchar)";

        String q3 = "insert into recipes (title, description,steps, drink, imageid, color, timer)\n" +
                "values ('Classic Aviation', \n" +
                "'The Aviation cocktail is a 1900''s mixed drink with a lovely purple hue! This sweet tart classic cocktail is so tasty, it''s now back in style.',\n" +
                "'LOL AINT GOT IT BRO',\n" +
                " 'gin', 'NaN', 'FFFFFF',30)";

        db.execSQL(q1);
        db.execSQL(q2);

        //template first row
        db.execSQL(q3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public CocktailRecipe getRecipePlease() {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM RECIPES";
        Cursor cursor = db.rawQuery(query, null);
        CocktailRecipe recipe = new CocktailRecipe();
        if (cursor.moveToFirst()) {
            recipe.set_id(Integer.parseInt(cursor.getString(0)));
            recipe.set_title(cursor.getString(1));
            recipe.set_description(cursor.getString(2));
            recipe.set_steps(cursor.getString(3));
            recipe.set_drink(cursor.getString(4));
            recipe.set_imageid(cursor.getString(5));
            recipe.set_color(cursor.getString(6));
            recipe.set_timer(Integer.parseInt(cursor.getString(7)));
            cursor.close();
        } else
            recipe = null;

        return recipe;
    }
}
