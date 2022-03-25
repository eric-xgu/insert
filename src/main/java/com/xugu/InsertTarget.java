package com.xugu;

import com.xugu.config.Target;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;


@Component
public class InsertTarget {
    @Autowired
    private Target target;
    private List<String> file_list=new ArrayList<String>();
    private int i=0;


    public  void init(){
        try {
            setFile_list();
        }catch (Exception e){
            System.out.println("初始化参数失败");
            e.printStackTrace();
        }
    }
    //检测参数是否为空
    public boolean checkObjFieldIsNull(Object obj) throws IllegalAccessException {

        boolean flag = false;
        for(Field f : obj.getClass().getDeclaredFields()){
            f.setAccessible(true);
            if(f.get(obj) == null){
                flag = true;
                System.out.println("参数不能为空:"+f.get(obj));
                return flag;
            }
        }
        return flag;
    }
    //获取子目录
    public  void setFile_list() throws Exception{
            checkObjFieldIsNull(target);
            File baseFile = new File(target.getLocal_path());
            if (!baseFile.exists()) {
                System.out.println( target.getLocal_path()+ " not exists");//不存在就输出
                Thread.interrupted();
            }

            File[] files = baseFile.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.println("存在子目录"+file.getAbsolutePath());
                } else {
                    file_list.add(file.getAbsolutePath());
                }
            }
            for (String str:file_list){
                System.out.println(str);
            }

    }
    public Connection getConn() throws  Exception{
        Class.forName("com.xugu.cloudjdbc.Driver");
        Properties info = new Properties();
        info.put("user", target.getUser());
        info.put("password", target.getPassword());
        String url="jdbc:xugu://"+target.getIp()+":"+target.getPort()+"/"+target.getDb();
        Connection conn = DriverManager.getConnection(url,info);
        return conn;
    }

    public synchronized String getFile(){
        if(i==file_list.size()){
            return null;
        }
        String file=file_list.get(i);
        i++;
        return file;
    }

    public void run(){


            Runnable runnable=()->{

                try {
                    Connection conn = getConn();
                    Statement st =conn.createStatement();
                    String str=null;
                    while ((str=getFile())!=null){
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                        System.out.println(df.format(new Date())+Thread.currentThread());// new Date()为获取当前系统时间
                        File file = new File(str);
                        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), target.getChar_set()));//构造一个BufferedReader类来读取文件
                        String s = null;
                        String s1=null;
                        s = br.readLine();
                        s="insert into "+target.getTarget_table()+"("+s.replace(target.getSplit(), ",")+") values('";
                        int count=0;
                        int count1=0;
                        while((s1 = br.readLine())!=null){//使用readLine方法，一次读一行
                            String sql=s+s1.replace(target.getSplit(), "','")+"');";
                            //System.out.println(sql);
                            try {
                                st.execute(sql);
                            }catch (SQLException e){

                                //[E5021] 表或视图USR_SOD.SURF_WEA_CHN_MUL_HOR_TABQ不存在
                                if(e.getErrorCode()==5021){
                                     e.printStackTrace();
                                break;
                                }else if(e.getErrorCode()==13001){
                                    count1++;
                                    continue;
                                }else{
                                    e.printStackTrace();
                                    break;
                                }
                            }
                            count++;
                        }
                        br.close();
                        System.out.println(df.format(new Date())+Thread.currentThread()+":"+str+"入库条数："+count+" 主键或唯一值约束冲突条数："+count1);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            };
            for(int j=0;j<target.getThread_num();j++){
             new Thread(runnable).start();
            }

//        Statement st =conn.createStatement();
//        for (String str:file_list) {
//            File file = new File(str);
//            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), target.getChar_set()));//构造一个BufferedReader类来读取文件
//
//            String s = null;
//            String s1=null;
//            s = br.readLine();
//            s="insert into "+target.getTarget_table()+"("+s.replace(target.getSplit(), ",")+") values('";
//            System.out.println(s);
//            int count=0;
//            while((s1 = br.readLine())!=null){//使用readLine方法，一次读一行
//                    String sql=s+s1.replace(target.getSplit(), "','")+"');";
//                    //System.out.println(sql);
//                    st.execute(sql);
//                    count++;
//                }
//            br.close();
//            System.out.println("入库条数："+count);
//        }
//            }catch(Exception e){
//                e.printStackTrace();
//            }

    }

}
