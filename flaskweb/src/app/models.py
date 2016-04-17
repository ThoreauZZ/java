'''
Created on 2016年3月20日

@author: henry.zhao
'''
from flaskext.mysql import MySQL
mysql = MySQL()
mysql.init_app(app)

db=mysql.connect(user="root", passwd="123456",db="flask_web", charset="utf8")
db.autocommit(True)
c = db.cursor()

def getUser():
    try:
        sql="INSERT INTO `user` (`id`, `name`, `password`) \
        VALUES ('5', 'webmaster@python.org', 'very-secret');"
        ret = c.execute(sql)
    except mysql.IntegrityError:
            pass
    return "OK"

if __name__ == '__main__':
    getUser()