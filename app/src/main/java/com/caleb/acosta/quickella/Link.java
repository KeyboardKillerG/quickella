package com.caleb.acosta.quickella;

public class Link {
    String link;

    Link(Contact contact){
        this.link = "https://wa.me/"+ contact.getAreaCode() + contact.getPhone();
    }

    Link(Contact contact, String message){
        this.link = "https://wa.me/"+ contact.getAreaCode() + contact.getPhone() +"?text=" + message;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
