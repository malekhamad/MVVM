package com.example.livedata;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class EditViewModel extends AndroidViewModel {
    SubjectRoomDatabase database ;
    SubjectDao dao ;
    public EditViewModel(@NonNull Application application) {
        super(application);
         database =SubjectRoomDatabase.getInstance(application);
         dao = database.subjectDao();

    }

    public LiveData<Subject> getSubject(int id){
        return dao.getSubject(id);
    }


    public void updateSubject(Subject subject){
        new updateAsyncTask(dao).execute(subject);
    }


    private class updateAsyncTask extends AsyncTask<Subject,Void,Void>{

        public SubjectDao subjectDao ;
        public updateAsyncTask(SubjectDao subjectDao){
            this.subjectDao = subjectDao;
        }


        @Override
        protected Void doInBackground(Subject... subjects) {
            subjectDao.updateSubject(subjects[0]);
            return null ;
        }
    }
}
