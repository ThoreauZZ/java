local function print_table(t)
	-- body
	local function parse_array(key, tab)
		local str = ''

		for _, v in pairs(tab) do
			str = str .. key .. ' ' .. v .. '\r\n'
		end

		return str
	end

	local str = ''
	for k, v in pairs(t)do
		if type(v) == 'table' then
			ngx.log(ngx.ALERT, "is table.")
			str = str .. parse_array(k, v)
		else
			str = str .. k .. ' ' .. v .. '\r\n'
			ngx.log(ngx.ALERT, "is not table.")
		end
	end
	return str
end
--headers = ngx.req.get_headers(max_headers?, raw?)
-- 获取请求头，参数1：头个数，最大100，raw：是否区分大小写，默认false
local headers = ngx.req.get_headers(10, true)
local tp = type(headers)
ngx.log(ngx.ALERT,tp)

ngx.say(print_table(headers))