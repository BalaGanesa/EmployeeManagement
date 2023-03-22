setInterval(notification, 5000);

//function inOut() {
//	console.log("aa");
//	var iframe = document.createElement("iframe");
//	iframe.src = "checkIn.html";
//	document.getElementById("main").style.backgroundImage = "none";
//	document.getElementById("main").innerHTML = "";
//	iframe.height = "100%";
//	iframe.width = "100%";
//	iframe.style.border = "0";
//	document.getElementById("main").appendChild(iframe);
//}
//
//function InoutCheck(){
//var a  = document.getElementById("checkInBut");
//if(a.innerText == "Check In"){
//  document.getElementById("checkInBut").innerText = "Check Out";
//  document.getElementById("checkInBut").style.backgroundColor = "#e95b6d";
//  startTimer();
//}
//else{
//  document.getElementById("checkInBut").innerText = "Check In";
//  document.getElementById("checkInBut").style.backgroundColor = "#27cda5";
//  stopTimer()
//}
//
//}
//function holiday(){
//	var xhr = new XMLHttpRequest();
//	xhr.open("POST", "holiday");
//	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//	xhr.send();
//	xhr.onreadystatechange = function() {
//		if (xhr.readyState == 4) {
//			console.log(xhr.responseText);
//			var json = JSON.parse(xhr.responseText);
//		console.log(json.response1.response.holidays[0].date.iso);
//		
//		
//			}
//		}
//}
//function date(){
//  var today = new Date();
//  var date = today.getDate()+'-'+(today.getMonth()+1)+'-'+today.getFullYear();
//  document.getElementById("date").innerHTML += date;
//  holiday();
//}
//let timerInterval = null;
//let timerSeconds = 0;
//
//function startTimer() {
//    timerInterval = setInterval(updateTimer, 1000);
//}
//
//function stopTimer() {
//    clearInterval(timerInterval);
//}
//
//function updateTimer() {
//    timerSeconds++;
//    let hours = Math.floor(timerSeconds / 3600);
//    let minutes = Math.floor((timerSeconds - (hours * 3600)) / 60);
//    let seconds = timerSeconds - (hours * 3600) - (minutes * 60);
//    document.getElementById("timer").innerHTML = pad(hours) + ":" + pad(minutes) + ":" + pad(seconds);
//}
//
//function pad(num) {
//    return ("0" + num).slice(-2);
//}

document.body.addEventListener('click', function() {
	if (document.querySelector('.focused') != null && document.getElementById('shadowDiv') == null) {
		var temp = document.querySelector('.focused').children[0].innerText;
		var id = temp.substring(temp.indexOf('(') + 1, temp.indexOf(')'));
		console.log(id);
		viewEmployeeProfile(id);
		document.querySelector('.focused').classList.remove('focused');
	}
})
function viewEmployeeProfile(id) {
	var shadow = document.createElement("div");
	document.body.appendChild(shadow);
	shadow.setAttribute("id", "shadow");
	var shadowDiv = document.createElement("div");
	shadow.appendChild(shadowDiv);
	shadowDiv.setAttribute("id", "shadowDiv");
	viewEmployee(id);
}
function viewEmployee(id) {
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "view");
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send("empId=" + id);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {

			console.log(xhr.responseText);
						var json = JSON.parse(xhr.responseText);
			employeeView(json);
		}
	}
}

function employeeView(json) {
	console.log("taskView");
	document.getElementById("shadowDiv").innerHTML = "<div id = 'CloseButton' onclick = 'removingShadow()'>&#215</div><div class='SmallDiv1'><span>Employee Id</span><input type='text' id = 'EmployeeId' name='EmployeeId' readonly></div><div class='SmallDiv1'><span>Employee Name</span><input type='text' id = 'EmployeeName' name='EmployeeName' readonly></div><div class='SmallDiv1'><span>Team</span><input type='text' id = 'Team' name='Team' readonly></div><div class='SmallDiv1'><span>Email ID</span><input type='text' id = 'EmailId' name='EmailId' readonly></div><div class='SmallDiv1'><span>Contact_no</span><input type='text' id = 'Contact_no' name='Contact_no' readonly></div><div class='SmallDiv1'><span>ReprotingTo</span><input type='text' id = 'ReprotingTo' name='ReprotingTo' readonly></div>";
	console.log(document.getElementById("shadowDiv"));
	document.getElementById("EmployeeId").value = json[0].id;
	document.getElementById("EmployeeName").value = json[0].name;
	document.getElementById("Team").value = json[0].team;
	document.getElementById("EmailId").value = json[0].email_id;
	document.getElementById("Contact_no").value = json[0].contact_no;
	document.getElementById("ReprotingTo").value = json[1].name;
}





function login() {
	var currentlyLoged = document.getElementById("emailId").value;
	var password = document.getElementById("userPass").value;
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			console.log(xhr.responseText);
			if (xhr.responseText == "HR\n") {
				window.location.href = "index.html";
			} else if (xhr.responseText == "emp\n") {
				window.location.href = "Emp.html";
			} else if (xhr.responseText == "Mentor\n") {
				window.location.href = "Mentor.html";
			}
			else if (xhr.responseText == "Manager\n") {
				window.location.href = "Manager.HTML";
			} else {
				alert(xhr.responseText);
			}
		}
	}
	xhr.open("POST", "login");
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send("emailId=" + currentlyLoged + "&userPass=" + password + "");

}

function load() {
	const today = new Date().toISOString().split('T')[0];
	document.getElementById("dateFrom").setAttribute("min", today);
}

function viewProfile() {
	console.log("aa");
	var iframe = document.createElement("iframe");
	iframe.src = "viewProfile.html";
	document.getElementById("main").innerHTML = "";
	iframe.height = "100%";
	iframe.width = "100%";
	iframe.style.border = "0";
	document.getElementById("main").appendChild(iframe);
	update1();
}

