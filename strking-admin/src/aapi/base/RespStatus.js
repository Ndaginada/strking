/**
 * Created with assistant-vue.
 * Author: kkai.
 * Date: 2017/12/17.
 * Time: 下午5:08.
 */

const respStatus = {
    OK: {
        code: 1,
        message: '成功'
    },
    BAD_REQUEST: {
        code: 400,
        message: '参数错误'
    },
    UNAUTHORIZED: {
        code: 401,
        message: '用户未授权'
    },
    FORBIDDEN: {
        code: 403,
        message: '无权访问'
    },
    NOT_FOUND: {
        code: 404,
        message: '不存在'
    },
    NOT_ACCEPTABLE: {
        code: 406,
        message: '无法接受此请求'
    },
    DUPLICATION: {
        code: 408,
        message: '重复'
    },
    INTERNAL_SERVER_ERROR: {
        code: 500,
        message: '服务器异常'
    },
    OUT_OF_RANGE: {
        code: 509,
        message: '访问频率被限制'
    },
    COMMON_ERROR: {
        code: 600,
        message: '业务逻辑错误'
    },
    BAD_USER: {
        code: 601,
        message: '用户名密码错误'
    }
};
export default respStatus;
