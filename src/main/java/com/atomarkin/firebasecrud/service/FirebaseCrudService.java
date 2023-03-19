package com.atomarkin.firebasecrud.service;

import com.atomarkin.firebasecrud.model.User;public class FirebaseCrudService {
    //Create fire base crud services to create, read, update and delete data from firebase

    public String create(User user) {
        return "Create";
    }

    public String read(String documentId) {
        return "Read";
    }

    public String update(User user) {
        return "Update";
    }

    public String delete(String documentId) {
        return "Delete";
    }

}