function change() {
	document.getElementById("main").style.backgroundImage = "none";
	document.getElementById("main").style.backgroundColor = "White";
	document.getElementById("main").innerHTML = "<div id='add'><div id='add2'><div class='smallDiv1'><span>Employee ID</span><select class ='smallDiv1' id='emp' name='emp' onchange = 'fetchMentor()'><option value='Select the employee'>Select the Employee</option></select></div><div class='smallDiv1'><span>Assign the Mentor</span><select class ='smallDiv1' id='Mentor' name='Mentor'></select></div><button type='button' id = 'Submit' value='Submit' onclick = 'chnage1()'>change</button><button type='button' id = 'Submit' value='Submit' onclick = 'cancel1()'>cancel</button>";
}
function cancel1() {
	window.location.href = "Manager.HTML";
}

function chnage1() {
	var empID = document.getElementById("emp").value.split(" ");
	var Mentor = document.getElementById("Mentor").value.split(" ");
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "changeReport");
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send("empId=" + empID[0] + "&mentor=" + Mentor[0]);
	xhr.onreadystatechange = () => {
		if (xhr.readyState == 4) {
			alert(xhr.responseText);
		}
	}
}

function fetchMentor() {
	var xhr = new XMLHttpRequest();
	var a = document.getElementById("emp").value.split(" ");
	console.log(a[0]);
	xhr.open("POST", "changeReportees");
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send("empId=" + a[0]);
	xhr.onreadystatechange = () => {
		if (xhr.readyState == 4) {
			console.log(xhr.responseText);
			var b = JSON.parse(xhr.responseText);
			console.log(b);
			var select = document.getElementById("Mentor");
			for (var i = 0; i < b.length; i++) {
				var option = document.createElement("option");
				option.text = b[i].id + " " + b[i].name;
				option.value = b[i].id + " " + b[i].name;
				select.appendChild(option);
			}
		}
	}
}

function fetchEmployee() {
	console.log("aa");
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "changeReportees");
	xhr.send();
	xhr.onreadystatechange = () => {
		if (xhr.readyState == 4) {
			console.log(xhr.responseText);
			var b = JSON.parse(xhr.responseText);
			console.log(b);
			var select = document.getElementById("emp");
			for (var i = 0; i < b.length; i++) {
				var option = document.createElement("option");
				option.text = b[i].id + " " + b[i].name;
				option.value = b[i].id + " " + b[i].name;
				select.appendChild(option);
			}
		}
	}
}
function update() {
	console.log("Aa");
	console.log(document.getElementById("main"));
	var iframe = document.createElement("iframe");
	iframe.src = "edit.html";
	document.getElementById("main").innerHTML = "";
	iframe.height = "100%";
	iframe.width = "100%";
	iframe.style.border = "0";
	document.getElementById("main").appendChild(iframe);
	update1();
}

function save() {
	console.log("aa");
	var data = {};
	data.empId = document.getElementById("empId").value;
	data.empName = document.getElementById("empname").value;
	data.DOB = document.getElementById("date").value;
	data.team = document.getElementById("team").value;
	data.ph_no = document.getElementById("contact_no").value;
	data.email_id = document.getElementById("email").value;
	data.Mentor = document.getElementById("reportees").value;
	var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
	if (!emailPattern.test(email.value)) {
		alert("Invalid email address.");
	} else if (isNaN(contact_no.value) || contact_no.value.length != 10) {
		alert("Invalid Phone number.");
	} else {
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = () => {
			console.log(xhr.readyState);
			if (xhr.readyState == 4) {
				alert(xhr.responseText);
			}
		}
		xhr.open("GET", 'update?json=' + encodeURIComponent(JSON.stringify(data)));
		xhr.send();
	}
}

function notify() {
	var n = document.getElementById("n");
	var a = document.createElement("div");
	a.setAttribute("id", "notify");
	document.getElementById("main").appendChild(a);
	var p = document.createElement("p");
	p.setAttribute("id", "para");
	const node = document.createTextNode("Notification");
	p.appendChild(node);
	a.appendChild(p);

	n.setAttribute("onclick", "notify2()");
}

function notify2() {
	document.getElementById("notify").setAttribute("id", "hide")
	document.getElementById("n").setAttribute("onclick", "notify()");
}

function Apporve() {
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "Apporve");
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send();
	xhr.onreadystatechange = () => {
		if (xhr.readyState == 4) {
			document.getElementById("main").style.backgroundImage = "white";
			document.getElementById("main").style.backgroundImage = "none";
			var json = JSON.parse(xhr.responseText);
			var parent = document.createElement("div");
			parent.setAttribute("id", "Need");
			document.getElementById("main").innerHTML = '';
			document.getElementById("main").appendChild(parent);
			for (let i = 0; i < json.length; i++) {
				var divName = "EmpId" + i;

				var ParentDiv = document.createElement("div");
				document.getElementById("Need").appendChild(ParentDiv);
				ParentDiv.setAttribute("class", "elements");
				ParentDiv.setAttribute("id", divName);
				var EmpId = document.createElement("span");
				document.getElementById(divName).appendChild(EmpId);
				EmpId.innerText = json[i].EmpId;

				var EmpName = document.createElement("span");
				document.getElementById(divName).appendChild(EmpName);
				EmpName.innerText = json[i].EmpName;

				var Need = document.createElement("span");
				document.getElementById(divName).appendChild(Need);
				Need.innerText = json[i].Need;

				var Reason = document.createElement("span");
				Reason.setAttribute("id", "reason");
				document.getElementById(divName).appendChild(Reason);
				Reason.innerText = json[i].Reason;

				var RequestedDate = document.createElement("span");
				document.getElementById(divName).appendChild(RequestedDate);
				RequestedDate.innerText = json[i].RequestedDate;

				var approve = document.createElement("span");
				approve.innerText = "Approve";
				approve.onclick = (event) => {
					accept(json[i].EmpId, event.target.parentNode);
				};
				approve.setAttribute("id", "Approvement");

				var reject1 = document.createElement("span");

				reject1.innerText = "Reject";
				reject1.onclick = (event) => {
					reject(json[i].EmpId, event.target.parentNode);
				};
				reject1.setAttribute("id", "Rejection");
				document.getElementById(divName).appendChild(reject1);
				document.getElementById(divName).appendChild(approve);
			}
		}
	}

}

