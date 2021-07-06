package com.caleb.acosta.quickella;

public class Contact {
        int id;
        String name, areaCode, phone;

        //Empty
        Contact(){}

        Contact(int id, String name, String areaCode, String phone){
                this.id = id;
                this.name = name;
                this.areaCode = areaCode;
                this.phone = phone;
        }
        Contact(String areaCode, String phone){
                this.areaCode = areaCode;
                this.phone = phone;
        }


        public int getId() { return id; }

        public void setId(int id) { this.id = id; }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getAreaCode() {
                return areaCode;
        }

        public void setAreaCode(String areaCode) {
                this.areaCode = areaCode;
        }

        public String getPhone() {
                return phone;
        }

        public void setPhone(String phone) {
                this.phone = phone;
        }
}
