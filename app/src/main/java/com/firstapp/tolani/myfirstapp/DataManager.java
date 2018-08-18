package com.firstapp.tolani.myfirstapp;


import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
import android.widget.Toast;

import java.util.ArrayList;


public class DataManager
{
    private SQLiteDatabase db;
    DatabaseHelper helper;
    String db_name;



    DataManager(Context context, String n) {

        helper = new DatabaseHelper(context,n);
        db_name = n;

    }




    ArrayList<String> getAllTables()
    {
        ArrayList<String> arrTblNames = new ArrayList<>();
        db = helper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        if (c.moveToFirst()) {
            while ( !c.isAfterLast() ) {
                arrTblNames.add( c.getString( c.getColumnIndex("name")) );
                c.moveToNext();
            }
        }
        c.close();

        return arrTblNames;
    }

     boolean createTab(String table, String [] colNames, String [] colTypes, Context context, int cLength) {

        String [] tNames = helper.getTableNames();
        int tbSize;
        String tempName;

        tbSize = cLength;

        int i;

        int flag=0;
        for(i=0; i<tbSize; ++i)
        {
            tempName = tNames[i];
            if(table.equals(tempName))
            {
                Toast.makeText(context,"This table already exists!!",Toast.LENGTH_SHORT).show();
                break;
            }
            else
            {
                helper.createTable(table,colTypes,colNames,cLength);
                flag=1;
                break;
            }
        }

        if(flag==1)
        {
            tbSize++;
            tNames[tbSize-1]=table;
            helper.setTableNames(tNames);
        }
        else
        {
            return false;
        }

    return true;
    }

