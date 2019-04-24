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

function articleList(article_title,type_id,limit,offset) {
    return myhttp({
        url:"strking-article/article/list",
        method:"POST",
        data: {
            article_title,
            type_id,
            limit,
            offset
        }
    })
}


function articleDetails(article_id) {
    return myhttp({
        url:"strking-article/article/details/"+article_id,
        method:"POST",
    })
}
function delArticle(article_id) {
    return myhttp({
        url:"strking-article/article/delArticle/"+article_id,
        method:"POST",
    })
}


function addRecommendVideo(title,video_url,from_author,add_admin_name) {
    return myhttp({
        url:"strking-article/recommendVideoController/addRecommendVideo",
        method:"POST",
        data:{
            title,
            video_url,
            from_author,
            add_admin_name
        }
    })
}

function recommendList(title,limit,offset) {
    return myhttp({
        url:"strking-article/recommendVideoController/recommendList",
        method:"POST",
        data:{
            title,
            limit,
            offset
        }
    })
}
function delRecommend(recommend_id) {
    return myhttp({
        url:"strking-article/recommendVideoController/delRecommend/"+recommend_id,
        method:"POST",
    })
}

function changeArticleStatus(article_id,status) {
    return myhttp({
        url:"strking-article/article/changeArticleStatus",
        method:"POST",
        data:{
            article_id,
            status
        }
    })
}
export {uplode_article_img,addArticle,articleList,articleDetails,changeArticleStatus,delArticle,addRecommendVideo,recommendList,delRecommend}