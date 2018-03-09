import myhttp from "./base/myhttp";

function register() {
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