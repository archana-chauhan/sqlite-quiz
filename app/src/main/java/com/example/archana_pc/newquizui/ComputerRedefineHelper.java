package com.example.archana_pc.newquizui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;



class ComputerRedefineHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DB_NAME = "TQuiz.db";

    //If you want to add more questions or wanna update table values
    //or any kind of modification in db just increment version no.
    private static final int DB_VERSION = 4;
    //Table name
    private static final String TABLE_NAME = "TQ";
    private static final String TABLE_NAME_1 = "Class1";

    //Id of question
    private static final String UID = "_UID";

    private static final String UID_1 = "_UID_1";

    //Question
    private static final String QUESTION = "QUESTION";

    private static final String QUESTION_1 = "QUESTION_1";

    //Option A
    private static final String OPTA = "OPTA";
    //Option B
    private static final String OPTB = "OPTB";
    //Option C
    private static final String OPTC = "OPTC";
    //Option D
    private static final String OPTD = "OPTD";
    //Answer
    private static final String ANSWER = "ANSWER";

    //Option A
    private static final String OPTA_1 = "OPTA_1";
    //Option B
    private static final String OPTB_1 = "OPTB_1";
    //Option C
    private static final String OPTC_1 = "OPTC_1";
    //Option D
    private static final String OPTD_1 = "OPTD_1";
    //Answer
    private static final String ANSWER_1 = "ANSWER_1";




    //So basically we are now creating table with first column-id , sec column-question , third column -option A, fourth column -option B , Fifth column -option C , sixth column -option D , seventh column - answer(i.e ans of  question)
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + UID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + QUESTION + " VARCHAR(255), " + OPTA + " VARCHAR(255), " + OPTB + " VARCHAR(255), " + OPTC + " VARCHAR(255), " + OPTD + " VARCHAR(255), " + ANSWER + " VARCHAR(255));";
    private static final String CREATE_TABLE_1 = "CREATE TABLE " + TABLE_NAME_1 + " ( " + UID_1 + " INTEGER PRIMARY KEY AUTOINCREMENT , " + QUESTION_1 + " VARCHAR(255), " + OPTA_1 + " VARCHAR(255), " + OPTB_1 + " VARCHAR(255), " + OPTC_1 + " VARCHAR(255), " + OPTD_1 + " VARCHAR(255), " + ANSWER_1 + " VARCHAR(255));";


    //Drop table query
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private static final String DROP_TABLE_1 = "DROP TABLE IF EXISTS " + TABLE_NAME_1;


    ComputerRedefineHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //OnCreate is called only once
        sqLiteDatabase.execSQL(CREATE_TABLE);
        sqLiteDatabase.execSQL(CREATE_TABLE_1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //OnUpgrade is called when ever we upgrade or increment our database version no
        sqLiteDatabase.execSQL(DROP_TABLE);
        sqLiteDatabase.execSQL(DROP_TABLE_1);

        onCreate(sqLiteDatabase);
    }

    void allQuestion() {
        ArrayList<ComputerRedefineQuestions> arraylist = new ArrayList<>();

        arraylist.add(new ComputerRedefineQuestions("1" , "Galileo was an Italian astronomer who developed?", "Telescope", "Airoplane", "Electricity", "Train", "Telescope"));

        arraylist.add(new ComputerRedefineQuestions("2" , "Who is the father of Geometry ?", "Aristotle", "Euclid", "Pythagoras", "Kepler", "Euclid"));

        arraylist.add(new ComputerRedefineQuestions("3", "Who was known as Iron man of India ?", "Govind Ballabh Pant", "Jawaharlal Nehru", "Subhash Chandra Bose", "Sardar Vallabhbhai Patel", "Sardar Vallabhbhai Patel"));

        arraylist.add(new ComputerRedefineQuestions("4", "The first woman in space was ?", "Valentina Tereshkova", "Sally Ride", "Naidia Comenci", "Tamara Press", "Valentina Tereshkova"));

        arraylist.add(new ComputerRedefineQuestions("5", "Who is the Flying Sikh of India ?", "Mohinder Singh", "Joginder Singh", "Ajit Pal Singh", "Milkha singh", "Milkha singh"));

        arraylist.add(new ComputerRedefineQuestions("6", "The Indian to beat the computers in mathematical wizardry is", "Ramanujam", "Rina Panigrahi", "Raja Ramanna", "Shakunthala Devi", "Shakunthala Devi"));

        arraylist.add(new ComputerRedefineQuestions("7", "Who is Larry Pressler ?", "Politician", "Painter", "Actor", "Tennis player", "Politician"));

        arraylist.add(new ComputerRedefineQuestions("8","Michael Jackson is a distinguished person in the field of ?", "Pop Music", "Jounalism", "Sports", "Acting", "Pop Music"));

        arraylist.add(new ComputerRedefineQuestions("20","the Founder of the most famous gaming platform steam is ?", "Bill Cliton", "Bill Williams", "Gabe Newell", "Bill Gates", "Gabe Newell"));

        this.addAllQuestions(arraylist);

    }


    void allQuestion_1() {
        ArrayList<ComputerRedefineQuestions1> arraylist = new ArrayList<>();

        arraylist.add(new ComputerRedefineQuestions1("1" , "What type of animal is a seahorse?", "Crustacean", "Arachnid", "Fish", "Shell", "Crustacean"));

        arraylist.add(new ComputerRedefineQuestions1("2" , "Which of the following dogs is the smallest?", "Dachshund", "Poodle", "Pomeranian", "Chihuahua", "Chihuahua"));

        arraylist.add(new ComputerRedefineQuestions1("3", "What color are zebras?", "White with black stripes", "Black with white stripes", "Both of the above", "None of the above", "Black with white stripes"));


        this.addAllQuestions_1(arraylist);

    }


    private void addAllQuestions(ArrayList<ComputerRedefineQuestions> allQuestions) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (ComputerRedefineQuestions question : allQuestions) {
                values.put(UID, question.getId());
                values.put(QUESTION, question.getQuestion());
                values.put(OPTA, question.getOptA());
                values.put(OPTB, question.getOptB());
                values.put(OPTC, question.getOptC());
                values.put(OPTD, question.getOptD());
                values.put(ANSWER, question.getAnswer());
                db.insert(TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }


    private void addAllQuestions_1(ArrayList<ComputerRedefineQuestions1> allQuestions) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (ComputerRedefineQuestions1 question : allQuestions) {
                values.put(UID_1, question.getId());
                values.put(QUESTION_1, question.getQuestion());
                values.put(OPTA_1, question.getOptA());
                values.put(OPTB_1, question.getOptB());
                values.put(OPTC_1, question.getOptC());
                values.put(OPTD_1, question.getOptD());
                values.put(ANSWER_1, question.getAnswer());
                db.insert(TABLE_NAME_1, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    List<ComputerRedefineQuestions> getAllOfTheQuestions() {

        List<ComputerRedefineQuestions> questionsList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        String coloumn[] = {UID, QUESTION, OPTA, OPTB, OPTC, OPTD, ANSWER};
        Cursor cursor = db.query(TABLE_NAME, coloumn, null, null, null, null, null);


        while (cursor.moveToNext()) {
            ComputerRedefineQuestions question = new ComputerRedefineQuestions();
            question.setId(cursor.getString(0));
            question.setQuestion(cursor.getString(1));
            question.setOptA(cursor.getString(2));
            question.setOptB(cursor.getString(3));
            question.setOptC(cursor.getString(4));
            question.setOptD(cursor.getString(5));
            question.setAnswer(cursor.getString(6));
            questionsList.add(question);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        db.close();
        return questionsList;
    }

    List<ComputerRedefineQuestions1> getAllOfTheQuestions_1() {

        List<ComputerRedefineQuestions1> questionsList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        String coloumn[] = {UID_1, QUESTION_1, OPTA_1, OPTB_1, OPTC_1, OPTD_1, ANSWER_1};
        Cursor cursor = db.query(TABLE_NAME_1, coloumn, null, null, null, null, null);


        while (cursor.moveToNext()) {
            ComputerRedefineQuestions1 question = new ComputerRedefineQuestions1();
            question.setId(cursor.getString(0));
            question.setQuestion(cursor.getString(1));
            question.setOptA(cursor.getString(2));
            question.setOptB(cursor.getString(3));
            question.setOptC(cursor.getString(4));
            question.setOptD(cursor.getString(5));
            question.setAnswer(cursor.getString(6));
            questionsList.add(question);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        db.close();
        return questionsList;
    }

}