    boolean insertRecord(String table, String [] colData, int col){

        boolean isInserted;

        isInserted = helper.insertRec(colData,table,col);

        if(isInserted)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    boolean deleteRecord(String name, String table, String col)
    {
        boolean isDeleted;

        isDeleted = helper.deleteRec(name,table,col);
        if(isDeleted) {
            return true;
        }
        else
        {
            return false;
        }
    }

    public String [] selectAllRows(String tab){
        Cursor cur = helper.selectAll(tab);
        String [] rowData = {""};
        int i,j;
        int rLen = cur.getCount();
        int cLen = cur.getColumnCount();

        for(cur.moveToFirst(),i=0;!cur.isAfterLast() && i<rLen;cur.moveToNext(),i++)
        {
            for(j=0;j<cLen;++j)
            {
                rowData[i] = rowData[i]+ cur.getString(j)+"   ";
            }
        }
        return rowData;
    }

    public String [] getColData(String table)
    {
        db = helper.getWritableDatabase();
        Cursor cr = db.query(table,null,null,null,null,null,null);
        String [] colData = cr.getColumnNames();
        cr.close();
        return colData;

    }

    public boolean deleteRow(String tab)
    {
        boolean isDeleted;

        isDeleted = helper.deleteTab(tab);

        if(isDeleted) {return true;}
        else
        {return false;}
    }

    public void searchRecord(String v,String tab, String [] colData, String col, int colNo)
    {
        Cursor cur = helper.searchRec(v,tab,colData,col,colNo);


    }

     String[] searchAllRows(String tab, String fld, String val)
    {
        int i;
        Cursor cur = null;
        String [] rows = {""};

        String [] tabNam = helper.getTableNames();
        String [] colNam = helper.getColumnNames();
        int tLen = tabNam.length;
        int cLen = colNam.length;

        for(i=0;i<tLen && i<cLen;++i)
        {
            if(tabNam[i]==tab && colNam[i]==fld)
            {
                cur = helper.searchAll(val,tab,fld);
                break;
            }
        }

        int rLen = cur.getCount();
        int colLen = cur.getColumnCount();
        int j=0;

        for(cur.moveToFirst(),i=0;!cur.isAfterLast() && i<rLen;cur.moveToNext(),i++)
        {
            while(j<colLen) {
                rows[i] = rows[i]+ cur.getString(j);
            }
        }

        return rows;
    }

     boolean updateRecord(String tab,String f, String v)
    {
        boolean isUpdated;

        isUpdated = helper.updateRec(tab,f,v);

        if(isUpdated) {return true;}
        else
        {return false;}
    }

    boolean checkTableExists(String name)
    {
        String [] tabNames;
        tabNames = helper.getTableNames();

        int count=0;

        for(int i=0;i<50;++i)
        {
            if(tabNames[i]!=null) {count++; }
        }
        int size = count;

        for(int j=0;j<size;++j)
        {
            if(name.equals(tabNames[j]))
            {
                return true;
            }
        }
        return false;
    }


    public String getDb_name() { return db_name; }

     void deleteDB(String n,Context context)
    {
        context.deleteDatabase(n);
    }

    private class DatabaseHelper extends SQLiteOpenHelper {

         String [] columnNames = new String[50];
         String [] columnTypes = new String[50];
        String [] tableNames = new String[50];
        private static final int DB_VERSION = 1;

         DatabaseHelper(Context context, String name) {
            super(context, name, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {}

        @Override
        public void onUpgrade(SQLiteDatabase db,
                              int oldVersion, int newVersion) {
        }

        public void setColumnNames(String [] cn)
        {
            columnNames = cn;
        }


        public void setColumnTypes(String [] ct)
        {
            columnTypes = ct;
        }
        String [] getTableNames() { return tableNames;}
        void setTableNames(String [] tn)
        {
            tableNames = tn;
        }

         String [] getColumnNames() { return columnNames;}
        public String [] getColumnTypes() { return columnTypes;}

         boolean createTable(String tbnam, String [] ctypes, String [] cnames, int coLength)
        {
            String create_query = "CREATE TABLE " + tbnam + "(";
            int i;
            db = helper.getWritableDatabase();

            for(i=0;i<coLength;++i)
            {
                create_query = create_query + cnames[i] + " " + ctypes[i] + " ";
            }

            create_query = create_query + ");";
            db.execSQL(create_query);
            return true;
        }

        public boolean insertRec(String [] recordInfo, String table_name, int col){

            int i;
            String temp;

            for(int l=0;l<col;++l)
            {
                temp = recordInfo[l];
                recordInfo[l] = "'" + temp + "'";
            }

            String colQuery="";

            for(i=0;i<col;++i)
            {
                if(i==col-1)
                {
                    colQuery = colQuery + recordInfo[i];
                }
                else
                {
                    colQuery = colQuery + recordInfo[i] + ",";
                }
            }



            String query = "INSERT INTO " + table_name + " VALUES (" + colQuery + ");" ;
            db.execSQL(query);
            return true;
        }

        public boolean deleteRec(String name, String table_name, String column){

            String query = "DELETE FROM " + table_name +
                    " WHERE " + column +
                    " = " + name + ";";

            db.execSQL(query);
            return true;
        }

        public Cursor selectAll(String table_name) {
            db = getWritableDatabase();
            Cursor c = db.rawQuery("SELECT *" +" FROM " +
                    table_name, null);
            return c;
        }

        public boolean deleteTab(String tab)
        {
            db = getWritableDatabase();
            String query = "DROP TABLE " + tab + ";";
            db.execSQL(query);
            return true;
        }

        public Cursor searchRec(String val, String table_name, String [] viewRec, String column, int col) {

            int i;
            String colData="";
            db = getWritableDatabase();

            for(i=0;i<col;++i)
            {
                colData = colData + viewRec[i] + ",";
            }

            String query = "SELECT " + colData  + " FROM " + table_name + " WHERE " + column + " = " + val + ";";

            Cursor c = db.rawQuery(query, null);
            return c;
        }

        public Cursor searchAll(String val, String table_name, String column) {

            String query = "SELECT * "  + " FROM " + table_name + " WHERE " + column + " = " + val + ";";
            db=getWritableDatabase();
            Cursor c = db.rawQuery(query, null);
            return c;
        }

        public boolean updateRec (String tableNam ,String field, String value)
        {
            db = getWritableDatabase();
            String query = "UPDATE " + tableNam + " SET " + field + " = " + value + ";";
            db.execSQL(query);
            return true;
        }
    }
}
