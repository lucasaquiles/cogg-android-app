package br.com.lucasaquiles.cogg.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import br.com.lucasaquiles.cogg.bean.ItemPic;
import br.com.lucasaquiles.cogg.bean.Pic;
import br.com.lucasaquiles.cogg.bean.Sketche;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String DB_NAME = "cogg_demo";
	private static final int DB_VERSION = 6;
	
	private static final String TAG_LOG= "dabase_schema";
	
	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {

		onUpgrade(db, connectionSource, 0, 0);
		try{
			TableUtils.createTable(connectionSource, Pic.class);
			TableUtils.createTable(connectionSource, Sketche.class);
			TableUtils.createTable(connectionSource, ItemPic.class);
			Log.i(TAG_LOG, "criou as tabelas");

		}catch(Exception e){
			Log.e(TAG_LOG, "exception: "+e);
		}
				
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, ConnectionSource connectionSource, int arg2,
			int arg3) {

		
		try{

			TableUtils.dropTable(connectionSource, Pic.class, true);
			TableUtils.dropTable(connectionSource, Sketche.class, true);
			TableUtils.dropTable(connectionSource, ItemPic.class, true);

			Log.i(TAG_LOG, "atualizou as tabelas");
		}catch(Exception e){
			Log.e(TAG_LOG, "exception: "+e);
		}
//		

	}
	
	
	
}