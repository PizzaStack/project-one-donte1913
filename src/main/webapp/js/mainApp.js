var employeeId = "";
var employeeFirstName = "";
var employeeLastName = "";
var employeeEmailAddress = "";
var employeeAddress = "";

var managerId = "";
var managerFirstName = "";
var managerLastName = "";
var managerEmailAddress = "";
var managerAddress = "";


function initLogin(){
    document.getElementById("main").innerHTML =
        `
    <form name="employee" style="position: absolute; left: auto">
        <p><u>Employee Login</u></p>
        Username:<br /><input type="text" name="username" /><br />
        Password:<br /><input type="password" name="password" /><br /><br />
        <input type="button" onclick="checkEmployee(username, password)" value="Login" />
        <input type="reset" value="Cancel" />
    </form>
    <form name="manager" style="position: absolute; left: 250px">
        <p><u>Manager Login</u></p>
        Username:<br /><input type="text" name="username" /><br />
        Password:<br /><input type="password" name="password" /><br /><br />
        <input type="button" onclick="checkManager(username, password)" value="Login" />
        <input type="reset" value="Cancel" />
    </form>
    `;


    (function(){
        let xhr = new XMLHttpRequest();

        xhr.onreadystatechange = function(){
            if(this.readyState === XMLHttpRequest.DONE && this.status === 200){

            }
        }

        xhr.open("POST", "http://localhost:8080/Project_1_Servlet_war/connection");

        xhr.send("OFF");
    })();
}

function checkEmployee(username, password){
    if(username.value == "") {
        alert("Invalid username. Please try again");
    } else if(password.value == ""){
        alert("Invalid password. Please try again");
    } else {

        (function(){
            let xhr = new XMLHttpRequest();

            xhr.onreadystatechange = function() {
                if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                    let userInfo = JSON.parse(xhr.responseText);
                    if(userInfo.verified == "true"){
                        employeeId = userInfo.id;
                        employeeFirstName = userInfo.firstname;
                        employeeLastName = userInfo.lastname;
                        employeeEmailAddress = userInfo.emailaddress;
                        employeeAddress = userInfo.address;
                        employeeLogin();
                    } else {
                        alert("Incorrect credentials, try again!");
                    }
                }
            }

            xhr.open("POST", "http://localhost:8080/Project_1_Servlet_war/verifyn", false);

            let credentialArray = [username.value, password.value, "employee"];

            xhr.send(credentialArray);

        })();

    }
}

function checkManager(username, password){
    if(username.value == "") {
        alert("Invalid username. Please try again");
    } else if(password.value == ""){
        alert("Invalid password. Please try again");
    } else {
        (function(){
            let xhr = new XMLHttpRequest();

            xhr.onreadystatechange = function() {
                if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                    let userInfo = JSON.parse(xhr.responseText);
                    if(userInfo.verified == "true"){
                        managerId = userInfo.id;
                        managerFirstName = userInfo.firstname;
                        managerLastName = userInfo.lastname;
                        managerEmailAddress = userInfo.emailaddress;
                        managerAddress = userInfo.address;
                        managerLogin();
                    } else {
                        alert("Incorrect credentials, try again!");
                    }
                }
            }

            xhr.open("POST", "http://localhost:8080/Project_1_Servlet_war/verifyn", false);

            let credentialArray = [username.value, password.value, "manager"];

            xhr.send(credentialArray);

        })();
    }
}

function employeeLogin(){
    getEmployeeReimbursementById(employeeId, "pending");
    document.getElementById("main").innerHTML = `
        <nav id="employeeNav">
        <div class="row">
            <div class="col-md">
                <p id="employeeHome" onclick="employeeLogin()">Home</p>
            </div>
            <div class="col-md">
                <p id="employeeInformation" onclick="employeeManageInformation()">My Information</p>
            </div>
            <div class="col-md">
                <p id="employeeReimbursements" onclick="employeeManageReimbursements()">Manage Reimbursements</p>
            </div>
            <div class="col-md">
                <p id="logout" onclick="initLogin()">Log Out</p>
            </div>
        </div>
        </nav>
        <div id="welcomeEmployee">
            <h2 id="welcomeFirstLast">Welcome ${employeeFirstName} ${employeeLastName}!</h2>
            <h3 id="pendingReimbursementRequests">Pending Reimbursement Requests:</h3>
            <div id="pendingRequests">
            <table class="table" id="pendingReimbursementTable">
            <thead>
              <tr>
                <th scope="col">Reimbursement Id</th>
                <th scope="col">Title</th>
                <th scope="col">Description</th>
                <th scope="col">Amount</th>
                <th scope="col">Date</th>
                <th scope="col">Status</th>
                <th scope="col">Receipt</th>
              </tr>
            </thead>
            <tbody id="pendingReimbursementTableBody">

            </tbody>
            </table>
            </div>
        </div>
    `
}

