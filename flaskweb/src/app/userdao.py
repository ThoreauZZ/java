'''
Created on 2016年3月20日

@author: henry.zhao
'''
import pymysql
from flask import Flask
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] ="mysql://root:123456@localhost:3306/flask_web"
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
    
class Role(db.Model):
    __tablename__ = 'roles'
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(64), unique=True)
    def __repr__(self):
        return '<Role %r>' % self.name
    users = db.relationship('User', backref='role')
    
class User(db.Model):
    __tablename__ = 'users'
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(64), unique=True, index=True)
    def __repr__(self):
        return '<User %r>' % self.username
    role_id = db.Column(db.Integer, db.ForeignKey('roles.id'))
    
if __name__=="__main__":
    db.create_all()
    
