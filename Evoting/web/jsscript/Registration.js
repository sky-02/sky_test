let userid,pwd,username,mobile,address,city,email,cpwd,gender;
function addUser(){
    username=$("#username").val();
    pwd=$("#password").val();
    cpwd=$("#cpassword").val();
    address=$("#address").val();
    city=$("#city").val();
    email=$("#email").val();
    mobile=$("#mobile").val();
    userid=$("#adhar").val();
    gender=$('input[name="gender"]:checked').val();
    if(!validate()){
         swal("error","fill all the textfield","error");
        return;
    }
    if(!checkPwd()){
         swal("error","password and retype pwd are different","error");
        return;
    }
    if(!checkMail()){
         swal("error","plzz enter valid mail","error");
        return;
    }
    if(!checkNo()){
        return;
    }
    
    let data={"username":username,"gender":gender,"id":userid,"city":city,"email":email,"pwd":pwd,"address":address,"mobile":mobile};
   xhr=$.post("RegistrationServlet",data,processResponse);
    xhr.fail(handleError);
}
function processResponse(responseText,textStatus,xhr){
        console.log("responseText is "+responseText);
        if(responseText.trim()=="uap"){
            swal("error","user already present ","error");
        }
        else if(responseText.trim()=="success"){
            swal("success","Registration done","success");
           y=window.setTimeout(()=>{
               window.location.href="Login.html"
               window.clearTimeout(y);
            },3000);
        }
        else{
            swal("error","some issue"+responseText,"error");
        }
}
function validate(){
    if(username==""||pwd==""||address==""||city==""||email==""||mobile==""||userid=="")
        return false;
    return true;
}
function checkNo(){
    if(mobile.length!=10){
        swal("error","length of mobile no should be 10","error");
        return false;
    }
    else{
        let result=true;
        for(i=0;i<mobile.length;i++){
            let n=mobile.charCodeAt(i);
           console.log("loop working time is "+(i+1))
            console.log("value of given char is "+n);
            if(n>=48&&n<=57){
                reseult=true
            }
            else{
                 swal("error","plzz enter digit only","error");
                 result=false;
                return false;
            }
        }
        return result;
    }
}

function checkMail(){
    let dot=email.indexOf(".");
    let at=email.indexOf("@");
    if(at<1||dot<(at+2)){
        return false;
    }
    return true;
}
function checkPwd(){
   if(pwd===cpwd)
     return true;
   else
       return false;
}
let x=document.getElementById("mobile");
console.log("value of x "+x)
x.onkeypress = (e) => {
    if (e.charCode >= 48 && e.charCode <= 57) {
       console.log("write")
    } else {
         swal("error","plzz enter digit only","error");
        console.log("wrong");
        
    }


}
function handleError(xhr){   
console.log('Some error occurred during your request: ' +  xhr.status + ' ' + xhr.statusText);
}