function managerLogin(){
    getAllEmployees();
    document.getElementById("main").innerHTML = `
        <nav id="managerNav">
        <div class="row">
            <div class="col-md">
                <p id="managerHome" onclick="managerLogin()">Home</p>
            </div>
            <div class="col-md">
                <p id="managerReimbursements" onclick="managerManageReimbursements()">Manage Reimbursements</p>
            </div>
            <div class="col-md">
            <p id="logout" onclick="initLogin()">Log Out</p>
            </div>
        </nav>
        </div>
        <div id="welcomeManager">
        <h2 id="welcomeFirstLast">Welcome ${managerFirstName} ${managerLastName}!</h2>
        <h3 id="employeeList">Employee List:</h3>
        <div id="allEmployees">
            <table class="table" id="allEmployeesTable">
            <thead>
              <tr>
                <th scope="col">Employee Id</th>
                <th scope="col">First Name</th>
                <th scope="col">Last Name</th>
                <th scope="col">Address</th>
                <th scope="col">Email Address</th>
              </tr>
            </thead>
            <tbody id="allEmployeesTableBody">

            </tbody>
            </table>
            </div>
        </div>
    `
}

function employeeManageReimbursements(){
    document.getElementById("main").innerHTML = `
    <nav id="employeeNav">
    <div class="row">
        <div class="col-md">
            <p id="employeeHome" onclick="employeeLogin()">Home</p>
        </div>
        <div class="col-md">
            <p id="employeeResolvedReimbursementsId" onclick="employeeResolvedReimbursements()">View Resolved Reimbursements</p>
        </div>
        <div class="col-md">
        <p id="logout" onclick="initLogin()">Log Out</p>
        </div>
    </nav>
    <div id="submitReimbursement">
        <form id="reimbursementForm" enctype="multipart/form-data">
        <h2>
        Submit Reimbursement:
        </h2>
        Title:<br>
        <input id="titleBox" type="text" name="titleBox" required><br>
        Description:<br>
        <textarea id="descriptionBox" type="text" name="description" required></textarea><br>
        Amount:<br>
        <input id="amountBox" type="number" min="0.01" step="0.01" max="10000" name="amount" required><br>   
        <br>
        <input id="reimbursementImage" name="file" type="file" accept=".png,.jpg,.jpeg" required><br>
        <br>
        <button type="button" id="submitRequest" onclick="uploadReimbursementInit(titleBox, description, amount, file)">Submit</button>
        </form>
    </div>
    </div>
    `
}

function managerManageReimbursements(){
    document.getElementById("main").innerHTML = `
    <nav id="managerNav">
    <div class="row">
        <div class="col-md">
            <p id="managerHome" onclick="managerLogin()">Home</p>
        </div>
        <div class="col-md">
            <p id="managerPendingReimbursementsId" onclick="managerPendingReimbursements()">View Pending Reimbursements</p>
        </div>
        <div class="col-md">
            <p id="managerResolvedReimbursementsId" onclick="managerResolvedReimbursements()">View Resolved Reimbursements</p>
        </div>
        <div class="col-md">
        <p id="logout" onclick="initLogin()">Log Out</p>
        </div>
    </nav>
    <div id="viewReimbursementById">
        <form id="reimbursementFormById" enctype="multipart/form-data">
            <h2>
                View Reimbursements by Employee Id:
            </h2>
            <div class="row">
                <div class="col-md">
                    <p id="employeeIdReimbursementForm">Employee Id:</p><br>
                </div>
                <div class="col-md">
                    <input id="employeeIdBox" type="number" min="1" step="1" max="10000" name="employeeIdBox" required>
                </div>
                <div class="col-md">
                    <button type="button" id="submitRequest2" onclick="getEmployeeReimbursementById(employeeIdBox.value,'all')">Submit</button>
                </div>
            </div>
        </form>
    </div>
    <div id="reimbursementListById">
    <table class="table" id="allEmployeeReimbursement">
    <thead>
      <tr>
        <th scope="col">Reimbursement Id</th>
        <th scope="col">Title</th>
        <th scope="col">Description</th>
        <th scope="col">Amount</th>
        <th scope="col">Date</th>
        <th scope="col">Status</th>
        <th scope="col">Manager Id</th>
        <th scope="col">Receipt</th>
        <th scope="col">Action</th>
      </tr>
    </thead>
    <tbody id="allReimbursementTableBody">

    </tbody>
    </table>
    </div>
    `
}

