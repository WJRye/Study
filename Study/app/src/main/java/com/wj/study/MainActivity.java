package com.wj.study;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.wj.study.adapter.RecyclerViewAdapter;
import com.wj.study.domain.Person;
import com.wj.study.util.PinYin;
import com.wj.study.view.CharacterView;
import com.wj.study.view.CircleProgressView;
import com.wj.study.view.ProgressView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Map<Character, Integer> mPositions = new HashMap<>();

    private float mStart = 0.1f;
    private ProgressView mProgressView;
    private CircleProgressView mCircleProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerViewAdapter(getPersons()));

        CharacterView characterView = (CharacterView) findViewById(R.id.character_view);
        characterView.insertFirst('#');
        characterView.setColor(Color.BLUE);
        characterView.setTextSize(getResources().getDimensionPixelSize(R.dimen.textSize_14sp));
        characterView.setOnCharacterTouchListener(new CharacterView.OnCharacterTouchListener() {
            @Override
            public void onDown(View view, char c) {
                Log.d("TAG", "onDown=" + c);
                Integer position = mPositions.get(Character.valueOf(c));
                if (position != null) {
                    recyclerView.scrollToPosition(position.intValue());
                }
            }

            @Override
            public void onMove(View view, char c) {
                Log.d("TAG", "onMove=" + c);
                Integer position = mPositions.get(Character.valueOf(c));
                if (position != null)
                    recyclerView.scrollToPosition(position.intValue());
                Toast.makeText(view.getContext(), String.valueOf(c), Toast.LENGTH_SHORT).show();
            }
        });

        mProgressView = (ProgressView) findViewById(R.id.progress_view);
        mProgressView.setIsShowPercentText(true);
        mProgressView.setPercentTextSize(getResources().getDimensionPixelSize(R.dimen.textSize_14sp));
        mCircleProgressView = (CircleProgressView) findViewById(R.id.circle_progress_view);

    }


    private List<Person> getPersons() {

        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, new String[]{ContactsContract.Contacts.DISPLAY_NAME}, null, null, null);
        List<Person> persons = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Person person = new Person();
                person.setName(cursor.getString(0));
                person.setPinyin(PinYin.getPinYin(cursor.getString(0)));
                person.setType(Person.TYPE_CONTENT);
                persons.add(person);
            }
            cursor.close();
            cursor = null;
        }

        Collections.sort(persons);

        char lastCharacter = ' ';
        char currentCharacter = ' ';
        int size = persons.size();
        List<Person> newPersons = new ArrayList<>(size * 2);
        for (int i = 0; i < size; i++) {
            Person oldPerson = persons.get(i);
            currentCharacter = oldPerson.getPinyin().charAt(0);
            if (lastCharacter != currentCharacter) {
                Person person = new Person();
                person.setName(String.valueOf(Character.toUpperCase(currentCharacter)));
                person.setType(Person.TYPE_TITLE);
                newPersons.add(person);
                mPositions.put(Character.toUpperCase(currentCharacter), newPersons.size() - 1);
                lastCharacter = currentCharacter;
            }
            newPersons.add(oldPerson);
        }
        persons.clear();
        persons = null;
        return newPersons;
    }

    public void onClick(View view) {
        mCircleProgressView.show(CircleProgressView.TYPE_THREE);
        mProgressView.setProgressGradually(1.0f);
    }

    @Override
    public void onStop() {
        mCircleProgressView.dismiss();
        super.onStop();
    }

}
