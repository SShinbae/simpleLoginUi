//package com.example.viewsanduielement;
//
//import android.content.Context;
//
//import androidx.room.Database;
//import androidx.room.Room;
//import androidx.room.RoomDatabase;
//
//public class AppDatabase {
//    @Database(entities ={Expense.class}, version = 1,exportSchema= true)
//    public abstract class AppDatabase extends RoomDatabase{
//        private static volatile AppDatabase INSTANCE;
//
//        public abstract ExpenseDAO expenseDAO();
//
//        public static AppDatabase getDatabase(final Context context){
//            if (INSTANCE == null)
//                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class, "db_expense").setJournalMode(JournalMode.TRUNCATE).fallbackToDestructiveMigration().build();
//        }
//    }
//}
//