function employeeManageInformation(){
    document.getElementById("main").innerHTML = `
    <nav id="employeeNav">
    <div class="row">
        <div class="col-md">
            <p id="employeeHome" onclick="employeeLogin()">Home</p>
        </div>
        <div class="col-md">
        <p id="logout" onclick="initLogin()">Log Out</p>
        </div>
    </nav>
    <h2 id="personalInformation">Personal Information:</h2>
    <form id="employeeInformationForm">
    <div class="row">
    <div class="col-md">
        <p class="employeeInfo">Employee Id (Cannot Change):</p>
    </div>
    <div class="col-md">
        <input id="employeeId" value="${employeeId}" readonly><br>
    </div>
    </div>
    <div class="row">
        <div class="col-md">
            <p class="employeeInfo">First Name:</p>
        </div>
        <div class="col-md">
            <input id="employeeFirstName" name="newEmployeeFirstName" value="${employeeFirstName}"><br>
        </div>
    </div>
    <div class="row">
    <div class="col-md">
        <p class="employeeInfo">Last Name:</p>
    </div>
    <div class="col-md">
        <input id="employeeLastName" name="newEmployeeLastName" value="${employeeLastName}"><br>
    </div>
    </div>
    <div class="row">
    <div class="col-md">
        <p class="employeeInfo">Email Address:</p>
    </div>
    <div class="col-md">
        <input id="employeeEmailAddress" name="newEmployeeEmailAddress" value="${employeeEmailAddress}"><br>
    </div>
    </div>
    <div class="row">
    <div class="col-md">
        <p class="employeeInfo">Address:</p>
    </div>
    <div class="col-md">
        <input id="employeeAddress" name="newEmployeeAddress" value="${employeeAddress}"><br>
    </div>
    </div>
    <div class="row">
    <div class="col-md">
    
    </div>
    <div class="col-md">
        <button id="saveEmployeeInfoChangesButton" type="button" onclick="saveEmployeeInfoChanges(newEmployeeFirstName, newEmployeeLastName, newEmployeeEmailAddress, newEmployeeAddress)">Save Changes</button>
    </div>
    </div>

    </form>
    `
}




function getAllEmployees() {
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {
        if(this.readyState === 4 && this.status === 200){
            let jsonArray = [];
            jsonArray = JSON.parse(this.responseText);
            let allEmployeesTableBodyDOMManipulation = ``;
            for(i in jsonArray){
                json = jsonArray[i];
                allEmployeesTableBodyDOMManipulation = allEmployeesTableBodyDOMManipulation + `
            <tr>
                <td>${json.employeeid}</td>
                <td>${json.firstname}</td>
                <td>${json.lastname}</td>
                <td>${json.address}</td>
                <td>${json.emailaddress}</td>
            </tr>
            `
            }

            document.getElementById("allEmployeesTableBody").innerHTML = allEmployeesTableBodyDOMManipulation;
        }
    };

    xhr.open("GET", "http://localhost:8080/Project_1_Servlet_war/employee");

    xhr.send();

}

