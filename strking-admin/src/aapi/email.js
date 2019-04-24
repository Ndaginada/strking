import myhttp from "./base/myhttp";

function sendCode(userid) {
    return myhttp({
        url: "microservice-provider-user/mail/sendCode",
        method: "POST",
        data: {
            userid
        }
    })
}


export {sendCode}