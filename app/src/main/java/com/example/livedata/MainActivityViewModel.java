package com.example.livedata;

import android.app.Application;
import android.nfc.cardemulation.HostNfcFService;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivityViewModel extends AndroidViewModel {
    private String TAG = MainActivityViewModel.class.getSimpleName();

   // private MutableLiveData<List<MainActivityModel>> fruitList;
    private SubjectDao subjectDao ;
    private SubjectRoomDatabase database ;
    private LiveData<List<Subject>>subjectList;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        database = SubjectRoomDatabase.getInstance(application);
        subjectDao = database.subjectDao();
        subjectList = subjectDao.list();

    }
    // return all nodes from database . . . ;
    public LiveData<List<Subject>> getSubjectList(){

        return subjectList;
    }

 /*   LiveData<List<MainActivityModel>> getFruitList() {
        if (fruitList == null) {
            fruitList = new MutableLiveData<>();
            loadFruits();
        }
        return fruitList;
    }*/


    public void insertData(Subject subject){
        new InsertAsyncTask(subjectDao).execute(subject);

    }



    public void deleteSubject(Subject subject){
        new deleteAsyncTask(subjectDao).execute(subject);
    }

    private class deleteAsyncTask extends AsyncTask<Subject,Void,Void>{

        public SubjectDao subjectDao ;
        public deleteAsyncTask(SubjectDao subjectDao){
            this.subjectDao = subjectDao ;
        }

        @Override
        protected Void doInBackground(Subject... subjects) {
            subjectDao.deleteSubject(subjects[0]);
            return null;
        }
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "on cleared called");
    }

// create async task for insert method . . . .; ;
    private class InsertAsyncTask extends AsyncTask<Subject,Void,Void>{
        private SubjectDao subjectDao ;
        public InsertAsyncTask(SubjectDao subjectDao){
            this.subjectDao = subjectDao;
        }

    @Override
    protected Void doInBackground(Subject... subjects) {
            subjectDao.insert(subjects[0]);
        return null;
    }
}

  }

