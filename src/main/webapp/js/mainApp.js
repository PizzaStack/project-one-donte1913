function initLogin(){
        document.getElementById("main").innerHTML = 
        `
        <form name="employee" style="position: absolute; left: auto" action="servlet" method="Post">
            <p><u>Employee Login</u></p>
            Username:<br /><input type="text" name="eusername" /><br />
            Password:<br /><input type="password" name="epswrd" /><br /><br />
            <input type="button" onclick="checkEmployee(this.form)" value="Login" />
            <input type="reset" value="Cancel" />
        </form>

        <form name="manager" style="position: absolute; left: 250px">
            <p><u>Manager Login</u></p>
            Username:<br /><input type="text" name="musername" /><br />
            Password:<br /><input type="password" name="mpswrd" /><br /><br />
            <input type="button" onclick="checkManager(this.form)" value="Login" />
            <input type="reset" value="Cancel" />
        </form>
        `;

        
    (function(){
        let xhr = new XMLHttpRequest();

        xhr.onreadystatechange = function(){
            if(this.readyState === XMLHttpRequest.DONE && this.status === 200){

            }
        }

        xhr.open("POST", "http://localhost:8080/Project_1_Servlet/verify");

        xhr.send("OFF");
    })();
}
