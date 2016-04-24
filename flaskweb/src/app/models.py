'''
Created on 2016年3月20日

@author: henry.zhao
'''
from flask import Flask
from flask_mysqldb  import MySQL
app = Flask(__name__)
mysql = MySQL(app)
db = mysql.connect(host="localhost", user="root", passwd="123456",db="flask_web", charset="utf8")
db.autocommit(True)


def getUser():
    cur=mysql.connection.cursor()
    try:
        sql="INSERT INTO user (id, name,password) VALUES ('5', 'webmaster@python.org', 'very-secret')"
        ret = cur.execute(sql)
    except mysql.IntegrityError:
            pass
    return "OK"

if __name__ == '__main__':
    getUser()