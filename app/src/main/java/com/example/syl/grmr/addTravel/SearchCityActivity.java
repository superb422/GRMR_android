package com.example.syl.grmr.addTravel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.syl.grmr.Adapter.CityListviewAdapter;
import com.example.syl.grmr.R;

import java.util.ArrayList;
import java.util.List;

public class SearchCityActivity extends AppCompatActivity {


    static public List<String> list;          // 데이터를 넣은 리스트변수

    static public String searchText;
    private ListView listView;          // 검색을 보여줄 리스트변수
    private EditText editSearch;        // 검색어를 입력할 Input 창
    private CityListviewAdapter adapter;      // 리스트뷰에 연결할 아답터
    private ArrayList<String> arraylist;
    private ImageButton deleteButton; //입력한 텍스트 모두 지우는 버튼

    // X버튼 / 뒤로가기 동기화
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Back button pressed.", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_city);

        editSearch = (EditText) findViewById(R.id.city_editText);
        listView = (ListView) findViewById(R.id.city_listview);

        // 리스트를 생성한다.
        list = new ArrayList<String>();

        // 검색에 사용할 데이터을 미리 저장한다.(임의 데이터)
        settingList();

        // 리스트의 모든 데이터를 arraylist에 복사한다.// list 복사본을 만든다.
        arraylist = new ArrayList<String>();
        arraylist.addAll(list);
        searchText = "";

        // 리스트에 연동될 아답터를 생성한다.
        adapter = new CityListviewAdapter(list, this);

        // 리스트뷰에 아답터를 연결한다.
        listView.setAdapter(adapter);

        //도시 선택을 한 번 이상 한 후, 다시 선택하려 할 때, 바로 이전에 선택했던 도시 텍스트 가져오고 그 텍스트에 대해 검색된 리스트를 출력한다.
        if (CityListviewAdapter.clickedCity == true) {
            editSearch.setText(addTravelActivity.city_searchBar.getText());
            String text = editSearch.getText().toString();
            search(text);
        }

        /**************** Change Edit Text *****************************/
        editSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // input창에 문자를 입력할때마다 호출됨.

                String text;
                text = editSearch.getText().toString();
                search(text);

            }
        });

        /****************delete button click *****************************/
        deleteButton = (ImageButton) findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //텍스트가 없으면 이전 화면으로 간다. 추후 수정 가능성 있음.
                if (editSearch.getText().length() == 0) {
                    Intent addTravelIntent = new Intent(SearchCityActivity.this, addTravelActivity.class);
                    addTravelIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    SearchCityActivity.this.startActivity(addTravelIntent);
                }
                //텍스트가 존재 시, 모두 지운다.
                else {
                    editSearch.setHint("  도시를 입력해 주세요.");
                    editSearch.setText(null);
                }
            }
        });
    }

    /********************Searching method *****************************/
    public void search(String charText) {


        // 문자 입력시마다 리스트를 지우고 새로 뿌려줌.
        list.clear();


        searchText = charText;
        // 문자 입력이 없을때는 모든 데이터를 보여줌.
        if (charText.length() == 0) {

            list.addAll(arraylist);
        }
        // 문자 입력을 할때.
        else {
            // 리스트의 모든 데이터를 검색함.
            for (int i = 0; i < arraylist.size(); i++) {

                //입력단어 수가 리스트단어 수보다 많으면 substring에서 에러발생 일어나기 때문에 필터링.
                if (arraylist.get(i).length() >= charText.length()) {

                    // arraylist의 모든 데이터에 입력받은 단어(charText)가 맨 앞에서부터 포함되어 있으면 true를 반환함.
                    //substring으로 입력단어만큼 잘라주지 않으면, 단어가 중간이나 맨 뒤에 포함되어 있어도 필터링이 되지 않음.
                    if (arraylist.get(i).toLowerCase().substring(0, charText.length()).equals(charText) || arraylist.get(i).toUpperCase().substring(0, charText.length()).equals(charText)) {
                        //검색된 데이터 리스트에 추가
                        list.add(arraylist.get(i));

                    }
                }
            }
        }

        // 리스트 데이터가 변경되었으므로 어댑터를 갱신하여 검색된 데이터를 화면에 보여준다.
        adapter.notifyDataSetChanged();


    }

    /****************************Data List *****************************/
    // 검색에 사용될 데이터를 리스트에 추가한다. 추후 반복문으로 바꾼다.
    private void settingList() {
        list.add("OSAKA");
        list.add("OSAKA2");
        list.add("OSAKA3");
        list.add("OSAKA4");
        list.add("OSAKA5");
        list.add("OKINAWA");
        list.add("OKINAWA7");
        list.add("OKINAWA8");
        list.add("OKINAWA9");
        list.add("AYEON");
        list.add("AYEON2");
        list.add("AYEON3");
        list.add("AYEON4");
        list.add("AYEON5");
        list.add("SOYEON1");
        list.add("SOYEON2");
        list.add("SOYEON3");

    }

}
