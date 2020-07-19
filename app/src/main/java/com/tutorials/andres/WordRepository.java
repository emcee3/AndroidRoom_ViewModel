package com.tutorials.andres;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.tutorials.andres.db.WordRoomDatabase;
import com.tutorials.andres.db.dao.WordDao;
import com.tutorials.andres.db.entity.Word;

import java.util.List;

public class WordRepository {

    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAlphabetizedWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        WordRoomDatabase.databaseWriterExecutor.execute(() -> {
            mWordDao.insert(word);
        });
    }
}
