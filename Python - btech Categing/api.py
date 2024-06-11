from flask import *
from database import *
import uuid 

api=Blueprint('api',__name__)


@api.route("/login")
def login():
    data={}
    uname=request.args['username']
    password=request.args['password']
    q="select * from login where username='%s' and password='%s'"%(uname,password)
    res=select(q)
    if res:
        data['status']="success"
        data['data']=res
    else:
        data['status']="failed"
    return str(data)


@api.route("/reg",methods=['get','post'])
def reg():
    data={}

    fname=request.form['fname']
    lname=request.form['lname']
    place=request.form['place']
    phone=request.form['phone']
    email=request.form['email']
    username=request.form['uname']
    password=request.form['pass']
    image=request.files['image']
    path="static/profilepic/"+str(uuid.uuid4())+image.filename
    image.save(path)
   

    q="select * from login where username='%s'"%(username)
    rep=select(q)

    if rep:
        data['status']='already'
    else:
        q="insert into `login` values(NULL,'%s','%s','user') "%(username,password)
        ref=insert(q)
        v="insert into `user` values(NULL,'%s','%s','%s','%s','%s','%s','%s') "%(ref,fname,lname,place,phone,email,path)
        insert(v)
        data['status']='success'
    data['method']="reg"
    return str(data)



@api.route("/view_recipy")
def view_recipy():
    data={}
    q="SELECT *,concat(fname,'',lname) as username FROM recipy inner join user using (user_id)"
    res=select(q)
    if res:
        data['status']="success"
        data['data']=res
    else:
        data['status']="failed"
    data['method']="view_recipy"
    return str(data)

@api.route("/view_recipy_search")
def view_recipy_search():
    data={}
    search='%'+request.args['search']+'%'
    q="SELECT *,concat(fname,'',lname) as username FROM recipy inner join user using (user_id) where name like '%s'"%(search)
    res=select(q)
    if res:
        data['status']="success"
        data['data']=res
    else:
        data['status']="failed"
    data['method']="view_recipy"
    return str(data)



@api.route("/chatdetail")
def chatdetail():
	sid=request.args['sender_id']
	rid=request.args['receiver_id']
	
	data={}
	q="select * from chat where (sender_id='%s' and receiver_id='%s') or (sender_id='%s' and receiver_id='%s') group by chat_id "%(sid,rid,rid,sid)
	
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		
		data['status']="failed"
	data['method']='chatdetail'
	
	return str(data)

@api.route("/chat")
def chat():
	data={}
	sid=request.args['sender_id']
	rid=request.args['receiver_id']
	det=request.args['details']
	
	
	q="insert into chat values(null,'%s','%s','%s',curdate())"%(sid,rid,det)
	insert(q)
	data['status']='success'
	data['method']='chat'
	return str(data)


@api.route("/viewusers")
def viewusers():
	lid=request.args['log_id']
	
	data={}
	q="select *,concat(fname,'',lname) as name from user where login_id <> '%s'"%(lid)
	
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
		data['method']='viewusers'
	else:
		data['method']='viewusers'
		data['status']="failed"
	
	return str(data)


@api.route("/my_profile")
def my_profile():
    lid=request.args['lid']

    data={}
    q="select * from user where login_id='%s'"%(lid)
    print(q)
    res=select(q)
    if res:
        data['status']="success"
        data['data2']=res
        q="SELECT *,concat(fname,'',lname) as username FROM recipy inner join user using (user_id) where user_id=(select user_id from user where login_id='%s')"%(lid)
        data['data']=select(q)
        data['method']='my_profile'
    else:
        data['method']='my_profile'
        data['status']="failed"
    return str(data)


@api.route("/diet_mode")
def diet_mode():
    data={}
    q="SELECT *,concat(fname,'',lname) as username FROM recipy inner join user using (user_id) where diet_mode='1'"
    res=select(q)
    if res:
        data['status']="success"
        data['data']=res
    else:
        data['status']="failed"
    data['method']="diet_mode"
    return str(data)

