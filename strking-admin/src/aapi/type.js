import myhttp from "./base/myhttp";


function typeList() {

    return myhttp({
        url:"strking-article/type/list",
        method:"POST",
    })
}

function countUnderArticle() {
    return myhttp({
        url:"strking-article/type/countUnderArticle",
        method:"POST",
    })
}

function delType(type_id) {
    return myhttp({
        url:"strking-article/type/delType/"+type_id,
        method:"POST",
    })
}

function addType(type_content) {
    return myhttp({
        url:"strking-article/type/addType",
        method:"POST",
        data:{
            type_content
        }
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
export {typeList,getTypeContent,countUnderArticle,delType,addType}