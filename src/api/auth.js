import myhttp from "./base/myhttp"

/**
 * 登录
 * @returns {*}
 * @param userName
 * @param passWord
 */
function auth_login(userName, passWord) {
    var clientId = '098f6bcd4621d373cade4e832627b4f6';
    return myhttp({

        url:"oauth/token",
        method:"POST",
        data: {
            clientId,
            userName,
            passWord
        }
    })
}


export {auth_login}