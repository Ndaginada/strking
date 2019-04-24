import myhttp from "./base/myhttp";

function uplode_article_img(file) {
    return myhttp({
        url:"strking-file-uplode/article_upload",
        method:"POST",
        headers:{'Content-Type':'multipart/form-data'},
        data: file
    })
}

function addArticle(content,title,titleImg,typeId ) {

    const authorId = window.localStorage.getItem("strking_user_id");
    return myhttp({
        url:"strking-article/article/edit",
        method:"POST",
        data: {
            content,
            title,
            titleImg,
            authorId,
            typeId
        }
    })
}

function articleList(type_id) {
    return myhttp({
        url:"strking-article/article/list",
        method:"POST",
        data: {
            type_id,
        }
    })
}

function articleDetails(article_id) {
    return myhttp({
        url:"strking-article/article/details/"+article_id,
        method:"POST",
    })
}
export {uplode_article_img,addArticle,articleList,articleDetails}