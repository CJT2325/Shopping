package com.cjt.shopping.bean;

import java.util.List;

/**
 * 作者: 陈嘉桐 on 2016/7/4
 * 邮箱: 445263848@qq.com.
 */
public class UserInfo {

    /**
     * action : login
     * email : null
     * name : cjt
     * password : 123456
     * phone : null
     * result : 103
     * sex : null
     * user : {"email":"345353@qq.com","id":1013,"myAddresses":[],"name":"cjt","password":"123456","phone":"18814117978","power":1,"sex":"男","state":1,"totalOut":0,"vendor":null}
     */

    private String action;
    private Object email;
    private String name;
    private String password;
    private Object phone;
    private String result;
    private Object sex;
    /**
     * email : 345353@qq.com
     * id : 1013
     * myAddresses : []
     * name : cjt
     * password : 123456
     * phone : 18814117978
     * power : 1
     * sex : 男
     * state : 1
     * totalOut : 0
     * vendor : null
     */

    private UserBean user;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Object getSex() {
        return sex;
    }

    public void setSex(Object sex) {
        this.sex = sex;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        private String email;
        private int id;
        private String name;
        private String password;
        private String phone;
        private int power;
        private String sex;
        private int state;
        private int totalOut;
        private Object vendor;
        private List<?> myAddresses;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getPower() {
            return power;
        }

        public void setPower(int power) {
            this.power = power;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getTotalOut() {
            return totalOut;
        }

        public void setTotalOut(int totalOut) {
            this.totalOut = totalOut;
        }

        public Object getVendor() {
            return vendor;
        }

        public void setVendor(Object vendor) {
            this.vendor = vendor;
        }

        public List<?> getMyAddresses() {
            return myAddresses;
        }

        public void setMyAddresses(List<?> myAddresses) {
            this.myAddresses = myAddresses;
        }
    }
}