function getEmployeeReimbursementById(id, type){
    if(id === ""){
        alert("Enter in an Employee Id!");
    } else {
        employeeId = id;
        var xhr = new XMLHttpRequest();

        xhr.onreadystatechange = function() {
            if(this.readyState === XMLHttpRequest.DONE && this.status === 200){

                if(type === "pending"){
                    let jsonArray = [];
                    jsonArray = JSON.parse(this.responseText);
                    let pendingReimbursementTableBodyDOMManipulation = ``;
                    for (i in jsonArray){
                        json = jsonArray[i];
                        let newLocation = json.location.replace(/\\/g,"/");
                        pendingReimbursementTableBodyDOMManipulation = pendingReimbursementTableBodyDOMManipulation + `
              <tr>
                <td>${json.reimbursementid}</td>
                <td>${json.title}</td>
                <td>${json.description}</td>
                <td>$${json.amount}</td>
                <td>${json.date}</td>
                <td>Pending</th>
                <td><button class="reimbursementButton" onclick="getReimbursementImage('${newLocation}')">View Receipt</button></td>
              </tr>
           `
                    }

                    document.getElementById("pendingReimbursementTableBody").innerHTML = pendingReimbursementTableBodyDOMManipulation;

                } else if(type === "resolved"){
                    let jsonArray = [];
                    jsonArray = JSON.parse(this.responseText);
                    let resolvedReimbursementTableBodyDOMManipulation = ``;
                    for (i in jsonArray){
                        json = jsonArray[i];
                        let newLocation = json.location.replace(/\\/g,"/");
                        resolvedReimbursementTableBodyDOMManipulation = resolvedReimbursementTableBodyDOMManipulation + `
              <tr>
                <td>${json.reimbursementid}</td>
                <td>${json.title}</td>
                <td>${json.description}</td>
                <td>$${json.amount}</td>
                <td>${json.date}</td>
                <td>Approved</th>
                <td><button class="reimbursementButton" onclick="getReimbursementImage('${newLocation}')">View Receipt</button></td>
              </tr>
           `
                    }

                    document.getElementById("resolvedReimbursementTableBody").innerHTML = resolvedReimbursementTableBodyDOMManipulation;

                } else if(type === "all"){
                    type = "all";
                    let jsonArray = [];
                    jsonArray = JSON.parse(this.responseText);
                    if(jsonArray.length === 0){
                        alert("There are no reimbursement requests associated with that Employee Id!");
                    }
                    let allReimbursementTableBodyDOMManipulation = ``;
                    for (i in jsonArray){
                        json = jsonArray[i];
                        let status = "";
                        let button = "";
                        let managerid = "";
                        if(json.status === 0){
                            status = "Pending";
                            button = `<td><button class="reimbursementButton" onclick="approveRequest('id',${json.reimbursementid},${managerId},'approve')">Approve</button><button class="reimbursementButton" onclick="denyRequest('id',${json.reimbursementid},${managerId},'deny')">Deny</button></td>`
                        } else {
                            status = "Approved";
                            button = `<td></td>`;
                            managerid = json.managerid;
                        }
                        let newLocation = json.location.replace(/\\/g,"/");
                        allReimbursementTableBodyDOMManipulation = allReimbursementTableBodyDOMManipulation + `
              <tr>
                <td>${json.reimbursementid}</td>
                <td>${json.title}</td>
                <td>${json.description}</td>
                <td>$${json.amount}</td>
                <td>${json.date}</td>
                <td>${status}</td>
                <td>${managerid}</td>
                <td><button class="reimbursementButton" onclick="getReimbursementImage('${newLocation}')">View Receipt</button></td>
                ${button}
              </tr>
           `
                    }
                    document.getElementById("allReimbursementTableBody").innerHTML = allReimbursementTableBodyDOMManipulation;
                }
            }
        };

        xhr.open("POST", "http://localhost:8080/Project_1_Servlet_war/downloadReimbursementServlet");

        let sendParameters = [id, type];

        xhr.send(sendParameters);
    }
}

function saveEmployeeInfoChanges(newEmployeeFirstName, newEmployeeLastName, newEmployeeEmailAddress, newEmployeeAddress){
    if(employeeFirstName === newEmployeeFirstName.value && employeeLastName === newEmployeeLastName.value && employeeEmailAddress === newEmployeeEmailAddress.value && employeeAddress === newEmployeeAddress.value){
        alert("No changes made to save!");
    } else if(newEmployeeFirstName.value == "" || newEmployeeLastName.value == "" || newEmployeeEmailAddress == "" || newEmployeeAddress == ""){
        alert("At least one of the fields is empty, try again!");
    } else {

        (function() {
            let xhr = new XMLHttpRequest();

            xhr.onreadystatechange = function(){
                if(this.readyState === XMLHttpRequest.DONE && this.status === 200){
                    if(xhr.responseText == "true"){
                        employeeFirstName = newEmployeeFirstName.value;
                        employeeLastName = newEmployeeLastName.value;
                        employeeEmailAddress = newEmployeeEmailAddress.value;
                        employeeAddress = newEmployeeAddress.value;
                        alert("Changes saved!");
                    } else {
                        alert("Oops something went wrong, try again!");
                    }
                }
            };

            xhr.open("POST", "http://localhost:8080/Project_1_Servlet_war/singleEmployee", false);

            let employeeInfoChanges = JSON.stringify({"employeeid":employeeId,"firstname":newEmployeeFirstName.value,"lastname":newEmployeeLastName.value,"emailaddress":newEmployeeEmailAddress.value,"address":newEmployeeAddress.value});

            xhr.send(employeeInfoChanges);

        })();

    }
}