function LeaveApporve() {
	console.log("aa")
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "leave");
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send();
	xhr.onreadystatechange = () => {
		if (xhr.readyState == 4) {
			document.getElementById("main").style.backgroundImage = "white";
			document.getElementById("main").style.backgroundImage = "none";
			var json = JSON.parse(xhr.responseText);
			var parent = document.createElement("div");
			parent.setAttribute("id", "Need");
			document.getElementById("main").innerHTML = '';
			document.getElementById("main").appendChild(parent);
			for (let i = 0; i < json.length; i++) {
				var divName = "EmpId" + i;

				var ParentDiv = document.createElement("div");
				document.getElementById("Need").appendChild(ParentDiv);
				ParentDiv.setAttribute("class", "element1");
				ParentDiv.setAttribute("id", divName);
				var EmpId = document.createElement("span");
				document.getElementById(divName).appendChild(EmpId);
				EmpId.innerText = json[i].EmpId;

				var EmpName = document.createElement("span");
				document.getElementById(divName).appendChild(EmpName);
				EmpName.innerText = json[i].EmpName;

				var FromDate = document.createElement("span");
				document.getElementById(divName).appendChild(FromDate);
				FromDate.innerText = json[i].FromDate;

				var ToDate = document.createElement("span");
				document.getElementById(divName).appendChild(ToDate);
				ToDate.innerText = json[i].ToDate;

				var Reason = document.createElement("span");
				Reason.setAttribute("id", "reason");
				document.getElementById(divName).appendChild(Reason);
				Reason.innerText = json[i].Reason;


				var LeaveType = document.createElement("span");
				document.getElementById(divName).appendChild(LeaveType);
				LeaveType.innerText = json[i].LeaveType;

				var approve = document.createElement("span");
				approve.innerText = "Approve";
				approve.onclick = (event) => {
					accept1(json[i].EmpId, event.target.parentNode);
				};
				approve.setAttribute("id", "Approvement");

				var reject1 = document.createElement("span");
				reject1.innerText = "Reject";
				reject1.onclick = (event) => {
					bala(json[i].EmpId, event.target.parentNode);
				};
				reject1.setAttribute("id", "Rejection");
				document.getElementById(divName).appendChild(reject1);
				document.getElementById(divName).appendChild(approve);
			}
		}
	}

}
function accept1(id, div) {
	console.log("aa");
	var id = id * 1;
	if (!isNaN(id)) {
		div.remove();
		var xhr = new XMLHttpRequest();
		console.log(xhr.responseText);
		xhr.open('GET', 'leave?id=' + id + '&status=APPORVED');
		xhr.send();
	}
}

function accept(id, div) {
	var id = id * 1;
	if (!isNaN(id)) {
		div.remove();
		var xhr = new XMLHttpRequest();
		console.log(xhr.responseText);
		xhr.open('GET', 'Apporve?id=' + id + '&status=ACCEPT');
		xhr.send();
	}
}
function bala(id, div) {
	console.log("aa");
	var id = id * 1;
	if (!isNaN(id)) {
		div.remove();
		var xhr = new XMLHttpRequest();
		xhr.open('GET', 'leave?id=' + id + '&status=REJECTED');
		xhr.send();
	}
}
function check() {
	console.log("Aa");
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			var a = JSON.parse(this.responseText);
			console.log(a);
			if (a.Statuscode == 200) {
				console.log(a);
				if (a.role == 'HR') {
					whole.innerHTML = window.location.href = "index.html";
				}
				if (a.role == 'Employee') {
					window.location.href = "Emp.html";
				}
				if (a.role == 'Mentor') {
					window.location.href = "Mentor.html";
				}
				if (a.role == 'Manager') {
					window.location.href = "Manager.HTML";
				}
			}
		}
	}
	xhr.open('GET', 'Home');
	xhr.send();
}

function reject(id, div) {
	var id = id * 1;
	if (!isNaN(id)) {
		div.remove();
		var xhr = new XMLHttpRequest();
		xhr.open('GET', 'Apporve?id=' + id + '&status=REJECT');
		xhr.send();
	}
}

function subbmit() {
	var a = document.getElementById("empId").value.split("-");
	var data = {};
	data.empId = a[0];
	data.empName = a[1];
	data.require = document.getElementById("Require").value;
	data.reason = document.getElementById("reason").value;

	var xhr = new XMLHttpRequest();


	xhr.onreadystatechange = () => {
		if (xhr.readyState == 4) {
			alert(xhr.responseText);
		}
	}
	xhr.open("POST", "Requirement");
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(JSON.stringify(data));
}

function loadRequirement() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "Requirement");
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			let json = JSON.parse(xhr.responseText);
			var select = document.getElementById("Require");
			select.innerHTML = "";
			for (var i = 0; i < json.length; i++) {
				var option = document.createElement("option");
				option.text = json[i];
				option.value = json[i];
				select.appendChild(option);
			}
		}

	}
}

