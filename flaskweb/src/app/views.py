#views.py用于便携Blog的主逻辑

# -*- coding: utf-8 -*-
#!/usr/bin/env python

from app import app
from flask import render_template,url_for,request

@app.route('/')
def index():
    user_agent = request.headers.get('User-Agent')
    return "<h2>hello world!</h2>"+'<p>Your browser is %s</p>' % user_agent

@app.route('/welcome')
def welcome():
    return render_template("welcome.html")

@app.route('/index')
def mainIndex():
    return render_template("index.html")
@app.route('/login')
def login():
    return render_template("index.html")
