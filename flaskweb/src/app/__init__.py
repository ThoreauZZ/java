# -*- coding: utf-8 -*-
#!/usr/bin/env python
from flask import Flask

app=Flask(__name__) #创建flask实例
app.config.from_object('config')#从config.py读取配置文件


#这个import语句放在这里, 防止views, models import发生循环import

from app import views,models