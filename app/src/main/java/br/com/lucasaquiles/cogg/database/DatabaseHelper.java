package br.com.lucasaquiles.cogg.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import br.com.lucasaquiles.cogg.bean.Pic;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String DB_NAME = "cogg_demo";
	private static final int DB_VERSION = 3;
	
	private static final String TAG_LOG= "apresentacao orm";
	
	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {

		onUpgrade(db, connectionSource, 0, 0);
		try{
			TableUtils.createTable(connectionSource, Pic.class);

			Log.i(TAG_LOG, "criou as tabelas");

		}catch(Exception e){
			Log.e(TAG_LOG, "exception: "+e);
		}
				
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, ConnectionSource connectionSource, int arg2,
			int arg3) {
		// TODO Auto-generated method stub
		
		try{
//
			TableUtils.dropTable(connectionSource, Pic.class, true);
	//		TableUtils.dropTable(connectionSource, Pic.class, arg2);
//			TableUtils.dropTable(connectionSource, Categoria.class, arg2);
		}catch(Exception e){
			Log.e(TAG_LOG, "exception: "+e);
		}
//		

	}
	
	
	
}