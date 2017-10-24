package strathmore.com.sqlitelaba;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by HP250 on 19/10/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "contactsManager";

    private static final String TABLE_CONTACTS = " contacts";
    private static final String TABLE_DRINKS = "drinks";
    private static final String TABLE_EMAIL = "email";

    //contacts table
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";

    //drinks table
    private static final String DRI_ID = "id";
    private static final String DRI_NAME = "name";

    //email table
    private static final String EMA_ID = "id";
    private static final String EMA_NAME = "name";
    private static final String EMA_EMAIL = "email";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = " CREATE TABLE " + TABLE_CONTACTS + "(" + KEY_ID
                + " INTEGER PRIMARY KEY , " + KEY_NAME + " TEXT, " + KEY_PH_NO + " TEXT " + ")";

        String CREATE_DRINKS_TABLE = " CREATE TABLE " + TABLE_DRINKS + "(" + DRI_ID +
                " INTEGER PRIMARY KEY , " + DRI_NAME + " TEXT" + ")";

        String CREATE_EMAIL_TABLE = " CREATE TABLE " + TABLE_EMAIL + "(" + EMA_ID +
                " INTEGER PRIMARY KEY , " + EMA_NAME + " TEXT, " + EMA_EMAIL + " TEXT " + ")";

        db.execSQL(CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_DRINKS_TABLE);
        db.execSQL(CREATE_EMAIL_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_CONTACTS + TABLE_DRINKS + TABLE_EMAIL);
        onCreate(db);
    }

    //C O N T A C T
    //INSERTING NEW ROWS INTO THE CONTACT
    public void addContacts(Contacts contacts) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contacts.getName()); //Contact name
        values.put(KEY_PH_NO, contacts.getPhoneNumber()); // contact phone number

        // Inserting row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); //Closing db connection

    }

    // Getting single contact
    public Contacts getContacts(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID, KEY_NAME, KEY_PH_NO}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Contacts contacts = new Contacts(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));

        //return contacts

        return contacts;
    }

    //Getting all contacts
    public List<Contacts> getAllContacts() {
        List<Contacts> contactsList = new ArrayList<Contacts>();
        //select a query
        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contacts contacts = new Contacts();
                contacts.setId(Integer.parseInt(cursor.getString(0)));
                contacts.setName(cursor.getString(1));
                contacts.setPhoneNumber(cursor.getString(2));
                //Adding contacts list
                contactsList.add(contacts);
            } while (cursor.moveToNext());
        }

        //RETURN CONTACT LIST
        return contactsList;


    }

    //getting contacts count
    public int getContactsCount() {
        String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        //return count
        return cursor.getCount();

    }

    //Updating single contact
    public int updateContacts(Contacts contacts) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contacts.getName());
        values.put(KEY_PH_NO, contacts.getPhoneNumber());

        return db.update(TABLE_CONTACTS, values, KEY_ID + "=?",
                new String[]{String.valueOf(contacts.getId())});

    }

    //Deleting single contact
    public int deleteContacts(Contacts contacts) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contacts.getName());
        values.put(KEY_PH_NO, contacts.getPhoneNumber());

        //updating row

        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(contacts.getId())});


    }



    //D R I N K S
    //INSERTING A NEW VALUE INTO THE DRINKS
    public void addDrinks(Drinks drinks) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DRI_NAME, drinks.getDname()); //drink name

        // Inserting row
        db.insert(TABLE_DRINKS, null, values);
        db.close(); //Closing db connection

    }

    //INSERTING A SINGLE VALUE INTO A DRINKS
    public Drinks getDrinks(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{DRI_ID, DRI_NAME}, DRI_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Drinks drinks = new Drinks(Integer.parseInt(cursor.getString(0)), cursor.getString(1));

        //return contacts

        return drinks;
    }

    //GETTING ALL THE CONTACTS
    public List<Drinks> getAllDrinks(){
        List<Drinks> drinksList = new ArrayList<Drinks>();
        //select a query
        String selectQuery ="SELECT * FROM " + TABLE_DRINKS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if(cursor.moveToFirst()){
            do{
                Drinks drinks = new Drinks();
                drinks.setDId(Integer.parseInt(cursor.getString(0)));
                drinks.setDname(cursor.getString(1));
                //Adding contacts list
                drinksList.add(drinks);
            }while (cursor.moveToNext());
        }

        //RETURN CONTACT LIST
        return drinksList;


    }

    //GETTING DRINKS COUNT
    public int getDribksCount() {
        String countQuery = "SELECT * FROM "+ TABLE_DRINKS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =db.rawQuery(countQuery, null);
        cursor.close();

        //return count
        return cursor.getCount();

    }

    //UPDATING A SINGLE CONTACT
    public int updateDrinks(Drinks drinks) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DRI_NAME, drinks.getDname());

        return db.update(TABLE_DRINKS, values, DRI_ID +"=?",
                new String[] {String.valueOf (drinks.getDId()) });

    }

    //DELETING  A SINGLE CONTACT
    public int deleteDrinks (Drinks drinks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DRI_NAME, drinks.getDname());

        //updating row

        return db.update(TABLE_DRINKS, values, DRI_ID + " = ?",
                new String[] {String.valueOf(drinks.getDId())});


    }


    //E M A I L

    //INSERTING A NEW VALUE INTO THE EMAIL
    public void addEmail(Email email) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EMA_NAME, email.getEname()); //email name
        values.put(EMA_EMAIL, email.getEmailaddress());//email address

        // Inserting row
        db.insert(TABLE_EMAIL, null, values);
        db.close(); //Closing db connection

    }

    //INSERTING A SINGLE VALUE INTO A EMAIL
    public Email getEmail(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_EMAIL, new String[]{EMA_ID,EMA_NAME, EMA_EMAIL}, EMA_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Email email = new Email(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));

        //return contacts

        return email;
    }

    //GETTING ALL THE EMAILS
    public List<Email> getAllEmail(){
        List<Email> emailList = new ArrayList<Email>();
        //select a query
        String selectQuery ="SELECT * FROM " + TABLE_EMAIL;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if(cursor.moveToFirst()){
            do{
                Email email = new Email();
                email.setEid(Integer.parseInt(cursor.getString(0)));
                email.setEname(cursor.getString(1));
                email.setEmailaddress(cursor.getString(2));
                //Adding contacts list
                emailList.add(email);
            }while (cursor.moveToNext());
        }

        //RETURN CONTACT LIST
        return emailList;


    }

    //GETTING EMAIL COUNT
    public int getEmailCount() {
        String countQuery = "SELECT * FROM "+ TABLE_EMAIL;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =db.rawQuery(countQuery, null);
        cursor.close();

        //return count
        return cursor.getCount();

    }

    //UPDATING A SINGLE EMAIL
    public int updateEmail(Email email) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EMA_NAME, email.getEname());
        values.put(EMA_EMAIL, email.getEmailaddress());

        return db.update(TABLE_EMAIL, values, EMA_ID +"=?",
                new String[] {String.valueOf (email.getEid()) });

    }

    //DELETING  A SINGLE EMAIL
    public int deleteEmail (Email email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EMA_NAME, email.getEname());
        values.put(EMA_EMAIL, email.getEmailaddress());

        //updating row

        return db.update(TABLE_EMAIL, values, EMA_ID + " = ?",
                new String[] {String.valueOf(email.getEid())});


    }

}