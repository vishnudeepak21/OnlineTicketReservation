<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<link rel="stylesheet" href="css/style.css">
<link href="https://fonts.googleapis.com/css?family=Ubuntu"
	rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet"
	href="path/to/font-awesome/css/font-awesome.min.css">
<title>Admin Home</title>
</head>
<style>
body {
	background-color: #F3EBF6;
	font-family: 'Ubuntu', sans-serif;
}

.main {
	background-color: #FFFFFF;
	width: 400px;
	height: 400px;
	margin: 7em auto;
	border-radius: 1.5em;
	box-shadow: 0px 11px 35px 2px rgba(0, 0, 0, 0.14);
}

.sign {
	padding-top: 40px;
	color: #8C55AA;
	font-family: 'Ubuntu', sans-serif;
	font-weight: bold;
	font-size: 23px;
}

.un {
	width: 76%;
	color: rgb(38, 50, 56);
	font-weight: 700;
	font-size: 14px;
	letter-spacing: 1px;
	background: rgba(136, 126, 126, 0.04);
	padding: 10px 20px;
	border: none;
	border-radius: 20px;
	outline: none;
	box-sizing: border-box;
	border: 2px solid rgba(0, 0, 0, 0.02);
	margin-bottom: 50px;
	margin-left: 46px;
	text-align: center;
	margin-bottom: 27px;
	font-family: 'Ubuntu', sans-serif;
}

form.form1 {
	padding-top: 40px;
}

.pass {
	width: 76%;
	color: rgb(38, 50, 56);
	font-weight: 700;
	font-size: 14px;
	letter-spacing: 1px;
	background: rgba(136, 126, 126, 0.04);
	padding: 10px 20px;
	border: none;
	border-radius: 20px;
	outline: none;
	box-sizing: border-box;
	border: 2px solid rgba(0, 0, 0, 0.02);
	margin-bottom: 50px;
	margin-left: 46px;
	text-align: center;
	margin-bottom: 27px;
	font-family: 'Ubuntu', sans-serif;
}

.un:focus, .pass:focus {
	border: 2px solid rgba(0, 0, 0, 0.18) !important;
}

.submit {
	cursor: pointer;
	border-radius: 5em;
	color: #fff;
	background: linear-gradient(to right, #9C27B0, #E040FB);
	border: 0;
	padding-left: 40px;
	padding-right: 40px;
	padding-bottom: 10px;
	padding-top: 10px;
	font-family: 'Ubuntu', sans-serif;
	margin-left: 35%;
	font-size: 13px;
	box-shadow: 0 0 20px 1px rgba(0, 0, 0, 0.04);
}

.forgot {
	text-shadow: 0px 0px 3px rgba(117, 117, 117, 0.12);
	color: #E1BEE7;
	padding-top: 15px;
}

.error-message {
	background-color: #fce4e4;
	border: 1px solid #fcc2c3;
	float: left;
	padding: 20px 30px;
}

.error-text {
	color: #cc0033;
	font-family: Helvetica, Arial, sans-serif;
	font-size: 13px;
	font-weight: bold;
	line-height: 20px;
	text-shadow: 1px 1px rgba(250, 250, 250, .3);
}

@media ( max-width : 600px) {
	.main {
		border-radius: 0px;
	}
}

a {
	 cursor: pointer;
	border-radius: 3em;
	color: #fff;
	background: linear-gradient(to right, #b279a7, #d387ab);
	border: 0;
	padding-left: 3px;
	padding-right: 3px;
	padding-bottom: 3px;
	padding-top: 3px;
	font-family: 'Ubuntu', sans-serif;
	margin-left: 1px;
	font-size: 13px;
	box-shadow: 0 0 20px 1px rgba(0, 0, 0, 0.04);
	text-decoration: none;
}

a:hover {
	 border-color: rgba(255, 255, 255, 1);
}

#events {
	font-family: 'Ubuntu', sans-serif;
	border-collapse: collapse;
	width: 100%;
}

#events td, #events th {
	border: 1px solid #ddd;
	text-align: center;
	padding: 8px;
}

#events tr:nth-child(even) {
	background-color: #f2f2f2;
}

#events tr:hover {
	background-color: #ddd;
}

#events th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: center;
	background-color: #F3EBF6;
}
</style>

<h1 class="sign" align="center">Soft Block Details</h1>
<a href="/TicketBooking/userhome">Previous</a>
<br>
<br>
<br>

<table id="events">
	<tr>
		<th>Event Id</th>
		<th>Event Name</th>
		<th>Show Date</th>
		<th>Seats Booked</th>
		<th>Seat confirmed Date</th>
	</tr>
	<c:forEach var="event" items="${eventList}">
		<tr>
			<td>${event.gameEventId}</td>
			<td>${event.gameEventName}</td>
			<td>${event.showDate}</td>
			<td>${event.totalSeatBooked}</td>
			<td>${event.confirmationDate}</td>
		</tr>
	</c:forEach>
</table>
<br>
<br>
<br>
<a href="/TicketBooking/userhome">Previous</a>