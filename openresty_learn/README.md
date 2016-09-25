## 一、mac 安装
安装前准备
```
brew install pcre openssl #没有软连接的话，做好软连接
ln -s /usr/local/Cellar/pcre/8.38 /usr/local/opt/pcre
```
下载安装
```
wget https://openresty.org/download/openresty-1.11.2.1.tar.gz
tar zxvf openresty-1.11.2.1.tar.gz
cd openresty-1.11.2.1
./configure \
   --with-cc-opt="-I/usr/local/opt/openssl/include/ -I/usr/local/opt/pcre/include/" \
   --with-ld-opt="-L/usr/local/opt/openssl/lib/ -L/usr/local/opt/pcre/lib/" \
   -j4 \
   --prefix=/usr/local/opt/openresty

make -j4
make install -j4
```


```
PATH=/usr/local/openresty/nginx/sbin:$PATH
export PATH
nginx -p `pwd`/ -c conf/nginx.conf
```

nginx指定配置文件启动
```
sudo nginx -p `pwd` -c conf/nginx.conf -s reload
```
