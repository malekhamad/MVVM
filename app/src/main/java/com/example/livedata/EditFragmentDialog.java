package com.example.livedata;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class EditFragmentDialog extends DialogFragment {
    Button UpdateBtn,CancelBtn ;
    EditText editTitle , editDescription ;
    int id ;

  private EditViewModel editViewModel ;
  private LiveData<Subject>subject ;

    public static EditFragmentDialog getInstance(int id){
        EditFragmentDialog dialog = new EditFragmentDialog();
        Bundle bundle = new Bundle();
        bundle.putInt("id",id);
        dialog.setArguments(bundle);
        return dialog;

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.edit_fragment_dialog,null);
        editViewModel = ViewModelProviders.of(getActivity()).get(EditViewModel.class);
           UpdateBtn = view.findViewById(R.id.updateBtn);
           CancelBtn = view.findViewById(R.id.cancelBtn);
           editTitle = view.findViewById(R.id.editTitle);
           editDescription = view.findViewById(R.id.editDesc);
           id = getArguments().getInt("id");
           subject = editViewModel.getSubject(id);

           subject.observe(getActivity(), new Observer<Subject>() {
               @Override
               public void onChanged(Subject subject) {
                   if(subject.getTitle()==null||subject.getDescription()==null){
                       editTitle.setText("");
                       editDescription.setText("");
                   }else {
                       editTitle.setText(subject.getTitle());
                       editDescription.setText(subject.getDescription());
                   }
               }
           });

           // to handle when click on update btn  . . . .;
           UpdateBtn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Subject subject = new Subject(editTitle.getText().toString(),editDescription.getText().toString());
                   subject.setId(id);
                   editViewModel.updateSubject(subject);
                   Toast.makeText(getActivity(), "update Sucessfully", Toast.LENGTH_SHORT).show();
               }
           });

           // to handle when click on cancel btn . . . . ;
        CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });



        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setCancelable(true)
                .create();
    }
}
