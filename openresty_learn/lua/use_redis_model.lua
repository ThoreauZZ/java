local new_redis = require "lua.new_redis"

local red = new_redis:new({ip="172.16.69.100", timeout = 3000, port = 6379})

red:cmd("set","cur_time", ngx.time())
local res = red:cmd("get", "cur_time")
ngx.say(res)