function Add() {
	document.getElementById("main").style.backgroundImage = "none";
	document.getElementById("main").style.backgroundColor = "White";
	document.getElementById("main").innerHTML = "<div id='add'><div id='add1'><div class='smallDiv1'><span>EmployeeName</span><input type='text'id = 'EmployeeName' name='EmployeeName' required></div><div class='smallDiv1'><span>Date-Of-Birth</span><input type='date' id = 'DOB' name='DOB' required></div><div class='smallDiv1'><span>Gender</span><select id='Gender'><option value='Male'>Male</option><option value='Female'>Female</option><option value='Others'>Others</option></select></div><div class='smallDiv1'><span>EmployeeSalary</span><input type='number' id = 'EmployeeSalary' name='EmployeeSalary' required></div><div class='smallDiv1'><span>Contact_NO</span><input type='number' id = 'contact_no' name='contact_no' required></div><div class='smallDiv1'><span>Email_id</span><input type='email' id = 'Email_id' name='Email_id' required></div><div class='smallDiv1'><span>Assign the Team</span><select class ='smallDiv1' id='AssignTeam' name='AssignTeam' onchange='myFunction()'><option value='select the Team'>Select the Team</option><option value='DESK'>DESK</option><option value='CRM'>CRM</option><option value='ZOHOWRITER'>ZOHOWRITER</option><option value='ZOHOSHOW'>ZOHOSHOW</option><option value='ZLABS'>ZLABS</option></select></div><div class='smallDiv1'><label>Assign the Mentor</label><select id = 'Mentor' name='Mentor'></select></div><button type='button' id = 'Submit' value='Submit' onclick = 'submit()'>Submit</button></div></div>";
}

function myFunction() {
	var xhr = new XMLHttpRequest();
	var team = document.getElementById("AssignTeam").value;
	xhr.open("GET", "AddEmployee?id=" + team + "&method=GET");
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {

			var a = xhr.responseText.replace("[", "").replace("]", "");
			var b = a.split(", ");
			var select = document.getElementById("Mentor");
			select.innerHTML = "";
			for (var i = 0; i < b.length; i++) {
				var option = document.createElement("option");
				option.text = b[i];
				option.value = b[i];
				select.appendChild(option);
			}
		}

	}
}

function task1() {
	console.log("a");
	document.getElementById("main").style.backgroundImage = "none";
	document.getElementById("main").style.justifyContent = "space-around";
	document.getElementById("main").style.backgroundColor = "#dcdcdc";
	document.getElementById("main").innerHTML = "";
	for (var i = 1; i <= 3; i++) {
		var a = document.createElement("div");
		a.id = "div" + i;
		a.className = "container";
		document.getElementById("main").appendChild(a);
	}
	task2();
}
function task2() {
	var a = document.createElement("div");
	a.className = "request";
	document.getElementById("div1").appendChild(a);
	a.innerText = "REQUESTED";
	var body0 = document.createElement("div");
	body0.className = "body";
	document.getElementById("div1").appendChild(body0);
	var b = document.createElement("div");
	b.className = "inprogress";
	document.getElementById("div2").appendChild(b);
	b.innerText = "INPROGRESS";
	var body1 = document.createElement("div");
	body1.className = "body";
	document.getElementById("div2").appendChild(body1);
	var c = document.createElement("div");
	c.className = "Done";
	document.getElementById("div3").appendChild(c);
	c.innerText = "DONE";
	var body2 = document.createElement("div");
	body2.className = "body";
	document.getElementById("div3").appendChild(body2);
	task3();
}

function task3() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "viewTask");
	xhr.send();
	xhr.onreadystatechange = () => {
		console.log(xhr.readyState);
		if (xhr.readyState == 4) {
			var json = JSON.parse(xhr.responseText);
			for (var i = 0; i < json.length; i++) {
				if (json[i].TaskStatus == "PENDING") {
					var a = document.createElement("div");
					var id = json[i].TaskId;
					a.id = "pending" + i;
					a.className = "box";
					a.setAttribute('draggable', 'true');
					a.style.marginbottom = "20px";
					document.getElementById("div1").childNodes[1].appendChild(a);
					var taskId = document.createElement("div");
					taskId.id = "taskId";
					taskId.className = "Task"
					a.appendChild(taskId);
					var spanTag = document.createElement("span");
					spanTag.innerText = "Task ID";
					taskId.appendChild(spanTag)
					var spantag1 = document.createElement("span");
					spantag1.innerText = json[i].TaskId;
					spantag1.style.marginLeft = "107px";
					taskId.appendChild(spantag1);
					var divv = document.createElement("div");
					divv.id = "TaskName";
					divv.className = "Task"
					a.appendChild(divv);
					var span = document.createElement("span");
					span.innerText = "Task Name";
					divv.appendChild(span)
					var span1 = document.createElement("span");
					span1.innerText = json[i].TaskName;
					span1.style.marginLeft = "78px";
					divv.appendChild(span1)
					var tas = document.createElement("div");
					tas.id = "task";
					tas.className = "Task";
					tas.style.display = 'flex';
					a.appendChild(tas);
					var div1 = document.createElement("div");
					div1.id = "TaskDescription";
					div1.className = "Task";
					a.appendChild(div1);
					var span = document.createElement("span");
					span.innerText = "Task Description";
					div1.appendChild(span)
					var task = document.createElement("div");
					task.id = "taskDesc";
					task.className = "Task";
					a.appendChild(task);
					var span1 = document.createElement("span");
					span1.innerText = json[i].TaskDescription;
					task.appendChild(span1);
					span1.style.marginLeft = "31px";
					tas.appendChild(div1);
					tas.appendChild(task);
				}
				if (json[i].TaskStatus == "INPROGRESS") {
					var a = document.createElement("div");
					a.id = "pending" + i;
					a.className = "box";
					a.setAttribute('draggable', 'true');
					a.style.marginbottom = "20px";
					document.getElementById("div2").childNodes[1].appendChild(a);
					var taskId = document.createElement("div");
					taskId.id = "taskId";
					taskId.className = "Task";
					a.appendChild(taskId);
					var spanTag = document.createElement("span");
					spanTag.innerText = "Task ID";
					taskId.appendChild(spanTag)
					var spantag1 = document.createElement("span");
					spantag1.innerText = json[i].TaskId;
					spantag1.style.marginLeft = "107px";
					taskId.appendChild(spantag1);
					var divv = document.createElement("div");
					divv.id = "TaskName";
					divv.className = "Task";
					a.appendChild(divv);
					var span = document.createElement("span");
					span.innerText = "Task Name";
					divv.appendChild(span)
					var span1 = document.createElement("span");
					span1.innerText = json[i].TaskName;
					span1.style.marginLeft = "78px";
					divv.appendChild(span1)
					var tas = document.createElement("div");
					tas.id = "task";
					tas.className = "Task";
					tas.style.display = 'flex';
					a.appendChild(tas);
					var div1 = document.createElement("div");
					div1.id = "TaskDescription";
					div1.className = "Task";
					a.appendChild(div1);
					var span = document.createElement("span");
					span.innerText = "Task Description";
					div1.appendChild(span)
					var task = document.createElement("div");
					task.id = "taskDesc";
					task.className = "Task";
					a.appendChild(task);
					var span1 = document.createElement("span");
					span1.innerText = json[i].TaskDescription;
					task.appendChild(span1);
					span1.style.marginLeft = "31px";
					tas.appendChild(div1);
					tas.appendChild(task);
				}
				if (json[i].TaskStatus == "FINISHED") {
					var a = document.createElement("div");
					a.id = "pending" + i;
					a.className = "box";
					a.setAttribute('draggable', 'true');
					a.style.marginbottom = "20px";
					document.getElementById("div3").childNodes[1].appendChild(a);
					var taskId = document.createElement("div");
					taskId.id = "taskId";
					taskId.className = "Task";
					a.appendChild(taskId);
					var spanTag = document.createElement("span");
					spanTag.innerText = "Task ID";
					taskId.appendChild(spanTag)
					var spantag1 = document.createElement("span");
					spantag1.innerText = json[i].TaskId;
					spantag1.style.marginLeft = "107px";
					taskId.appendChild(spantag1);
					var divv = document.createElement("div");
					divv.id = "TaskName";
					divv.className = "Task";
					a.appendChild(divv);
					var span = document.createElement("span");
					span.innerText = "Task Name";
					divv.appendChild(span)
					var span1 = document.createElement("span");
					span1.innerText = json[i].TaskName;
					span1.style.marginLeft = "78px";
					divv.appendChild(span1)
					var tas = document.createElement("div");
					tas.id = "task";
					tas.className = "Task";
					tas.style.display = 'flex';
					a.appendChild(tas);
					var div1 = document.createElement("div");
					div1.id = "TaskDescription";
					div1.className = "Task";
					a.appendChild(div1);
					var span = document.createElement("span");
					span.innerText = "Task Description";
					div1.appendChild(span)
					var task = document.createElement("div");
					task.id = "taskDesc";
					task.className = "Task";
					a.appendChild(task);
					var span1 = document.createElement("span");
					span1.innerText = json[i].TaskDescription;
					task.appendChild(span1);
					span1.style.marginLeft = "31px";
					tas.appendChild(div1);
					tas.appendChild(task);


				}
			}
			task4();
		}
	}
}

