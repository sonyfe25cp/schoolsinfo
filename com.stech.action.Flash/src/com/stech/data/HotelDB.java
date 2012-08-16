package com.stech.data;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.stech.db.DBOpenHelper;
import com.stech.model.Hotel;
import com.stech.utils.Logger;
import com.stech.utils.Tools;

public class HotelDB {

	private String LOGTAG = "HotelDB";

	private DBOpenHelper dbOpenHelper;
	private SQLiteDatabase db;

	public HotelDB(Context context) {
		dbOpenHelper = new DBOpenHelper(context);
	}

	/**
	 * 更新评分
	 * 
	 * @param res_id
	 * @param score
	 * @return
	 */
	public boolean updateHotelScore(int res_id, float score) {
		return true;
	}

	public boolean insertHotel(Hotel hotel) {
		int id = hotel.getId();
		Hotel old = select(id);
		if (old == null) {
			Logger.i(LOGTAG, "not exist,new ~~");
			insert(hotel);
		} else {
			Logger.i(LOGTAG, "already exist,update ~~");
			update(hotel);
		}
		return true;
	}

	public List<Hotel> selectList(long campusId) {
		db = dbOpenHelper.getWritableDatabase();

		String sql = "select * from " + DBOpenHelper.HOTEL_TABLE_NAME
				+ " where campusid=? ";
		Logger.i(LOGTAG, sql);
		Cursor cursor = db
				.rawQuery(
						sql,
						new String[] { String.valueOf(campusId)});
		List<Hotel> hotelList = getListFromCursor(cursor);
		db.close();
		return hotelList;
	}

	private Hotel select(int id) {
		db = dbOpenHelper.getWritableDatabase();
		String sql = "select * from " + DBOpenHelper.HOTEL_TABLE_NAME
				+ " where _id = ?";
		Cursor cursor = db.rawQuery(sql, new String[] { String.valueOf(id) });
		Hotel hotel = getFromCursor(cursor);
		db.close();
		return hotel;
	}

	private List<Hotel> getListFromCursor(Cursor cursor) {
		List<Hotel> hotelList = new ArrayList<Hotel>();
		Hotel hotel = null;
		while (cursor.moveToNext()) {
			hotel = getHotelFromCursor(cursor);
			hotelList.add(hotel);
			Logger.i(LOGTAG, "hotelList + 1");
		}
		Logger.i(LOGTAG, "hotelList size: " + hotelList.size());
		cursor.close();
		return hotelList;
	}

	/**
	 * 按顺序取出
	 * 
	 * @param cursor
	 * @return
	 */
	private Hotel getFromCursor(Cursor cursor) {
		Hotel hotel = null;
		if (cursor.moveToNext()) {
			hotel = getHotelFromCursor(cursor);
		}
		cursor.close();
		return hotel;
	}

	private Hotel getHotelFromCursor(Cursor cursor) {
		Hotel hotel = new Hotel();
		hotel.setId(cursor.getInt(0));
		hotel.setName(cursor.getString(1));
		hotel.setLocation(cursor.getString(2));
		hotel.setDesc(cursor.getString(3));
		String phoneNum = cursor.getString(4);
		List<String> phoneList = Tools.phoneList(phoneNum);
		hotel.setPhoneList(phoneList);
		hotel.setScore(cursor.getFloat(5));
		hotel.setImgUrl(cursor.getString(6));
		hotel.setRank(cursor.getInt(7));
		hotel.setCampusId(cursor.getInt(8));
		return hotel;
	}

	/**
	 * 插入数据,注意顺序
	 * 
	 * @param hotel
	 * @return
	 */
	private boolean insert(Hotel hotel) {
		db = dbOpenHelper.getWritableDatabase();
		Logger.i(LOGTAG,
				"id:" + hotel.getId() + " --  campusId:" + hotel.getCampusId());

		String sqlInsert = "insert into "
				+ DBOpenHelper.AMUSEMENT_TABLE_NAME
				+ "(_id,name,location,desc,phone,score,imgurl,rank,campusid) "
				+ "values(?,?,?,?,?,?,?,?,?)";

		String phoneNum = Tools.phoneNum(hotel.getPhoneList());

		try {
			db.execSQL(
					sqlInsert,
					new Object[] { hotel.getId(), hotel.getName(),
							hotel.getLocation(), hotel.getDesc(),
							phoneNum, hotel.getScore(),
							hotel.getImgUrl(), hotel.getRank(),
							hotel.getCampusId() });
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.e(LOGTAG, "insert hotel error ");
		} finally {
			db.close();
		}
		return true;
	}

	private boolean update(Hotel hotel) {
		db = dbOpenHelper.getWritableDatabase();
		String sqlUpdate = "update "
				+ DBOpenHelper.RESTAURANT_TABLE_NAME
				+ " set "
				+ "name = ?,location=?,desc=?,phone=?,score=?,imgurl=?,rank=?,campusid=? "
				+ "where _id = ?";
		String phoneNum = Tools.phoneNum(hotel.getPhoneList());
		try {
			db.execSQL(
					sqlUpdate,
					new Object[] { hotel.getName(), hotel.getLocation(),
							hotel.getDesc(),  phoneNum,
							hotel.getScore(), hotel.getImgUrl(),
							hotel.getRank(), hotel.getCampusId(),
							hotel.getId(), });
		} catch (SQLException e) {
			Logger.e(LOGTAG, "update hotel error");
		} finally {
			db.close();
		}
		return true;
	}

}
