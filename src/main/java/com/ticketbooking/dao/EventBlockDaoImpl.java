package com.ticketbooking.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ticketbooking.beans.EventSeatBlockDtl;

@Repository
public class EventBlockDaoImpl implements EventBlockDao {

	@Autowired
	JdbcTemplate template;

	@Override
	public int upsert(EventSeatBlockDtl eventSeatBlock) {

		String sql = "REPLACE into EventBlockTbl(GameEventId,UserNumber,UserName,SoftBlockingDate,ConfirmationDate,TotalSeatBooked, CanecelledSeatCount) values('"
				+ eventSeatBlock.getGameEventId() + "','" + eventSeatBlock.getUserId() + "','"
				+ eventSeatBlock.getUserName() + "','" + eventSeatBlock.getSoftBlockingDate() + "','"
				+ eventSeatBlock.getConfirmationDate() + "','" + eventSeatBlock.getTotalSeatBooked() + "','"
				+ eventSeatBlock.getCancelledSeatCount() + "')";
		try {
			return template.update(sql);
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int updateCancellationSeat(EventSeatBlockDtl eventSeatBlock) {

		String sql = "update EventBlockTbl set SeatCancelledDate='" + eventSeatBlock.getSeatCancelledDate()
				+ "', CanecelledSeatCount='" + eventSeatBlock.getCancelledSeatCount() + "', TotalSeatBooked='"
				+ eventSeatBlock.getTotalSeatBooked() + "'where GameEventId='" + eventSeatBlock.getGameEventId()
				+ "' and UserNumber='" + eventSeatBlock.getUserId() + "'";
		try {
			return template.update(sql);
		} catch (Exception e) {
			return 0;
		}
	}

	public int getBookedSeatCount(EventSeatBlockDtl eventSeatBlock) {
		String sql = "SELECT TotalSeatBooked FROM EventBlockTbl where GameEventId='" + eventSeatBlock.getGameEventId()
				+ "' and UserNumber='" + eventSeatBlock.getUserId() + "'";
		try {
			return template.queryForObject(sql, new Object[] {}, new RowMapper<Integer>() {
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getInt(1);
				}
			});
		} catch (Exception e) {
			return 0;
		}

	}

	public int getCancelledSeatCount(EventSeatBlockDtl eventSeatBlock) {
		String sql = "SELECT CanecelledSeatCount FROM EventBlockTbl where GameEventId='"
				+ eventSeatBlock.getGameEventId() + "' and UserNumber='" + eventSeatBlock.getUserId() + "'";
		try {
			return template.queryForObject(sql, new Object[] {}, new RowMapper<Integer>() {
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getInt(1);
				}
			});
		} catch (Exception e) {
			return 0;
		}

	}

	@Override
	public int checkCustomerInfo(String id) {
		String sql = "SELECT count(*) FROM EventBlockTbl where UserNumber='" + id + "'";
		try {
			return template.queryForObject(sql, new Object[] {}, new RowMapper<Integer>() {
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getInt(1);
				}
			});
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int checkUserBlockedForEvent(EventSeatBlockDtl eventSeatBlock) {
		String sql = "SELECT TotalSeatBooked FROM EventBlockTbl where GameEventId='" + eventSeatBlock.getGameEventId()
				+ "' and UserNumber='" + eventSeatBlock.getUserId() + "'";
		try {
			return template.queryForObject(sql, new Object[] {}, new RowMapper<Integer>() {
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getInt(1);
				}
			});
		} catch (Exception e) {
			return 0;
		}
	}

}
