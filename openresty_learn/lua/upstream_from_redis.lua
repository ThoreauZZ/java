-- load global route cache into current request scope
-- by default vars are not shared between requests
local routes = _G.routes

-- setup routes cache if empty
if routes == nil then
    routes = {}
    ngx.log(ngx.ALERT, "Route cache is empty.")
end

-- try cached route first
local route = routes[ngx.var.http_host]
if route == nil then
    local redis = require "resty.redis"
    local red = redis:new()
    red:set_timeout(1000) -- 1 sec
    local ok, err = red:connect("172.16.69.100", 6379)
    if not ok then
        ngx.say("failed to connect: ", err)
        return
    end
    route = red:get("ngx.var.http_host")
end
-- fallback to redis for lookups
if route ~= nil then
    ngx.var.upstream = route
    routes[ngx.var.http_host] = route
    _G.routes = routes
else
    ngx.exit(ngx.HTTP_NOT_FOUND)
end