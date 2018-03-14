# strking-ui
> 菜鸡初学Vue 试着做一个移动app的界面  名字叫做strking →_→

> 在devlop分支开发

> 后端使用微服务模式，后续也会放在github上

> ### 后端服务端口：

---

端口 | 服务名 | 补充说明
---|---|---
http://localhost:1111/ | 服务注册eureka|
http://localhost:3333/ | api网关|
http://localhost:2222/ | 用户User服务|
http://localhost:4444/ | 文件上传服务|


> ### 前端vue
---
{{host}} = http://localhost:8080/ 

地址 | 页面 | 补充说明
---|---|---
{{host}}/login | 登陆页面|
{{host}}/register | 注册页面|
{{host}}/home | 主页面|
ArticleIndex | 文章列表页面|未配置路由，将其作为模块放入home中|
{{host}}/articlDetail| 文章详情页面|
Me | 我的页面|未配置路由，将其作为模块放入home中|


## Development

```shell
npm i cooking-cli -g
npm run dev
```

## Production
```
npm run dist
```

