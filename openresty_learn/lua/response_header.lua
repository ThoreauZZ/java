-- curl -i  localhost:8080/response_header 返回的server会被改变
-- ngx.header.Server = 'hh'
ngx.header['server'] = 'hh'