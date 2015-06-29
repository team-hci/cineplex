<%@page import="org.jboss.resteasy.spi.HttpRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- SmartUpload组件用到的文件上传类 -->

<%@ page import="com.jspsmart.upload.*,java.sql.*,java.util.Date;"%>

<%
String className="com.mysql.jdbc.Driver";
String url="jdbc:mysql://localhost:3306/cineplex";
String user="root";
String password="";
Connection conn; 
Statement st;

//设置request范围字符集

request.setCharacterEncoding("GBK");
//response.setContentType("text/html; charset=utf-8");

//取得整个web应用的物理根路径（注意不是jsp项目根路径）

String root = request.getSession().getServletContext().getRealPath("/");

//设置上传文件的保存路径（绝对路径/物理路径www.mwcly.cn）

String savePath = "/Users/huangjiawei/Projects/Cineplex/CineplexWeb/WebContent/posters/";
String showPath = "/CineplexWeb/posters/";

//声明SmartUpload类对象

SmartUpload mySmartUpload = new SmartUpload();

try { 

//初始化的方法必须先执行

//参数：config,request,response都是jsp内置对象

mySmartUpload.initialize(config,request,response);

//上传文件数据

mySmartUpload.upload();

//将全部上传文件保存到指定目录下

//mySmartUpload.save(savePath);

//取得文件名(因为只上传一个文件，所以用getFile(0))

//String fileName = mySmartUpload.getFiles().getFile(0).getFileName();

File file = mySmartUpload.getFiles().getFile(0);
String extname = file.getFileExt();//获取文件扩展名
String fileName = new Date().getTime() + "." + extname;// 产生一个唯一的文件名
file.saveAs(savePath + fileName);//更换路径重命名,File.SAVEAS_VIRTUAL
file.saveAs("/posters/"+fileName,File.SAVEAS_VIRTUAL);

//取得表单中普通控件的值(text,password……)

Request req = mySmartUpload.getRequest();

String movieName = req.getParameter("movie_name");
String description = req.getParameter("movie_description");
int region = Integer.parseInt(req.getParameter("region_radio"));
String[] types = req.getParameterValues("type_checkbox");
int[] types2 = new int[types.length];
for(int i=0;i<types.length;i++){
	types2[i] = Integer.parseInt(types[i]);
}
String[] directors = req.getParameter("director").split(" ");
String[] actors = req.getParameter("actor").split(" ");
String movieBegin = req.getParameter("movie_begin");
String movieOver = req.getParameter("movie_over");
int movieLength = Integer.parseInt(req.getParameter("movie_length"));

String movieHtml = showPath+fileName;


/* System.out.println(movieName);
System.out.println(description);
System.out.println(region);
for(int i=0;i<types2.length;i++){
	System.out.println(types2[i]);
}
for(int i=0;i<directors.length;i++){
	System.out.println(directors[i]);
}
for(int i=0;i<actors.length;i++){
	System.out.println(actors[i]);
} */

Class.forName(className);

  conn=DriverManager.getConnection(url, user, password);

   String sql="INSERT INTO movie(moviename,movieposter,moviedescription,regionid,moviebegin,movieover,movielength) VALUES('"+movieName+"','"+movieHtml+"','"+description+"',"+region+",'"+movieBegin+"','"+movieOver+"',"+movieLength+")";

   st = (Statement) conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY,  
           java.sql.ResultSet.CONCUR_UPDATABLE);    // 创建用于执行静态sql语句的Statement对象   

int count = st.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);  // 执行插入操作的sql语句，并返回插入数据的个数   
ResultSet rs = st.getGeneratedKeys(); 
int mid = -1;
if(rs.next()){
	mid = rs.getInt(1);
	//类型
	for(int i=0;i<types2.length;i++){
		st.executeUpdate("INSERT INTO mtype(movieid,typeid) VALUES("+mid+","+types2[i]+")");
	}
	for(int i=0;i<directors.length;i++){
		st.executeUpdate("INSERT INTO mdirector(movieid,directorname) VALUES("+mid+",'"+directors[i]+"')");
	}
	for(int i=0;i<actors.length;i++){
		st.executeUpdate("INSERT INTO mactor(movieid,actorname) VALUES("+mid+",'"+actors[i]+"')");
	}
}
    conn.close();   //关闭数据库连接   
	//System.out.println(mid);
    response.sendRedirect(request.getContextPath()+"/AddMovie.waiter");
    
} catch (Exception e){

    System.out.println("Error : " + e.toString()); 

}           

%>