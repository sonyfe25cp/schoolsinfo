package com.stech.data;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.stech.db.DBOpenHelper;
import com.stech.model.Amusement;
import com.stech.utils.Logger;
import com.stech.utils.Tools;

public class AmusementDB {

	private String LOGTAG = "AmusementDB";

	private DBOpenHelper dbOpenHelper;
	private SQLiteDatabase db;

	public AmusementDB(Context context) {
		dbOpenHelper = new DBOpenHelper(context);
	}
	
	/**
	 * 更新评分
	 * @param res_id
	 * @param score
	 * @return
	 */
	public boolean updateAmusementScore(int res_id,float score){
		return true;
	}
	
	public boolean insertAmusement(Amusement amusement) {
		int id = amusement.getId();
		Amusement old = select(id);
		if (old == null) {
			Logger.i(LOGTAG, "not exist,new ~~");
			insert(amusement);
		} else {
			Logger.i(LOGTAG, "already exist,update ~~");
			update(amusement);
		}
		return true;
	}

	public List<Amusement> selectList(long campusId, int type) {
		db = dbOpenHelper.getWritableDatabase();

		String sql = "select * from " + DBOpenHelper.AMUSEMENT_TABLE_NAME
				+ " where campusid=? and amusetype= ? ";
		Logger.i(LOGTAG, sql);
		Cursor cursor = db.rawQuery(sql,
				new String[] { String.valueOf(campusId),String.valueOf(type)});
		List<Amusement> restList = getListFromCursor(cursor);
		db.close();
		return restList;
	}

	private Amusement select(int id) {
		db = dbOpenHelper.getWritableDatabase();
		String sql = "select * from " + DBOpenHelper.AMUSEMENT_TABLE_NAME
				+ " where _id = ?";
		Cursor cursor = db.rawQuery(sql, new String[] { String.valueOf(id) });
		Amusement amusement = getFromCursor(cursor);
		db.close();
		return amusement;
	}

	private List<Amusement> getListFromCursor(Cursor cursor) {
		List<Amusement> restList = new ArrayList<Amusement>();
		Amusement amusement = null;
		while (cursor.moveToNext()) {
			amusement = getAmusementFromCursor(cursor);
			restList.add(amusement);
			Logger.i(LOGTAG, "restList + 1");
		}
		Logger.i(LOGTAG, "restList size: " + restList.size());
		cursor.close();
		return restList;
	}

	/**
	 * 按顺序取出
	 * @param cursor
	 * @return
	 */
	private Amusement getFromCursor(Cursor cursor) {
		Amusement amusement = null;
		if (cursor.moveToNext()) {
			amusement=getAmusementFromCursor(cursor);
		}
		cursor.close();
		return amusement;
	}

	private Amusement getAmusementFromCursor(Cursor cursor){
		Amusement amusement = new Amusement();
		amusement.setId(cursor.getInt(0));
		amusement.setName(cursor.getString(1));
		amusement.setLocation(cursor.getString(2));
		amusement.setDesc(cursor.getString(3));
		amusement.setAmusetype(cursor.getInt(4));
		String phoneNum = cursor.getString(5);
		List<String> phoneList = Tools.phoneList(phoneNum);
		amusement.setPhoneList(phoneList);
		amusement.setScore(cursor.getFloat(6));
		amusement.setImgUrl(cursor.getString(7));
		amusement.setRank(cursor.getInt(8));
		amusement.setCampusId(cursor.getInt(9));
		return amusement;
	}
	
	/**
	 * 插入数据,注意顺序
	 * @param amusement
	 * @return
	 */
	private boolean insert(Amusement amusement) {
		db = dbOpenHelper.getWritableDatabase();
		Logger.i(LOGTAG, "id:" + amusement.getId() + " --  campusId:" + amusement.getCampusId());

		String sqlInsert = "insert into "
				+ DBOpenHelper.AMUSEMENT_TABLE_NAME
				+ "(_id,name,location,desc,amusetype,phone,score,imgurl,rank,campusid) "
				+ "values(?,?,?,?,?,?,?,?,?,?)";

		String phoneNum = Tools.phoneNum(amusement.getPhoneList());

		try {
			db.execSQL(
					sqlInsert,
					new Object[] { amusement.getId(), amusement.getName(),
							amusement.getLocation(), amusement.getDesc(),
							amusement.getAmusetype(), phoneNum,
							amusement.getScore(), amusement.getImgUrl(),
							amusement.getRank(),
							amusement.getCampusId() });
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.e(LOGTAG, "insert amusement error ");
		} finally {
			db.close();
		}
		return true;
	}

	private boolean update(Amusement amusement) {
		db = dbOpenHelper.getWritableDatabase();
		String sqlUpdate = "update "
				+ DBOpenHelper.AMUSEMENT_TABLE_NAME
				+ " set "
				+ "name = ?,location=?,desc=?,amusetype=?,phone=?,score=?,imgurl=?,rank=?,campusid=? "
				+ "where _id = ?";
		String phoneNum = Tools.phoneNum(amusement.getPhoneList());
		try {
			db.execSQL(
					sqlUpdate,
					new Object[] { amusement.getName(),
							amusement.getLocation(), amusement.getDesc(),
							amusement.getAmusetype(), phoneNum,
							amusement.getScore(), amusement.getImgUrl(),
							amusement.getRank(), amusement.getCampusId(),
							amusement.getId(), });
		} catch (SQLException e) {
			Logger.e(LOGTAG, "update amusement error");
		} finally {
			db.close();
		}
		return true;
	}

	
}
