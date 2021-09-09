package com.ticketbooking.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ticketbooking.beans.Event;
import com.ticketbooking.beans.EventSeatBlockDtl;

@Repository
public class EventDaoImpl implements EventDao {

	@Autowired
	JdbcTemplate template;

	@Override
	public String getMaxId() {
		try {
			String sql = "SELECT COALESCE(max(CAST(GameEventId AS UNSIGNED)),0) FROM GameEventTbl";
			return template.queryForObject(sql, new Object[] {}, new RowMapper<String>() {
				@Override
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getString(1);
				}

			});
		} catch (Exception e) {
			return "";
		}
	}

	@Override
	public int saveEvent(Event event) {
		String sql = "insert into GameEventTbl values('" + event.getGameEventId() + "','" + event.getGameEventName()
				+ "','" + event.getShowDate() + "','" + event.getShowStatus() + "','" + event.getBookingStatus() + "','"
				+ event.getNumberOfSeats() + "')";
		try {
			return template.update(sql);
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public List<Event> getEventList() {
		String sql = "select * from GameEventTbl";
		try {
			return template.query(sql, new RowMapper<Event>() {
				public Event mapRow(ResultSet rs, int row) throws SQLException {
					Event event = new Event();
					event.setGameEventId(rs.getString("GameEventId"));
					event.setGameEventName(rs.getString("GameEventName"));
					event.setShowDate(rs.getString("ShowDate"));
					event.setShowStatus(rs.getString("ShowStatus"));
					event.setNumberOfSeats(rs.getString("NumberOfSeats"));
					return event;
				}
			});
		} catch (Exception e) {
			return new ArrayList<Event>();
		}

	}

	@Override
	public Event getEventById(String id) {
		String sql = "select * from GameEventTbl where GameEventId=?";
		try {
			return template.queryForObject(sql, new RowMapper<Event>() {
				public Event mapRow(ResultSet rs, int row) throws SQLException {
					Event event = new Event();
					event.setGameEventId(rs.getString("GameEventId"));
					event.setGameEventName(rs.getString("GameEventName"));
					event.setShowDate(rs.getString("ShowDate"));
					event.setShowStatus(rs.getString("ShowStatus"));
					event.setNumberOfSeats(rs.getString("NumberOfSeats"));
					return event;
				}
			}, id);
		} catch (Exception e) {
			return new Event();
		}

	}

	@Override
	public int updateEventById(Event event) {
		String sql = "update GameEventTbl set GameEventName='" + event.getGameEventName() + "', ShowDate='"
				+ event.getShowDate() + "',NumberOfSeats='" + event.getNumberOfSeats() + "' where GameEventId='"
				+ event.getGameEventId() + "'";
		try {
			return template.update(sql);
		} catch (Exception e) {
			return 0;
		}

	}

	@Override
	public int deleteEventById(Event event) {
		String sql = "update GameEventTbl set ShowStatus='" + event.getShowStatus() + "', bookingStatus='"
				+ event.getBookingStatus() + "'where GameEventId='" + event.getGameEventId() + "'";
		try {
			return template.update(sql);
		} catch (Exception e) {
			return 0;
		}

	}

	@Override
	public List<Event> getEventListByWeek() {
		String sql = "select * from GameEventTbl order by str_to_date(ShowDate, '%D-%M-%Y') ASC";
		try {
			return template.query(sql, new RowMapper<Event>() {
				public Event mapRow(ResultSet rs, int row) throws SQLException {
					Event event = new Event();
					event.setGameEventId(rs.getString("GameEventId"));
					event.setGameEventName(rs.getString("GameEventName"));
					event.setShowDate(rs.getString("ShowDate"));
					event.setShowStatus(rs.getString("ShowStatus"));
					event.setNumberOfSeats(rs.getString("NumberOfSeats"));
					return event;
				}
			});
		} catch (Exception e) {
			return new ArrayList<Event>();
		}

	}

	@Override
	public List<EventSeatBlockDtl> getSoftBlockDetails(String id) {

		String sql = "SELECT GameEventTbl.GameEventId , GameEventTbl.GameEventName ,"
				+ "GameEventTbl.ShowDate,EventBlockTbl.ConfirmationDate,EventBlockTbl.TotalSeatBooked "
				+ "FROM GameEventTbl ,EventBlockTbl "
				+ "WHERE GameEventTbl.GameEventId = EventBlockTbl.GameEventId and EventBlockTbl.UserNumber = '" + id
				+ "' " + "and EventBlockTbl.TotalSeatBooked >0 and STR_TO_DATE(ShowDate, '%d-%m-%Y') > CURDATE()";
		try {
			return template.query(sql, new RowMapper<EventSeatBlockDtl>() {
				public EventSeatBlockDtl mapRow(ResultSet rs, int row) throws SQLException {
					EventSeatBlockDtl event = new EventSeatBlockDtl();
					event.setGameEventId(rs.getString(1));
					event.setGameEventName(rs.getString(2));
					event.setShowDate(rs.getString(3));
					event.setConfirmationDate(rs.getString(4));
					event.setTotalSeatBooked(rs.getString(5));
					return event;
				}
			});
		} catch (Exception e) {
			return new ArrayList<EventSeatBlockDtl>();
		}

	}

	@Override
	public List<Event> getEventListByName(String name) {
		String sql = "select * from GameEventTbl where GameEventName like '%" + name
				+ "%' order by str_to_date(ShowDate, '%D-%M-%Y') ASC";
		try {
			return template.query(sql, new RowMapper<Event>() {
				public Event mapRow(ResultSet rs, int row) throws SQLException {
					Event event = new Event();
					event.setGameEventId(rs.getString("GameEventId"));
					event.setGameEventName(rs.getString("GameEventName"));
					event.setShowDate(rs.getString("ShowDate"));
					event.setShowStatus(rs.getString("ShowStatus"));
					event.setNumberOfSeats(rs.getString("NumberOfSeats"));
					return event;
				}
			});

		} catch (Exception e) {
			return new ArrayList<Event>();
		}

	}

	@Override
	public int updateEventSeatCount(Event event) {
		String sql = "update GameEventTbl set NumberOfSeats='" + event.getNumberOfSeats() + "' where GameEventId='"
				+ event.getGameEventId() + "'";
		try {
			return template.update(sql);
		} catch (Exception e) {
			return 0;
		}

	}

	@Override
	public int updateBookingStatus(Event event) {
		String sql = "update GameEventTbl set BookingStatus='" + event.getBookingStatus() + "' where GameEventId='"
				+ event.getGameEventId() + "'";
		try {
			return template.update(sql);
		} catch (Exception e) {
			return 0;
		}

	}

}