@api.route("/diet_mode_search")
def diet_mode_search():
    data={}
    froms=request.args['from']
    to=request.args['to']
    q="SELECT *,concat(fname,'',lname) as name FROM recipy inner join user using (user_id)  WHERE diet_mode = '1' AND calorie  BETWEEN '%s' AND '%s'"%(froms,to)
    print(q)
    res=select(q)
    if res:
        data['status']="success"
        data['data']=res
    else:
        data['status']="failed"
    data['method']="diet_mode"
    return str(data)


@api.route("/user_upload_recipy",methods=['post'])
def user_upload_recipy():
    data={}
    name=request.form['name']
    dtls=request.form['dtls']
    inc=request.form['inc']
    dietmode=request.form['dietmode']
    lid=request.form['lid']
    Calorie=request.form['Calorie']
    
    image=request.files['image']
    imgpath="static/uploads/"+str(uuid.uuid4())+image.filename
    image.save(imgpath)
    
    video=request.files['video'] 
    videopath="static/uploads/"+str(uuid.uuid4())+video.filename
    video.save(videopath)


    q="insert into recipy values(null,(select user_id from user where login_id='%s'),'%s','%s','%s','%s','%s','%s','%s')"%(lid,name,inc,imgpath,videopath,dtls,dietmode,Calorie)
    insert(q)
    data['status']='success'
    data['method']='user_upload_recipy'
    return str(data)




@api.route("/watch_video_withcount")
def watch_video_withcount():
    lid=request.args['lid']
    vid=request.args['vid']

    data={}
    q="select * from view_count where recipy_id='%s' and user_id=(select user_id from user where login_id='%s')"%(vid,lid)
    print(q)
    res=select(q)
    if res:
        data['status']="no count"
    else:
        q="insert into view_count values (null,(select user_id from user where login_id='%s'),'%s',1)"%(lid,vid)
        insert(q)
        data['status']="count added"
    data['method']="test_count"
    return str(data)



@api.route("/view_ingredients")
def view_ingredients():
    data={}
    rid=request.args['rid']
    
    q="SELECT * FROM recipy where recipy_id='%s'"%(rid)
    print(q)
    res=select(q)
    if res:
        data['status']="success"
        data['ing']=res[0]['incredints']
        data['calorie']=res[0]['calorie']
    else:
        data['status']="failed"
    data['method']="view_ingredients"
    return str(data)

@api.route("/view_my_stat")
def view_my_stat():
    data={}
    count_limit=1
    count_payment=500
    rid=request.args['rid']
    
    q="SELECT count(count) as count FROM recipy inner join view_count using (recipy_id) where recipy_id='%s'"%(rid)
    print(q)
    res=select(q)
    if res:
        data['status']="success"
        data['count']=res[0]['count']
        if data['count'] > count_limit:
            data['Monetization'] = "Enabled"
            amount = int(data['count']) * count_payment
            data['amount'] = str(amount)+"Rs"
        else:
            data['Monetization'] = "Disabled"
            data['amount'] = "NA"
       
    else:
        data['status']="failed"
        data['Monetization'] = "Disabled"
        data['amount'] = "NA"
        data['count'] = "0"
    data['method']="view_my_stat"
    return str(data)


@api.route("/delete_receipy")
def delete_receipy():
    data={}
    rid=request.args['rid']
    q="delete from recipy where recipy_id='%s'"%(rid)
    delete(q)

    data['status']="success"
    data['method']="delete_receipy"
    return str(data)

@api.route("/view_videoplay")
def view_videoplay():
    data={}
    q="SELECT * FROM adds  ORDER BY RAND ( )  LIMIT 1 "
    res=select(q)
    if res:
        data['status']="success"
        data['data']=res
    else:
        data["status"]='failed'
    data['method']="view_videoplay"
    return str(data)    