package com.atomarkin.firebasecrud.service;

import com.atomarkin.firebasecrud.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import java.util.concurrent.ExecutionException;

public class FirebaseCrudService {
  // Create fire base crud services to create, read, update and delete data from firebase

  public String create(User user) throws ExecutionException, InterruptedException {
    // Create a new user in firebase
    Firestore firestore = FirestoreClient.getFirestore();
    ApiFuture<WriteResult> collectionApiFuture =
        firestore.collection("users").document(user.getDocumentId()).set(user);
    // Return the created user
    return collectionApiFuture.get().getUpdateTime().toString();
  }

  public String read(String documentId)throws ExecutionException, InterruptedException {
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

  public String update(User user) {
    return "Update";
  }

  public String delete(String documentId) {
    return "Delete";
  }
}
