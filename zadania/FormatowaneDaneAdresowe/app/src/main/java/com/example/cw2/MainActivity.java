package com.example.cw2;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cw2.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listViewContacts = findViewById(R.id.list_view_contacts);

        List<Contact> contacts = getContacts();
        ContactAdapter adapter = new ContactAdapter(this, contacts);
        listViewContacts.setAdapter(adapter);
    }

    @SuppressLint("Range")public ArrayList<Contact> getContacts() {
        ArrayList<Contact> contacts = new ArrayList<>();
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
                null,null, null);
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(cur
                        .getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur
                        .getString(cur
                                .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                if (Integer
                        .parseInt(cur.getString(cur
                                .getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {

                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                    + " = ?", new String[] { id }, null);
                    while (pCur.moveToNext()) {
                        Contact contact = new Contact();

                        String phoneNo = pCur
                                .getString(pCur
                                        .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                        contact.name = name;
                        contact.mobileNumber = phoneNo;

                        Cursor emailCur = cr.query(
                                ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                                new String[]{id}, null);
                        while (emailCur.moveToNext()) {
                            String email = emailCur.getString(emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));

                            contact.email = email;
                            contacts.add(contact);
                        }
                        emailCur.close();
                    }
                    pCur.close();
                }
            }
        }

        return contacts;
    }

    private static class ContactAdapter extends ArrayAdapter<Contact> {
        public ContactAdapter(Context context, List<Contact> contacts) {
            super(context, 0, contacts);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_contact, parent, false);
            }

            Contact contact = getItem(position);

            SpannableString name = new SpannableString(contact.getName());
            name.setSpan(new StyleSpan(Typeface.BOLD), 0, name.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            SpannableString phoneNumber = new SpannableString(contact.getMobileNumber());
            phoneNumber.setSpan(new UnderlineSpan(), 0, phoneNumber.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            SpannableString email = new SpannableString(contact.getEmail());
            email.setSpan(new StyleSpan(Typeface.ITALIC), 0, email.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            TextView nameTextView = convertView.findViewById(R.id.text_view_name);
            TextView phoneNumberTextView = convertView.findViewById(R.id.text_view_phone_number);
            TextView emailTextView = convertView.findViewById(R.id.text_view_email);

            nameTextView.setText(name);
            phoneNumberTextView.setText(phoneNumber);
            emailTextView.setText(email);

            return convertView;
        }
    }
}
