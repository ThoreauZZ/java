'''
Created on 2016年3月20日

@author: henry.zhao
'''
import pymysql
from flask import Flask
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] ="mysql://root:123456@hostname/database"
app.config['SQLALCHEMY_COMMIT_ON_TEARDOWN'] = True
db = SQLAlchemy(app)

# Connect to the database
def do2db():
    conn = pymysql.connect(host='localhost',
                                 user='root',
                                 password='123456',
                                 db='flask_web',
                                 charset='utf8')
    cur=conn.cursor();
    sql = "INSERT INTO `user` (`name`, `password`) VALUES (%s, %s)"
    cur.execute(sql, ('webmaster@python.org', 'very-secret'))
    conn.commit()
    conn.close()

if __name__=="__main__":
    do2db()
    
