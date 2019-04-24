import myhttp from "./base/myhttp"

function test_token() {
    return myhttp({
        url:"microservice-provider-user/hello",
        method:"GET"
    })
}

function uplode_img(file) {
    return myhttp({
        url:"strking-file-uplode/upload",
        method:"POST",
        headers:{'Content-Type':'multipart/form-data'},
        data: file
    })
}
export {test_token,uplode_img}