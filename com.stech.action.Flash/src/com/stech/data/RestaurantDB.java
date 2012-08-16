package com.stech.data;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.stech.db.DBOpenHelper;
import com.stech.model.Restaurant;
import com.stech.utils.Logger;
import com.stech.utils.Tools;

public class RestaurantDB {

	private String LOGTAG = "RestaurantDB";

	private DBOpenHelper dbOpenHelper;
	private SQLiteDatabase db;

	public RestaurantDB(Context context) {
		dbOpenHelper = new DBOpenHelper(context);
	}
	
	/**
	 * 更新评分
	 * @param res_id
	 * @param score
	 * @return
	 */
	public boolean updateRestaurantScore(int res_id,float score){
		return true;
	}
	
	public boolean insertRestaurant(Restaurant restaurant) {
		int id = restaurant.getId();
		// Logger.i(LOGTAG, "id:"+id);
		Restaurant old = select(id);
		if (old == null) {
			Logger.i(LOGTAG, "not exist,new ~~");
			insert(restaurant);
		} else {
			Logger.i(LOGTAG, "already exist,update ~~");
			update(restaurant);
		}
		return true;
	}

	public List<Restaurant> selectList(long campusId, int type) {
		db = dbOpenHelper.getWritableDatabase();

		String addSql = "";
		switch (type) {
		case 1:
			addSql = " and issale = 1 ";
			break;
		case 2:
			addSql = " and isrest = 1";
			break;
		case 3:
			addSql = " and isshitang = 1";
		default:
			break;
		}
		String sql = "select * from " + DBOpenHelper.RESTAURANT_TABLE_NAME
				+ " where campusid=?" + addSql;
		Logger.i(LOGTAG, sql);
		Cursor cursor = db.rawQuery(sql,
				new String[] { String.valueOf(campusId) });
		List<Restaurant> restList = getListFromCursor(cursor);
		db.close();
		return restList;
	}

	private Restaurant select(int id) {
		db = dbOpenHelper.getWritableDatabase();
		String sql = "select * from " + DBOpenHelper.RESTAURANT_TABLE_NAME
				+ " where _id = ?";
		Cursor cursor = db.rawQuery(sql, new String[] { String.valueOf(id) });
		Restaurant restaurant = getFromCursor(cursor);
		db.close();
		return restaurant;
	}

	private List<Restaurant> getListFromCursor(Cursor cursor) {
		List<Restaurant> restList = new ArrayList<Restaurant>();
		Restaurant restaurant = null;
		while (cursor.moveToNext()) {
			restaurant = new Restaurant();
			restaurant.setId(cursor.getInt(0));
			restaurant.setName(cursor.getString(1));
			restaurant.setLocation(cursor.getString(2));
			restaurant.setDesc(cursor.getString(3));
			// restaurant.setType(cursor.getInt(4));
			String phoneNum = cursor.getString(7);
			List<String> phoneList = Tools.phoneList(phoneNum);
			restaurant.setPhoneList(phoneList);
			restaurant.setScore(cursor.getFloat(8));
			restaurant.setImgUrl(cursor.getString(9));
			restaurant.setMenuUrl(cursor.getString(10));
			restaurant.setRank(cursor.getInt(11));
			restList.add(restaurant);
			Logger.i(LOGTAG, "restList + 1");
		}
		Logger.i(LOGTAG, "restList size: " + restList.size());
		cursor.close();
		return restList;
	}

	private Restaurant getFromCursor(Cursor cursor) {
		Restaurant restaurant = null;
		if (cursor.moveToNext()) {
			restaurant = new Restaurant();
			restaurant.setId(cursor.getInt(0));
			restaurant.setName(cursor.getString(1));
			restaurant.setLocation(cursor.getString(2));
			restaurant.setDesc(cursor.getString(3));
			// restaurant.setType(cursor.getInt(4));
			String phoneNum = cursor.getString(7);
			List<String> phoneList = Tools.phoneList(phoneNum);
			restaurant.setPhoneList(phoneList);
			restaurant.setScore(cursor.getFloat(8));
			restaurant.setImgUrl(cursor.getString(9));
			restaurant.setMenuUrl(cursor.getString(10));
			restaurant.setRank(cursor.getInt(11));
		}
		cursor.close();
		return restaurant;
	}

	private boolean insert(Restaurant restaurant) {
		db = dbOpenHelper.getWritableDatabase();
		Logger.i(LOGTAG, "id:" + restaurant.getId() + " --  campusId:"
				+ restaurant.getCampusId());

		// Logger.i(LOGTAG, "issale:"+restaurant.getIsSale());
		// Logger.i(LOGTAG, "isrest:"+restaurant.getIsRest());
		// Logger.i(LOGTAG, "isshitang:"+restaurant.getIsShiTang());

		String sqlInsert = "insert into "
				+ DBOpenHelper.RESTAURANT_TABLE_NAME
				+ "(_id,name,location,desc,issale,isrest,isshitang,phone,score,imgurl,menuurl,rank,campusid) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

		String phoneNum = Tools.phoneNum(restaurant.getPhoneList());

		try {
			db.execSQL(
					sqlInsert,
					new Object[] { restaurant.getId(), restaurant.getName(),
							restaurant.getLocation(), restaurant.getDesc(),
							restaurant.getIsSale(), restaurant.getIsRest(),
							restaurant.getIsShiTang(), phoneNum,
							restaurant.getScore(), restaurant.getImgUrl(),
							restaurant.getMenuUrl(),
							restaurant.getRank(),
							restaurant.getCampusId() });
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.e(LOGTAG, "insert rest error ");
		} finally {
			db.close();
		}
		return true;
	}

	private boolean update(Restaurant restaurant) {
		db = dbOpenHelper.getWritableDatabase();
		String sqlUpdate = "update "
				+ DBOpenHelper.RESTAURANT_TABLE_NAME
				+ " set "
				+ "name = ?,location=?,desc=?,issale=?,isrest=?,isshitang=?,phone=?,score=?,imgurl=?,menuurl=?,rank=?,campusid=? "
				+ "where _id = ?";
		String phoneNum = Tools.phoneNum(restaurant.getPhoneList());
		try {
			db.execSQL(
					sqlUpdate,
					new Object[] { restaurant.getName(),
							restaurant.getLocation(), restaurant.getDesc(),
							restaurant.getIsSale(), restaurant.getIsRest(),
							restaurant.getIsShiTang(), phoneNum,
							restaurant.getScore(), restaurant.getImgUrl(),
							restaurant.getMenuUrl(),
							restaurant.getRank(), 
							restaurant.getCampusId(),restaurant.getId() });
		} catch (SQLException e) {
			Logger.e(LOGTAG, "update rest error");
		} finally {
			db.close();
		}
		return true;
	}

	
}
