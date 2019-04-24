import myhttp from "./base/myhttp"

/**
 * 登录
 * @returns {*}
 * @param userName
 * @param passWord
 */
function auth_login(userName, passWord) {
    const clientId = '098f6bcd4621d373cade4e832627b4f6';
    const loginRole = 1;
    return myhttp({

        url:"oauth/token",
        method:"POST",
        data: {
            clientId,
            userName,
            passWord,
            loginRole,
        }
    })
}


export {auth_login}