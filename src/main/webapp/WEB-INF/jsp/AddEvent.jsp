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
<title>Sign in</title>
</head>
<style>
.error {
	color: red
}

body {
	background-color: #F3EBF6;
	font-family: 'Ubuntu', sans-serif;
}

.main {
	background-color: #FFFFFF;
	width: 700px;
	height: 700px;
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
	border: 2px solid rgba(0, 0, 0, 0.02);
	margin-bottom: 50px;
	margin-left: 46px;
	text-align: center;
	margin-bottom: 27px;
	font-family: 'Ubuntu', sans-serif;
	border-radius: 20px;
}

form.form1 {
	padding-top: 40px;
}

.un:focus {
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
	font-size: 14px;
	box-shadow: 0 0 20px 1px rgba(0, 0, 0, 0.04);
}

.forgot {
	text-shadow: 0px 0px 3px rgba(117, 117, 117, 0.12);
	color: #E1BEE7;
	padding-top: 15px;
}

.error-text {
	font-size: 5rem;
	text-transform: capitalize;
	font-size: 1.5rem;
	color: red;
	font-weight: bold;
	font-size: 20px;
	font-family: 'Ubuntu', sans-serif;
}

.btn {
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
	font-size: 14px;
	text-decoration: none;
	box-shadow: 0 0 20px 1px rgba(0, 0, 0, 0.04);
}

a:hover {
	 border-color: rgba(255, 255, 255, 1);
}

@media ( max-width : 600px) {
	.main {
		border-radius: 0px;
	}
}

label {
	cursor: pointer;
	text-color: linear-gradient(to right, #9C27B0, #E040FB);
	display: block;
	padding: 10px;
	margin: 3px;
	align: center
}

.error-message {
	cursor: pointer;
	color: 6b0f1a;
	display: block;
	padding: 10px;
	margin: 3px;
	width: 600px;
	align: center;
	font-weight: bold;
}

.success-message {
	cursor: pointer;
	color: green;
	display: block;
	padding: 10px;
	margin: 3px;
	width: 600px;
	align: center;
	font-weight: bold;
}
</style>
<body>

	<p class="sign" align="center">Add Event</p>
	<div class="main">
		<div align="center">
			<p class="success-message">${Success}</p>
		</div>
		<form:form method="post" action="addEventInDb" modelAttribute="event"
			class="form1">
			<label for="gameEventId">Game Event Id </label>
			<form:input class="un " path="gameEventId" readonly="true"
				type="text" align="center" placeholder="Event Id" id="gameEventId" />
			<br>
			<label for="gameEventName">Game Event Name </label>
			<form:input class="un " path="gameEventName" type="text"
				align="center" placeholder="Event Name" id="gameEventName" />
			<br>
			<label for="showDate">show date </label>
			<form:input class="un " path="showDate" type="text" align="center"
				placeholder="show Date" id="showDate" />
			<br>
			<label for="numberOfSeats">Number of Seats </label>
			<form:input class="un " path="numberOfSeats" type="text"
				align="center" placeholder="Number of Seats" id="numberOfSeats" />
			<br>
			<div align="center">
				<form:errors class="error-message" style="margin-bottom: 20px"
					align="center" path="*" />
				<br>
			</div>
			<input style="margin-auto: 20px" type="submit" class="btn"
				value="    Add    " />
			<a class="forgot" style="margin-left: 20px"
				href="/TicketBooking/previous">Previous</a>
		</form:form>
	</div>
</body>