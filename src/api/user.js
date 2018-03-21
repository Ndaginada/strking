import myhttp from "./base/myhttp";

function userInfo(userid) {
    return myhttp({
        url: "microservice-provider-user/userInfo",
        method: "POST",
        data: {
            userid
        }
    })
}

function changeInfo(username,email,icon,introduce) {
    const sid = window.localStorage.getItem("strking_user_id");
    return myhttp({
        url:"microservice-provider-user/changeInfo",
        method:"POST",
        data:{
            sid,
            username,
            email,
            icon,
            introduce
        }
    })
}
export {userInfo,changeInfo}