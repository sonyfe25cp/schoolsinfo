package com.stech.data;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.stech.db.DBOpenHelper;
import com.stech.model.Traffic;
import com.stech.utils.Logger;

/**
 * @author ChenJie
 *
 * 交通信息
 */
public class TrafficDB {

	private String LOGTAG = "TrafficDB";

	private DBOpenHelper dbOpenHelper;
	private SQLiteDatabase db;

	public TrafficDB(Context context) {
		dbOpenHelper = new DBOpenHelper(context);
	}

	/**
	 * 更新评分
	 * 
	 * @param res_id
	 * @param score
	 * @return
	 */
	public boolean updateTrafficScore(int res_id, float score) {
		return true;
	}

	public boolean insertTraffic(Traffic traffic) {
		int id = traffic.getId();
		// Logger.i(LOGTAG, "id:"+id);
		Traffic old = select(id);
		if (old == null) {
			Logger.i(LOGTAG, "not exist,new ~~");
			insert(traffic);
		} else {
			Logger.i(LOGTAG, "already exist,update ~~");
			update(traffic);
		}
		return true;
	}

	public List<Traffic> selectList(long campusId, int type) {
		db = dbOpenHelper.getWritableDatabase();

		String addSql = "";
		switch (type) {
		case 0:
			addSql = " order by traffictype";
			break;
		case 1:
			addSql = " and traffictype = 1 order by locationtype";
			break;
		case 2:
			addSql = " and traffictype = 2 order by locationtype";
		default:
			break;
		}
		String sql = "select * from " + DBOpenHelper.TRAFFIC_TABLE_NAME
				+ " where campusid=?" + addSql;
		Logger.i(LOGTAG, sql);
		Cursor cursor = db.rawQuery(sql,
				new String[] { String.valueOf(campusId) });
		List<Traffic> restList = getListFromCursor(cursor);
		db.close();
		return restList;
	}

	private Traffic select(int id) {
		db = dbOpenHelper.getWritableDatabase();
		String sql = "select * from " + DBOpenHelper.TRAFFIC_TABLE_NAME
				+ " where _id = ?";
		Cursor cursor = db.rawQuery(sql, new String[] { String.valueOf(id) });
		Traffic traffic = getFromCursor(cursor);
		db.close();
		return traffic;
	}

	private List<Traffic> getListFromCursor(Cursor cursor) {
		List<Traffic> restList = new ArrayList<Traffic>();
		Traffic traffic = null;
		while (cursor.moveToNext()) {
			traffic = getTrafficFromCursor(cursor);
			restList.add(traffic);
			Logger.i(LOGTAG, "trafficList + 1");
		}
		Logger.i(LOGTAG, "trafficList size: " + restList.size());
		cursor.close();
		return restList;
	}

	private Traffic getFromCursor(Cursor cursor) {
		Traffic traffic = null;
		if (cursor.moveToNext()) {
			traffic = getTrafficFromCursor(cursor);
		}
		cursor.close();
		return traffic;
	}
	
	private Traffic getTrafficFromCursor(Cursor cursor) {
		Traffic traffic = new Traffic();
		traffic.setId(cursor.getInt(0));
		traffic.setName(cursor.getString(1));
		traffic.setLocationType(Integer.parseInt(cursor.getString(2)));
		traffic.setStops_store(cursor.getString(3));
		traffic.setTraffictype(Integer.parseInt(cursor.getString(4)));
		traffic.setCampusId(Integer.parseInt(cursor.getString(5)));
		return traffic;
	}

	private boolean insert(Traffic traffic) {
		db = dbOpenHelper.getWritableDatabase();
		Logger.i(LOGTAG, "id:" + traffic.getId() + " --  campusId:"
				+ traffic.getCampusId());

		String sqlInsert = "insert into "
				+ DBOpenHelper.TRAFFIC_TABLE_NAME
				+ "(_id,name,locationtype,stops,traffictype,campusid) "
				+ "values(?,?,?,?,?,?)";

		try {
			db.execSQL(
					sqlInsert,
					new Object[] { traffic.getId(), traffic.getName(),
							traffic.getLocationType(), traffic.getStops_store(),
							traffic.getTraffictype(),traffic.getCampusId() });
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.e(LOGTAG, "insert traffic error ");
		} finally {
			db.close();
		}
		return true;
	}

	private boolean update(Traffic traffic) {
		db = dbOpenHelper.getWritableDatabase();
		String sqlUpdate = "update "
				+ DBOpenHelper.TRAFFIC_TABLE_NAME
				+ " set "
				+ "name = ?,locationtype=?,stops=?,traffictype=?,campusid=? "
				+ "where _id = ?";
		try {
			db.execSQL(
					sqlUpdate,
					new Object[] {traffic.getName(),traffic.getLocationType(), traffic.getStops_store(),
							traffic.getTraffictype(),traffic.getCampusId() , traffic.getId() });
		} catch (SQLException e) {
			Logger.e(LOGTAG, "update traffic error");
		} finally {
			db.close();
		}
		return true;
	}

}