function removingShadow() {
	console.log("task")
	$("#shadow").animate({
		width: "toggle",
	});
	document.getElementById('shadow').remove();
}


function task4() {
	const boxes = document.querySelectorAll('.box');
	const containers = document.querySelectorAll('.body');
	console.log(boxes);
	let activeBox = null;

	boxes.forEach((box) => {
		box.addEventListener('click', () => {
			console.log("call");
			taskId = box.firstChild.innerText.slice(7);
			console.log(taskId);
			var shadow = document.createElement("div");
			document.getElementById("whole").appendChild(shadow);
			shadow.setAttribute("id", "shadow");
			var shadowDiv = document.createElement("div");
			shadow.appendChild(shadowDiv);
			shadowDiv.setAttribute("id", "shadowDiv");
			viewTask2(taskId);
		});
	})








	boxes.forEach((box) => {
		box.addEventListener('dragstart', () => {
			activeBox = box;
		});

		box.addEventListener('dragend', () => {
			activeBox = null;
		});
	});
	containers.forEach((container) => {
		container.addEventListener('dragover', (e) => {

			e.preventDefault();
		});

		container.addEventListener('drop', () => {
			container.appendChild(activeBox);
			var divname = activeBox.parentElement.parentElement.id;
			var taskId = activeBox.firstChild.innerText.slice(7);
			var xhr = new XMLHttpRequest();

			xhr.open("GET", "updateTaskStatus?divname=" + divname + "&taskId=" + taskId);
			xhr.send();
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					alert(xhr.responseText);
				}
			}

		});
	});
}
function Inout() {
	document.getElementById("main").style.backgroundImage = "none";
	document.getElementById("main").style.backgroundColor = "White";
	var iframe = document.createElement("iframe");
	iframe.src = "Attendence.html";
	document.getElementById("main").innerHTML = "";
	iframe.height = "100%";
	iframe.width = "100%";
	iframe.style.border = "0";
	document.getElementById("main").appendChild(iframe);
}



function promotion() {
	document.getElementById("main").style.backgroundImage = "none";
	document.getElementById("main").style.backgroundColor = "White";
	document.getElementById("main").innerHTML = "<div id='add'><div id='add2'><div class='smallDiv1'><span>Employee ID</span><select class ='smallDiv1' id='emp' name='emp' onchange = 'fetchMentor()'><option value='Select the employee'>Select the Employee</option></select></div><div class='smallDiv1'><span>Assign the Mentor</span><select class ='smallDiv1' id='Mentor' name='Mentor'></select></div><button type='button' id = 'Submit' value='Submit' onclick = 'chnage1()'>change</button>";
}
function viewTask2(taskId) {
	console.log("call2")
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "updateTaskStatus");
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send("taskId=" + taskId);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			let json = JSON.parse(xhr.responseText);
			console.log(json);
			taskview(json);
		}
	}

}


function pay() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "payAmount");
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			alert(xhr.responseText);
		}
	}
}