function uploadReimbursementInit(titleBox, description, amount, file){
    let imageList = file.files;
    if(titleBox.value == ""){
        alert("Enter in a title for the request!");
    } else if(description.value == ""){
        alert("Enter in a description for the request!");
    } else if(amount.value == ""){
        alert("Enter in an amount for the request!");
    } else if(imageList.length == 0){
        alert("Attach a receipt for the request!");
    } else {
        uploadReimbursement((copyForm) => {
            let xhr = new XMLHttpRequest();

            xhr.onreadystatechange = function(){
                if(this.readyState === XMLHttpRequest.DONE && this.status === 200){
                    if(this.responseText == "true"){
                        alert("Upload successful!");
                        document.getElementById("titleBox").value = "";
                        document.getElementById("descriptionBox").value = "";
                        document.getElementById("amountBox").value = "";
                        document.getElementById("reimbursementImage").value = "";
                    }
                }
            };

            xhr.open("POST","http://localhost:8080/Project_1_Servlet_war/uploadReimbursement", false);


            xhr.send(copyForm);
        });
    }
}

function uploadReimbursement(callback){

    let copyForm = new FormData(document.getElementById("reimbursementForm"));
    copyForm.append("employeeid",employeeId);

    callback(copyForm);

}

function getReimbursementImage(path){
    console.log(path);
    (function(){
        let xhr = new XMLHttpRequest();

        xhr.onreadystatechange = function(){
            if(this.readyState === XMLHttpRequest.DONE && this.status === 200){
                window.open("http://localhost:8080/Project_1_Servlet_war/getReceipt");
            }
        };

        xhr.open("POST","http://localhost:8080/Project_1_Servlet_war/getReceipt", false);


        xhr.send(path);
    })();

}

function employeeResolvedReimbursements(){
    getEmployeeReimbursementById(employeeId,"resolved");
    document.getElementById("main").innerHTML = `
    <nav id="employeeNav">
    <div class="row">
        <div class="col-md">
            <p id="employeeHome" onclick="employeeLogin()">Home</p>
        </div>
        <div class="col-md">
        <p id="logout" onclick="initLogin()">Log Out</p>
        </div>
    </div>    
    </nav>
    <div id="welcomeEmployee">
            <h3 id="resolvedReimbursementRequests">Resolved Reimbursement Requests:</h3>
            <div id="resolvedRequests">
            <table class="table" id="resolvedReimbursementTable">
            <thead>
              <tr>
                <th scope="col">Reimbursement Id</th>
                <th scope="col">Title</th>
                <th scope="col">Description</th>
                <th scope="col">Amount</th>
                <th scope="col">Date</th>
                <th scope="col">Status</th>
                <th scope="col">Receipt</th>
              </tr>
            </thead>
            <tbody id="resolvedReimbursementTableBody">

            </tbody>
            </table>
            </div>
        </div>
        `
}

function approveRequest(source,reimbursementId, managerId, type) {
    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {
        if(this.readyState === XMLHttpRequest.DONE && this.status === 200){
            if(this.responseText === "true"){
                alert("Reimbursement request approved!");
                if(source === "all"){
                    managerPendingReimbursements();
                } else if(source === "id"){
                    getEmployeeReimbursementById(employeeId, "all");
                }
            }
        }
    };

    xhr.open("POST","http://localhost:8080/Project_1_Servlet_war/approval");

    let requestInfo = [reimbursementId, managerId, type];

    xhr.send(requestInfo);
}

function denyRequest(source, reimbursementId, managerId, type) {
    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {
        if(this.readyState === XMLHttpRequest.DONE && this.status === 200){
            if(this.responseText === "true"){
                alert("Reimbursement request denied!");
                if(source === "all"){
                    managerPendingReimbursements();
                } else if(source === "id"){
                    getEmployeeReimbursementById(employeeId, "all");
                }
            }
        }
    };

    xhr.open("POST","http://localhost:8080/Project_1_Servlet_war/approval");

    let requestInfo = [reimbursementId, managerId, type];

    xhr.send(requestInfo);
}

