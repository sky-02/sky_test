
function addVote(){
    let cid=$('input[name="vote"]:checked').val();
    console.log(" selected candidate id is",cid);
    data={id:cid};    
$.post("AddVoteServlet",data,function(responseText){
    console.log("response text is "+responseText) 
    if(responseText.trim()=="success"){
        swal("sucsess","Vote Done","success").then(
                (value)=>{
                     window.location.href="CandidateInformation.jsp"
                });
      
    }
    else{
        console.log("fail")
    }
});
}