function taskview(a) {
	console.log("taskView");
	document.getElementById("shadowDiv").innerHTML = "<div id = 'CloseButton' onclick = 'removingShadow()'>&#215</div><div class='SmallDiv1'><span>Task Id</span><input type='text' id = 'TaskId' name='TaskId' readonly></div><div class='SmallDiv1'><span>Task Name</span><input type='text' id = 'TaskNameinput' name='TaskName' readonly></div><div class='SmallDiv1'><span>Task Description</span><input type='text' id = 'TaskDescriptioninput' name='TaskDescription' readonly></div><div class='SmallDiv1'><span>Assigned Date</span><input type='text' id = 'AssignedDate' name='AssignedDate' readonly></div><div class='SmallDiv1'><span>Assigned By</span><input type='text' id = 'AssignedBy' name='AssignedBy' readonly></div><div class='SmallDiv1'><span>Submission Date</span><input type='text' id = 'SubmissionDate' name='SubmissionDate' readonly></div><div class='SmallDiv1'><span>Task Status</span><input type='text' id = 'TaskStatus' name='TaskStatus' readonly></div>";
	console.log(document.getElementById("shadowDiv"));
	document.getElementById("TaskId").value = a[0].TaskId;
	document.getElementById("TaskNameinput").value = a[0].TaskName;
	document.getElementById("TaskDescriptioninput").value = a[0].TaskDescription;
	document.getElementById("AssignedDate").value = a[0].Assigneddate;
	document.getElementById("AssignedBy").value = a[0].AssignedBy;
	document.getElementById("SubmissionDate").value = a[0].submissionDate;
	document.getElementById("TaskStatus").value = a[0].TaskStatus;
}

function checkPage() {
	var a = window.location.href.split("/");
	a = a[a.length - 1]
	if (a == "index.html") {
		window.location.href = "index.html";
	}
	else if (a == "Emp.html") {
		window.location.href = "Emp.html";
	}
	else if (a == "Mentor.html") {
		window.location.href = "Mentor.html";
	}
}

function notification() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "GetNotification");
	xhr.send()
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.responseText != "") {
				var a = JSON.parse(xhr.responseText);
				console.log(a);
				if (a.statuscode == 200) {
					alert(a);
				} else { }
			}

		}
	}
}

function loadEmpId() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			var a = JSON.parse(xhr.responseText);
			console.log(a.Statuscode);
			if (a.Statuscode == 200) {
				document.getElementById("empId").value = a.id + "-" + a.name;
			} else {
				window.location.href = "login.html";
			}
		}
	}
	xhr.open('GET', 'Home');
	xhr.send();
}

function submit() {
	var data = {};
	data.empName = document.getElementById("EmployeeName").value;
	data.DOB = document.getElementById("DOB").value;
	data.salary = document.getElementById("EmployeeSalary").value;
	data.ph_no = document.getElementById("contact_no").value;
	data.email_id = document.getElementById("Email_id").value;
	data.Team = document.getElementById("AssignTeam").value;
	data.Mentor = document.getElementById("Mentor").value;
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = () => {
		console.log(xhr.readyState);
		if (xhr.readyState == 4) {
			alert(xhr.responseText);
		}
	}
	xhr.open("POST", "AddEmployee");
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(JSON.stringify(data));

}

function log() {
	var currentlyLoged = document.cookie.split("=");
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "logout");
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send("currentlyLoged=" + currentlyLoged[1]);
	xhr.onreadystatechange = () => {
		if (xhr.readyState == 4) {
			window.location.href = "login.html";
		}
	}
}
function RemoveEmployee() {
	document.getElementById("main").style.backgroundImage = "none";
	document.getElementById("main").style.backgroundColor = "White";
	document.getElementById("main").innerHTML = "<div id='add'><div id='add2'><div class='smallDiv1'><span>Employee ID</span><input type='text' id = 'empID' name='empID' required></div><div class='smallDiv1'><span>Enter the Reason</span><input type='text' id = 'reason' name='reason' required></div><button type='button' id = 'Submit' value='Submit' onclick = 'submit1()'>Submit</button>";
}
function submit1() {
	var empId = document.getElementById("empID").value;
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "RemoveEmployee?empId=" + empId);
	xhr.send();
	xhr.onreadystatechange = () => {
		if (xhr.readyState == 4) {
			console.log(xhr.responseText);
			if (confirm(xhr.responseText)) {
				xhr.responseText == 'Are you confirm to Remove Employee' ? Remove(empId) : null;
			}
		}
	}

}
function Remove(empId) {
	console.log(empId);
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "RemoveEmployee");
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send("empId="+empId);
	xhr.onreadystatechange = () => {
		if (xhr.readyState == 4) {
			alert(xhr.responseText);
		}
	}

}
function TaskAssign() {
	document.getElementById("main").style.backgroundImage = "none";
	document.getElementById("main").style.backgroundColor = "White";
	document.getElementById("main").innerHTML = "<div id='add'><br><br><div id='add1'><br><br><div class='smallDiv1'>Task ID<input type='text' id = 'taskId' name='taskId' readonly></div><div class='smallDiv1'><span>Task Name</span><input type='text' id = 'taskname' name='taskname' required></div><div class='smallDiv1'><span>Task Description</span><input type = 'text' id='taskDescription' name = 'taskDescription'></div><div class='smallDiv1'><span>Assign the Employee</span><select class ='smallDiv1' id='AssignEmployee' name='AssignEmployee'></select></div><div class='smallDiv1'>SubmissionDate<input type='date' id = 'subDate' name='subDate' required></div><div class='smallDiv1'><span>Task priority</span><select id='priority'><option value='Normal'>Normal</option><option value='Medium'>Medium</option><option value='Urgent'>Urgent</option></select></div><button type='button' id = 'Submit' value='Submit' onclick = 'taskAssign()'>Submit</button>";
}
function task() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "TaskAssign");
	xhr.send();
	xhr.onreadystatechange = () => {
		if (xhr.readyState == 4) {
			var task = JSON.parse(xhr.responseText);
			document.getElementById("taskId").value = task.taskId;
			var select = document.getElementById("AssignEmployee");
			select.innerHTML = "";
			console.log(task);
			for (var i = 0; i < task.employees.length; i++) {
				var option = document.createElement("option");
				option.innerText += task.employees[i].id;
				option.innerText += " " + task.employees[i].name;
				select.appendChild(option);
			}
		}
	}
}

