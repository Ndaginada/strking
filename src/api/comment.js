import myhttp from "./base/myhttp";


function addComment(article_id,content) {
    let user_id = localStorage.getItem("strking_user_id");
    return myhttp({
        url:"strking-article/comment/addComment",
        method:"POST",
        data:{
            article_id,
            content,
            user_id,
        }
    })
}

function articleCommentList(article_id) {
    return myhttp({
        url:"strking-article/comment/articleCommentList",
        method:"POST",
        data:{
            article_id,
        }
    })
}
export {addComment,articleCommentList}