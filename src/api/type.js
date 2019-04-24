import myhttp from "./base/myhttp";


function typeList() {

    return myhttp({
        url:"strking-article/type/list",
        method:"POST",
    })
}

function getTypeContent(type_id) {
    return myhttp({
        url:"strking-article/type/getContent",
        method:"POST",
        data:{
            type_id
        }
    })
}
export {typeList,getTypeContent}