function update1() {
	console.log("aaa");
	var xhr = new XMLHttpRequest();
	var currentlyLoged = document.cookie.split("=");
	console.log(currentlyLoged[1])
	xhr.open("POST", "update");
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send("currentlyLoged=" + currentlyLoged[1]);
	xhr.onreadystatechange = () => {
		if (xhr.readyState == 4) {
			let json = JSON.parse(xhr.responseText);
			console.log(json);
			console.log(json.empID);
			var a = document.getElementById("Whole2").childNodes;
			a[0].value = json.empID;
			//            document.getElementById("empId").value = json.empID;
			//            document.getElementById("empname").value = json.empName;
			//            document.getElementById("Date").value = json.DOB;
			//            document.getElementById("team").value = json.team;
			//            document.getElementById("email").value = json.Emailid;
			//            document.getElementById("contact_no").value = json.contactno;
			//            document.getElementById("reportees").value = json.ReportingTO;
			document.getElementById("empid").value = json.empID;
			document.getElementById("Empname").value = json.empName;
			document.getElementById("date").value = json.DOB;
			document.getElementById("Team").value = json.team;
			document.getElementById("Email").value = json.Emailid;
			document.getElementById("Contact_no").value = json.contactno;
			document.getElementById("Reportees").value = json.ReportingTO;

		}
	}
}
function salaryHike() {
	document.getElementById("main").style.backgroundImage = "none";
	document.getElementById("main").style.backgroundColor = "White";
	document.getElementById("main").innerHTML = "<div id='add'><br><br><div id='add2'><br><br><div class='smallDiv1'><span>Employee name</span><select class ='smallDiv1' id='empName' name='empName' '></select></div><div class='smallDiv1'>Hike Percentage<input type='number' id = 'Hike' name='Hike' required></div><button type='button' id = 'Submit' value='Submit' onclick = 'hello()'>Submit</button> ";

}
function salaryHike1() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "hike");
	xhr.send();
	xhr.onreadystatechange = () => {
		if (xhr.readyState == 4) {
			var task = JSON.parse(xhr.responseText);
			var select = document.getElementById("empName");
			select.innerHTML = "";
			console.log(task);
			for (var i = 0; i < task.employees.length; i++) {
				var option = document.createElement("option");
				option.innerText += task.employees[i].id;
				option.innerText += " " + task.employees[i].name;
				select.appendChild(option);
			}
		}
	}
}
function hello() {

	var empId = document.getElementById("empName").value;
	var Hike = document.getElementById("Hike").value;
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "hike");
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send("empId=" + empId + "&hike=" + Hike);
	console.log("check", empId, Hike);
	xhr.onreadystatechange = () => {
		if (xhr.readyState == 4) {
			alert(xhr.responseText);
		}
	}

}
function Request() {
	var iframe = document.createElement("iframe");
	iframe.src = "Request.html";
	document.getElementById("main").innerHTML = "";
	iframe.height = "100%";
	iframe.width = "100%";
	iframe.style.border = "0";
	document.getElementById("main").appendChild(iframe);
}

function Apply() {
	var iframe = document.createElement("iframe");
	iframe.src = "Apply.html";
	document.getElementById("main").innerHTML = "";
	iframe.height = "100%";
	iframe.width = "100%";
	iframe.style.border = "0";
	document.getElementById("main").appendChild(iframe);

}

function view() {
	console.log("a");
	document.getElementById("main").style.backgroundImage = "none";
	document.getElementById("main").style.backgroundColor = "White";
	var iframe = document.createElement("iframe");
	iframe.src = "view.html";
	document.getElementById("main").innerHTML = "";
	iframe.height = "100%";
	iframe.width = "100%";
	iframe.style.border = "0";
	document.getElementById("main").appendChild(iframe);
}

function back() {
	if (window.location.contains(index.html)) {
		window.location.href == "login.html";
	} else {
		window.location.href == "index.html";
	}
}

function showingPassword() {
	var x = document.getElementById("userPass");
	if (x.type === "password") {
		document.getElementById("showPassword").remove();
		var icon = document.createElement("i");
		document.getElementById("pass").appendChild(icon);
		icon.setAttribute("id", "showPassword");
		icon.setAttribute("class", "fa-regular fa-eye");
		icon.addEventListener("click", showingPassword);
		x.type = "text";
	} else {
		document.getElementById("showPassword").remove();
		var icon = document.createElement("i");
		document.getElementById("pass").appendChild(icon);
		icon.setAttribute("id", "showPassword");
		icon.setAttribute("class", "fa-regular fa-eye-slash");
		icon.addEventListener("click", showingPassword);
		x.type = "password";
	}
}
function leave() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "Apply");
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			let json = JSON.parse(xhr.responseText);
			var select = document.getElementById("LeaveType");
			select.innerHTML = "";
			for (var i = 0; i < json.length; i++) {
				var option = document.createElement("option");
				option.text = json[i];
				option.value = json[i];
				select.appendChild(option);
			}
		}

	}
}

