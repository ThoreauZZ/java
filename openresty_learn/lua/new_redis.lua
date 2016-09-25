
-- redis访问封装
local redis = require "resty.redis"

local _M = {}
_M._VERSION = "1.10"

local mt = { __index = _M }

-- new 函数
function _M.new(self, opt)
	local ip 		= opt.ip 		or "127.0.0.1"
	local port 		= opt.port 		or 6379
	local timeout 	= opt.timeout 	or 1000
	local idletime 	= opt.idletime	or 10000
	local poolsize 	= opt.poolsize	or 100

	--setmetatable：为Lua 函数库提供支持
	return setmetatable({
		ip		= ip,
		port	= port,
		timeout	= timeout,
		idletime= idletime,
		poolsize= poolsize
		},mt)
end

-- 隐藏connect
local function _connect( self )
	local red = redis:new()

	red:set_timeout(self.timeout)
	local ok, err = red:connect(self.ip, self.port)
	if not ok then
		return nil, err
	end
	return red, nil
end

local function _set_keepalive(self, red )
	red:set_keepalive(self.idletime, self.poolsize)
end

-- 提供操作命令：这儿应该为command添加白名单；详细见openresty最佳实践
function _M.cmd(self, command, ... )
	local red, err = _connect(self)
	if err then
		return nil, err
	end
	local cmd_fun = red[command]
	local res, err = cmd_fun(red, ...)
	if nil == err then
		_set_keepalive(self, red)
	end
	return res, err
end

return _M


