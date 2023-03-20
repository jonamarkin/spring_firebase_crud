package com.atomarkin.firebasecrud.service;

import com.atomarkin.firebasecrud.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FirebaseCrudService {
  // Create fire base crud services to create, read, update and delete data from firebase

  public String create(User user) throws ExecutionException, InterruptedException {
    // Create a new user in firebase
    Firestore firestore = FirestoreClient.getFirestore();
    // Generate a random document id
    String documentId = firestore.collection("users").document().getId();

    ApiFuture<WriteResult> collectionApiFuture =
        firestore.collection("users").document(documentId).set(user);
    // Return the created user
    return collectionApiFuture.get().getUpdateTime().toString();
  }

  public String read(String documentId) throws ExecutionException, InterruptedException {
    // Read a user from firebase
    Firestore firestore = FirestoreClient.getFirestore();
    DocumentReference documentReference = firestore.collection("users").document(documentId);
    ApiFuture<DocumentSnapshot> userApiFuture = documentReference.get();
    // Return the read user
    DocumentSnapshot documentSnapshot = userApiFuture.get();
    if (documentSnapshot.exists()) {
      return documentSnapshot.getData().toString();
    }

    return null;
  }

  // Read all documents
  public List<User> readAll() throws ExecutionException, InterruptedException {
    // Read a user from firebase
    Firestore firestore = FirestoreClient.getFirestore();
    ApiFuture<QuerySnapshot> querySnapshotApiFuture = firestore.collection("users").get();
    // Return the read user
    QuerySnapshot querySnapshot = querySnapshotApiFuture.get();

    List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
    List<User> users = querySnapshot.toObjects(User.class);
    System.out.println(documents.size());
    System.out.println(documents);
    for (QueryDocumentSnapshot document : documents) {
      System.out.println(document.getId());
    }

    return users;
  }

  public String update(User user) {
    return "Update";
  }

  public String delete(String documentId) {
    // Delete a user from firebase
    Firestore firestore = FirestoreClient.getFirestore();
    ApiFuture<WriteResult> writeResultApiFuture =
        firestore.collection("users").document(documentId).delete();
    // Return response
    return "Document with ID %s has been deleted".formatted(documentId);
  }
}