function Apply1() {
	console.log("a");
	var a = document.getElementById("empId").value.split("-");
	var data = {};
	data.empId = a[0]
	data.empName = a[1];
	data.fromDate = document.getElementById("dateFrom").value;
	data.toDate = document.getElementById("dateTo").value;
	data.leavetype = document.getElementById("LeaveType").value;
	data.reason = document.getElementById("Reason").value;

	var xhr = new XMLHttpRequest();


	xhr.onreadystatechange = () => {
		if (xhr.readyState == 4) {
			alert(xhr.responseText);
		}
	}
	xhr.open("POST", "Apply");
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(JSON.stringify(data));
}
function taskAssign() {
	var a = document.getElementById("AssignEmployee").value.split(" ");
	var data = {};
	data.empId = a[0];
	data.TaskId = document.getElementById("taskId").value;
	data.TaskName = document.getElementById("taskname").value;
	data.TaskDescription = document.getElementById("taskDescription").value;
	data.submissionDate = document.getElementById("subDate").value;
	data.TaskPriority = document.getElementById("priority").value;

	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = () => {
		if (xhr.readyState == 4) {
			alert(xhr.responseText);
		}
	}
	xhr.open("POST", "TaskAssign");
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(JSON.stringify(data));
}
function cancelpage() {
	var a = window.parent.location.href.split("/");
	var b = a[a.length-1];
	console.log(b);
	if(b=="Emp.html"){
	document.body.innerHTML = window.location.href = "Emp.html";
	}
	else if(b=="index.html")
	document.body.innerHTML = window.location.href = "index.html"
	else if(b=="Manager.HTML")
	document.body.innerHTML = window.location.href = "Manager.HTML"
	else if(b=="Mentor.html")
	document.body.innerHTML = window.location.href = "Mentor.html"
	
}




function viewTask() {
	console.log("aa")
	var xhr = new XMLHttpRequest();
	xhr.open("post", "viewTask");
	xhr.send();
	xhr.onreadystatechange = () => {
		console.log(xhr.readyState);
		if (xhr.readyState == 4) {
			document.getElementById("main").style.backgroundImage = "white";
			document.getElementById("main").style.backgroundImage = "none";
			console.log(xhr.responseText);
			var json = JSON.parse(xhr.responseText);
			var parent = document.createElement("div");
			parent.setAttribute("id", "Need");
			document.getElementById("main").innerHTML = '';
			document.getElementById("main").appendChild(parent);
			for (let i = 0; i < json.length; i++) {
				var divName = "EmpId" + i;

				var ParentDiv = document.createElement("div");
				document.getElementById("Need").appendChild(ParentDiv);
				ParentDiv.setAttribute("class", "element2");
				ParentDiv.setAttribute("id", divName);
				var TaskId = document.createElement("span");
				document.getElementById(divName).appendChild(TaskId);
				TaskId.innerText = json[i].TaskId;

				var TaskName = document.createElement("span");
				document.getElementById(divName).appendChild(TaskName);
				TaskName.innerText = json[i].TaskName;

				var TaskDescription = document.createElement("span");
				TaskDescription.setAttribute("id", "reason");
				document.getElementById(divName).appendChild(TaskDescription);
				TaskDescription.innerText = json[i].TaskDescription;

				var AssignedTo = document.createElement("span");
				document.getElementById(divName).appendChild(AssignedTo);
				AssignedTo.innerText = json[i].AssignedTo;

				var AssignedBy = document.createElement("span");
				document.getElementById(divName).appendChild(AssignedBy);
				AssignedBy.innerText = json[i].AssignedBy;


				var Assigneddate = document.createElement("span");
				document.getElementById(divName).appendChild(Assigneddate);
				Assigneddate.innerText = json[i].Assigneddate;


				var submissionDate = document.createElement("span");
				document.getElementById(divName).appendChild(submissionDate);
				submissionDate.innerText = json[i].submissionDate;


				var Priority = document.createElement("span");
				document.getElementById(divName).appendChild(Priority);
				Priority.innerText = json[i].Priority;


				var TaskStatus = document.createElement("span");
				document.getElementById(divName).appendChild(TaskStatus);
				TaskStatus.innerText = json[i].TaskStatus;
			}
		}

	}
}

function viewTask1() {
	console.log("aa")
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "fetchTask");
	xhr.send();
	xhr.onreadystatechange = () => {
		console.log(xhr.readyState);
		if (xhr.readyState == 4) {
			document.getElementById("main").style.backgroundImage = "white";
			document.getElementById("main").style.backgroundImage = "none";
			console.log(xhr.responseText);
			var json = JSON.parse(xhr.responseText);
			var parent = document.createElement("div");
			parent.setAttribute("id", "Need");
			document.getElementById("main").innerHTML = '';
			document.getElementById("main").appendChild(parent);
			for (let i = 0; i < json.length; i++) {
				var divName = "EmpId" + i;

				var ParentDiv = document.createElement("div");
				document.getElementById("Need").appendChild(ParentDiv);
				ParentDiv.setAttribute("class", "element2");
				ParentDiv.setAttribute("id", divName);
				var TaskId = document.createElement("span");
				document.getElementById(divName).appendChild(TaskId);
				TaskId.innerText = json[i].TaskId;

				var TaskName = document.createElement("span");
				document.getElementById(divName).appendChild(TaskName);
				TaskName.innerText = json[i].TaskName;

				var TaskDescription = document.createElement("span");
				TaskDescription.setAttribute("id", "reason");
				document.getElementById(divName).appendChild(TaskDescription);
				TaskDescription.innerText = json[i].TaskDescription;

				var AssignedTo = document.createElement("span");
				document.getElementById(divName).appendChild(AssignedTo);
				AssignedTo.innerText = json[i].AssignedTo;

				var AssignedBy = document.createElement("span");
				document.getElementById(divName).appendChild(AssignedBy);
				AssignedBy.innerText = json[i].AssignedBy;


				var Assigneddate = document.createElement("span");
				document.getElementById(divName).appendChild(Assigneddate);
				Assigneddate.innerText = json[i].Assigneddate;


				var submissionDate = document.createElement("span");
				document.getElementById(divName).appendChild(submissionDate);
				submissionDate.innerText = json[i].submissionDate;


				var Priority = document.createElement("span");
				document.getElementById(divName).appendChild(Priority);
				Priority.innerText = json[i].Priority;


				var TaskStatus = document.createElement("span");
				document.getElementById(divName).appendChild(TaskStatus);
				TaskStatus.innerText = json[i].TaskStatus;
			}
		}

	}
}