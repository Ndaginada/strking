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

function userList(username, limit, offset) {
    return myhttp({
        url: "microservice-provider-user/userList",
        method: "POST",
        data: {
            username,
            limit,
            offset
        }
    })
}
function adminList(username, limit, offset) {
    return myhttp({
        url: "microservice-provider-user/adminList",
        method: "POST",
        data: {
            username,
            limit,
            offset
        }
    })
}

function delUser(userId) {
    return myhttp({
        url: "microservice-provider-user/delUser",
        method: "POST",
        data: {
            userId,
        }
    })
}

function addUser(username, password, email, introduce, userRole) {
    return myhttp({
        url: "microservice-provider-user/addUser",
        method: "POST",
        data: {
            username,
            password,
            email,
            introduce,
            userRole,
        }
    })
}

function changeInfo(sid, username, email, icon, introduce) {

    return myhttp({
        url: "microservice-provider-user/changeInfo",
        method: "POST",
        data: {
            sid,
            username,
            email,
            icon,
            introduce
        }
    })
}

function changePass(oldPassword, newPassword) {
    const sid = window.localStorage.getItem("strking_user_id");
    return myhttp({
        url: "microservice-provider-user/changePass",
        method: "POST",
        data: {
            sid,
            oldPassword,
            newPassword
        }
    })
}


export {userInfo, changeInfo, changePass, userList, delUser, addUser, adminList}