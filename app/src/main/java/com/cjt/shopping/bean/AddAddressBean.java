package com.cjt.shopping.bean;

import java.util.List;

/**
 * 作者: 陈嘉桐 on 2016/7/6
 * 邮箱: 445263848@qq.com.
 */
public class AddAddressBean {

    /**
     * action : add
     * address : null
     * consignee : CJT
     * id : 0
     * items : null
     * path : CCJCJJCJCJJC
     * phone : 18814117978
     * postalcode : 0
     * result : success
     * totalPrice : 0
     * user : {"email":"345353@qq.com","id":1013,"myAddresses":[{"consignee":"é\u0099\u0088å\u0098\u0089æ¡\u0090","id":31,"path":"å¹¿å·\u009eå\u008d\u008eè½¯143","phone":"18814117978","postalcode":"0","state":0,"user":null},{"consignee":"CJT","id":34,"path":"CCJCJJCJCJJC","phone":"18814117978","postalcode":"0","state":0,"user":null},{"consignee":"CJT","id":33,"path":"å¹¿å·\u009eå\u008d\u008eè½¯143","phone":"18814117978","postalcode":"0","state":0,"user":null},{"consignee":"CJT","id":38,"path":"CCJCJJCJCJJC","phone":"18814117978","postalcode":"0","state":0,"user":null},{"consignee":"CJT","id":36,"path":"CCJCJJCJCJJC","phone":"18814117978","postalcode":"0","state":0,"user":null},{"consignee":"CJT","id":37,"path":"CCJCJJCJCJJC","phone":"18814117978","postalcode":"0","state":0,"user":null},{"consignee":"CJT","id":35,"path":"CCJCJJCJCJJC","phone":"18814117978","postalcode":"0","state":0,"user":null},{"consignee":"é\u0099\u0088å\u0098\u0089æ¡\u0090","id":32,"path":"å¹¿å·\u009eå\u008d\u008eè½¯143","phone":"18814117978","postalcode":"0","state":0,"user":null}],"name":"cjt","password":"123456","phone":"18814117978","power":1,"sex":"男","state":1,"totalOut":0,"vendor":null}
     * userId : 1013
     */

    private String action;
    private Object address;
    private String consignee;
    private int id;
    private Object items;
    private String path;
    private String phone;
    private String postalcode;
    private String result;
    private int totalPrice;
    /**
     * email : 345353@qq.com
     * id : 1013
     * myAddresses : [{"consignee":"é\u0099\u0088å\u0098\u0089æ¡\u0090","id":31,"path":"å¹¿å·\u009eå\u008d\u008eè½¯143","phone":"18814117978","postalcode":"0","state":0,"user":null},{"consignee":"CJT","id":34,"path":"CCJCJJCJCJJC","phone":"18814117978","postalcode":"0","state":0,"user":null},{"consignee":"CJT","id":33,"path":"å¹¿å·\u009eå\u008d\u008eè½¯143","phone":"18814117978","postalcode":"0","state":0,"user":null},{"consignee":"CJT","id":38,"path":"CCJCJJCJCJJC","phone":"18814117978","postalcode":"0","state":0,"user":null},{"consignee":"CJT","id":36,"path":"CCJCJJCJCJJC","phone":"18814117978","postalcode":"0","state":0,"user":null},{"consignee":"CJT","id":37,"path":"CCJCJJCJCJJC","phone":"18814117978","postalcode":"0","state":0,"user":null},{"consignee":"CJT","id":35,"path":"CCJCJJCJCJJC","phone":"18814117978","postalcode":"0","state":0,"user":null},{"consignee":"é\u0099\u0088å\u0098\u0089æ¡\u0090","id":32,"path":"å¹¿å·\u009eå\u008d\u008eè½¯143","phone":"18814117978","postalcode":"0","state":0,"user":null}]
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
    private String userId;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getItems() {
        return items;
    }

    public void setItems(Object items) {
        this.items = items;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
        /**
         * consignee : éåæ¡
         * id : 31
         * path : å¹¿å·åè½¯143
         * phone : 18814117978
         * postalcode : 0
         * state : 0
         * user : null
         */

        private List<MyAddressesBean> myAddresses;

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

        public List<MyAddressesBean> getMyAddresses() {
            return myAddresses;
        }

        public void setMyAddresses(List<MyAddressesBean> myAddresses) {
            this.myAddresses = myAddresses;
        }

        public static class MyAddressesBean {
            private String consignee;
            private int id;
            private String path;
            private String phone;
            private String postalcode;
            private int state;
            private Object user;

            public String getConsignee() {
                return consignee;
            }

            public void setConsignee(String consignee) {
                this.consignee = consignee;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getPostalcode() {
                return postalcode;
            }

            public void setPostalcode(String postalcode) {
                this.postalcode = postalcode;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public Object getUser() {
                return user;
            }

            public void setUser(Object user) {
                this.user = user;
            }
        }
    }
}
