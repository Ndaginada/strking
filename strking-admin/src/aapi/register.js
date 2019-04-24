import myhttp from "./base/myhttp";

function register(username,password,email,introduce) {
    return myhttp({
        url: "oauth/register_normal",
        method: "POST",
        data: {
            username,
            password,
            email,
            introduce
        }
    })
}

export {register}