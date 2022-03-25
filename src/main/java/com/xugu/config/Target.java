package com.xugu.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

//lombok注解
//@Data
//@NoArgsConstructor
//@AllArgsConstructor

//将当前类对象放在IOC容器中
@Component
//表示与配置文件中前缀为student的数据相对应
@ConfigurationProperties(prefix = "target")
public class Target {
   private String ip;
   private String port;
   private String db;
   private String user;
   private String password;
   private String target_table;
   private String local_path;
   private String split;
   private String char_set;
   private short thread_num;

   public short getThread_num() {
      return thread_num;
   }

   public void setThread_num(short thread_num) {
      this.thread_num = thread_num;
   }

   public String getSplit() {
      return split;
   }

   public void setSplit(String split) {
      this.split = split;
   }

   public String getIp() {
      return ip;
   }

   public String getChar_set() {
      return char_set;
   }

   public void setChar_set(String char_set) {
      this.char_set = char_set;
   }

   public void setIp(String ip) {
      this.ip = ip;
   }

   public String getPort() {
      return port;
   }

   public void setPort(String port) {
      this.port = port;
   }

   public String getDb() {
      return db;
   }

   public void setDb(String db) {
      this.db = db;
   }

   public String getUser() {
      return user;
   }

   public void setUser(String user) {
      this.user = user;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getTarget_table() {
      return target_table;
   }

   public void setTarget_table(String target_table) {
      this.target_table = target_table;
   }

   public String getLocal_path() {
      return local_path;
   }

   public void setLocal_path(String local_path) {
      this.local_path = local_path;
   }
}
