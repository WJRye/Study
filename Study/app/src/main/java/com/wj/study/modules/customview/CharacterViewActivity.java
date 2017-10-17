package com.wj.study.modules.customview;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.wj.base.BaseActivity;
import com.wj.study.R;
import com.wj.study.adapter.CharacterViewAdapter;
import com.wj.study.domain.Person;
import com.wj.study.utils.PinYin;
import com.wj.study.view.CharacterView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharacterViewActivity extends BaseActivity {
    private Map<Character, Integer> mPositions = new HashMap<>();
    private static int PERMISSIONS_REQUEST_READ_CONTACTS = 0x123;
    private CharacterViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    PERMISSIONS_REQUEST_READ_CONTACTS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mAdapter.setPersons(getPersons());
                mAdapter.notifyDataSetChanged();
            } else {
                // Permission Denied
                Toast.makeText(CharacterViewActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void initViews(View view) {
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new CharacterViewAdapter();
        mAdapter.setPersons(getPersons());
        recyclerView.setAdapter(mAdapter);
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
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_character_view;
    }

    private List<Person> getPersons() {
        List<Person> persons = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            return persons;
        }
        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, new String[]{ContactsContract.Contacts.DISPLAY_NAME}, null, null, null);
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
}
