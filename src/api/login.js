import myhttp from "./base/myhttp";

/**
 * 登录
 * @param username
 * @param password
 * @returns {*}
 */
function login(username, password) {
    return myhttp({
        url:"microservice-provider-user/checkUser",
        method:"POST",
        data: {
            username,
            password
        }
    })
}

export {login}