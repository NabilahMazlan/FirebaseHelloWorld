package com.example.firebasehelloworld;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Collection;

import javax.annotation.Nullable;


public class MainActivity extends AppCompatActivity {

    private TextView tvMessage;
    private EditText etMessage;
    private TextView tvPriority;
    private EditText etPriority;
    private Button btnUpdate;

    private FirebaseFirestore firestore;
    private CollectionReference collection;
    private DocumentReference document;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMessage = findViewById(R.id.textViewMessage);
        etMessage = findViewById(R.id.editTextMessage);
        tvPriority = findViewById(R.id.textViewPriority);
        etPriority = findViewById(R.id.editTextPriority);

        btnUpdate = findViewById(R.id.buttonUpdate);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnUpdateOnClick(v);
            }
        });

        firestore = FirebaseFirestore.getInstance();

        collection = firestore.collection("messages");
        document = collection.document("message_id");
        document.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(e != null){
                    return;
                }
                if(documentSnapshot != null && documentSnapshot.exists()){
                    Message message = documentSnapshot.toObject(Message.class);
                    tvMessage.setText(message.getText());
                    tvPriority.setText("Priority is: " + message.getPriority());
                }
            }
        });


    }

    private void btnUpdateOnClick(View v) {
        String text = etMessage.getText().toString();
        String priority = etPriority.getText().toString();
        Message message = new Message(text, priority);
        document.set(message);

    }
}
