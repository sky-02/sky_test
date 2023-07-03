let userid,pwd;;
function connectUser(){
    userid=$("#username").val();
    pwd=$("#password").val();
    console.log("username is "+userid);
    console.log("pwd is "+pwd);
    if(!validateUser()){
        swal("error","plzz fill all field","error");
        return;
    }
    data={userid:userid,pwd:pwd};
     xhr=$.post("LoginServlet",data,processResponse);
    xhr.fail(handleError);
}
function handleError(xhr){   
console.log('Some error occurred during your request: ' +  xhr.status + ' ' + xhr.statusText);
}
function processResponse(responseText,textStatus,xhr){
    let url=responseText.trim();
    console.log("responseText is "+url);
    if(url!=""){
        swal("success","login acceptes","success")
       y=window.setTimeout(()=>{
               window.location.href=url;
               window.clearTimeout(y);
            },3000);
    }
    else{
        swal("error","username/password is invalid","error")
    }
}
function validateUser(){
    if(username==""||pwd=="")
        return false;
    return true;
}