package com.utad.kiran.presentationform;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity{

    private MutableLiveData<Boolean> mLiveWork = new MutableLiveData<>();
    private  MutableLiveData<Boolean> mLivePass = new MutableLiveData<>();
    private  MutableLiveData<Integer> mLiveAge = new MutableLiveData<>();
    @BindView(R.id.workSwitch) Switch workSwitch;
    @BindView(R.id.passSwitch) Switch passSwitch;
    @BindView(R.id.workAnswer)  TextView workAnswer;
    @BindView(R.id.passAnswer) TextView passAnswer;
    @BindView(R.id.ageAnswer) TextView ageAnswer;
    @BindView(R.id.ageText) EditText ageText;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        configureObserversWork();
        configureObserversPass();
        configureObserversAge();
        onClickWork();
        onClickPass();
        listenerAge();
    }

    private void configureObserversWork(){
        mLiveWork.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean workAnswerSwitch) {
                if (workAnswerSwitch == true){
                    workAnswer.setText(R.string.workYes);
                }else if (workAnswerSwitch == false){
                    workAnswer.setText(R.string.workNo);
                }
            }
        });
    }

    private void configureObserversPass(){
        mLivePass.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean passAnswerSwitch) {
                if (passAnswerSwitch == true){
                    passAnswer.setText(R.string.passYes);
                }else if (passAnswerSwitch == false){
                    passAnswer.setText(R.string.passNo);
                }
            }
        });
    }

    private void configureObserversAge(){
        mLiveAge.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer s) {
                if (!s.equals(null)){
                    ageAnswer.setText("Tengo " + s + " años");
                }
            }
        });
    }

    public void onClickWork(){
        workSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    mLiveWork.postValue(true);
                }else{
                    mLiveWork.postValue(false);
                }
            }
        });
    }

    public void onClickPass(){
        passSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    mLivePass.postValue(true);
                }else{
                    mLivePass.postValue(false);
                }
            }
        });
    }

    public void listenerAge() {
        ageText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    if(s.equals(null)) {
                        mLiveAge.postValue(Integer.valueOf(s.toString()));
                    }else{
                        mLiveAge.postValue(0);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                try{
                    if(s!=null) {
                        mLiveAge.postValue(Integer.valueOf(s.toString()));
                    }else{
                        mLiveAge.postValue(0);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    if(s!=null) {
                        mLiveAge.postValue(Integer.valueOf(s.toString()));
                    }else{
                        mLiveAge.postValue(0);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