function managerPendingReimbursements() {
    document.getElementById("main").innerHTML = `
    <nav id="managerNav">
    <div class="row">
        <div class="col-md">
            <p id="managerHome" onclick="managerLogin()">Home</p>
        </div>
        <div class="col-md">
        <p id="logout" onclick="initLogin()">Log Out</p>
        </div>
    </nav>
    <div id="pendingRRequests">
            <h3 id="pendingReimbursementRequests">Pending Reimbursement Requests:</h3>
            <div id="pendingRequests">
            <table class="table" id="pendingReimbursementTable">
            <thead>
              <tr>
                <th scope="col">Reimbursement Id</th>
                <th scope="col">Employee Id</th>
                <th scope="col">Title</th>
                <th scope="col">Description</th>
                <th scope="col">Amount</th>
                <th scope="col">Date</th>
                <th scope="col">Status</th>
                <th scope="col">Receipt</th>
                <th scope="col">Action</th>
              </tr>
            </thead>
            <tbody id="pendingReimbursementTableBody">

            </tbody>
            </table>
            </div>
        </div>
    `

    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {
        if(this.readyState === XMLHttpRequest.DONE && this.status === 200){


            let jsonArray = [];
            jsonArray = JSON.parse(this.responseText);
            let pendingReimbursementTableBodyDOMManipulation = ``;
            for (i in jsonArray){
                json = jsonArray[i];
                let newLocation = json.location.replace(/\\/g,"/");
                pendingReimbursementTableBodyDOMManipulation = pendingReimbursementTableBodyDOMManipulation + `
                  <tr>
                    <td>${json.reimbursementid}</td>
                    <td>${json.employeeid}</td>
                    <td>${json.title}</td>
                    <td>${json.description}</td>
                    <td>$${json.amount}</td>
                    <td>${json.date}</td>
                    <td>Pending</th>
                    <td><button class="reimbursementButton" onclick="getReimbursementImage('${newLocation}')">View Receipt</button></td>
                    <td><button class="reimbursementButton" onclick="approveRequest('all',${json.reimbursementid},${managerId},'approve')">Approve</button><button class="reimbursementButton" onclick="denyRequest('all',${json.reimbursementid},${managerId},'deny')">Deny</button></td>
                </tr>
               `
            }

            document.getElementById("pendingReimbursementTableBody").innerHTML = pendingReimbursementTableBodyDOMManipulation;

        }

    };
    xhr.open("POST", "http://localhost:8080/Project_1_Servlet_war/allReimbursement");

    xhr.send("pending");
}

function managerResolvedReimbursements() {
    document.getElementById("main").innerHTML = `
    <nav id="managerNav">
    <div class="row">
        <div class="col-md">
            <p id="managerHome" onclick="managerLogin()">Home</p>
        </div>
        <div class="col-md">
        <p id="logout" onclick="initLogin()">Log Out</p>
        </div>
    </nav>
    <div id="resolvedRRequests">
            <h3 id="resolvedReimbursementRequests">Resolved Reimbursement Requests:</h3>
            <div id="resolvedRequests">
            <table class="table" id="resolvedReimbursementTable">
            <thead>
              <tr>
                <th scope="col">Reimbursement Id</th>
                <th scope="col">Employee Id</th>
                <th scope="col">Title</th>
                <th scope="col">Description</th>
                <th scope="col">Amount</th>
                <th scope="col">Date</th>
                <th scope="col">Status</th>
                <th scope="col">Manager Id</th>
                <th scope="col">Receipt</th>
              </tr>
            </thead>
            <tbody id="resolvedReimbursementTableBody">

            </tbody>
            </table>
            </div>
        </div>
    `

    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {
        if(this.readyState === XMLHttpRequest.DONE && this.status === 200){


            let jsonArray = [];
            jsonArray = JSON.parse(this.responseText);
            let resolvedReimbursementTableBodyDOMManipulation = ``;
            for (i in jsonArray){
                json = jsonArray[i];
                let newLocation = json.location.replace(/\\/g,"/");
                resolvedReimbursementTableBodyDOMManipulation = resolvedReimbursementTableBodyDOMManipulation + `
                  <tr>
                    <td>${json.reimbursementid}</td>
                    <td>${json.employeeid}</td>
                    <td>${json.title}</td>
                    <td>${json.description}</td>
                    <td>$${json.amount}</td>
                    <td>${json.date}</td>
                    <td>Approved</th>
                    <td>${json.managerid}</td>   
                    <td><button class="reimbursementButton" onclick="getReimbursementImage('${newLocation}')">View Receipt</button></td> 
                </tr>
               `
            }

            document.getElementById("resolvedReimbursementTableBody").innerHTML = resolvedReimbursementTableBodyDOMManipulation;

        }

    };
    xhr.open("POST", "http://localhost:8080/Project_1_Servlet_war/allReimbursement");

    xhr.send("resolved");
}