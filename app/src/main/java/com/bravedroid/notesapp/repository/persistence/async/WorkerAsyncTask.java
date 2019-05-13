package com.bravedroid.notesapp.repository.persistence.async;

import android.os.AsyncTask;

public class WorkerAsyncTask extends AsyncTask<Void, Void, Void> {
    private final Runnable runnable;

    public WorkerAsyncTask(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        runnable.run();
        return null;
    }
}
