-- 获取http请求参数
local args = ngx.req.get_uri_args()
--参数salt赋值给salt
local salt = args.salt
-- 如果salt不存在，返回400
if not salt then
        ngx.exit(ngx.HTTP_BAD_REQUEST)
end
-- 当前时间+salt 作md5；nginx里面 .. 是字符串拼接
local string = ngx.md5(ngx.time() .. salt)
ngx.say(string)