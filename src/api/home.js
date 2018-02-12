import myhttp from "./base/myhttp"

function test_token() {
    return myhttp({
        url:"microservice-provider-user/hello",
        method:"GET"
    })
}
export {test_token}