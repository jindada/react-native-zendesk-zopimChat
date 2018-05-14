package com.jindada.RNZendeskZopimChat;

import android.content.Intent;
import android.app.Activity;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

import com.zopim.android.sdk.api.ZopimChat;
import com.zopim.android.sdk.model.VisitorInfo;
import com.zopim.android.sdk.prechat.ZopimChatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RNZendeskZopimChatModule extends ReactContextBaseJavaModule {

  public RNZendeskZopimChatModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public String getName() {
    return "RNZendeskZopimChat";
  }

  @ReactMethod
  public void initZopimChat(String key) {
    ZopimChat.init(key);
  }

  @ReactMethod
  public void startZopimChat(ReadableMap customFields) {
    String name = customFields.getString("name");
    String email = customFields.getString("email");
    String phone = customFields.getString("phone");
    VisitorInfo visitorData = new VisitorInfo.Builder()
      .name(name)
      .email(email)
      .phoneNumber(phone)
      .build();

    ZopimChat.setVisitorInfo(visitorData);

    Activity activity = getCurrentActivity();

    if(activity != null){
        Intent callZopimChat = new Intent(getReactApplicationContext(), ZopimChatActivity.class);
        callZopimChat.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getReactApplicationContext().startActivity(callZopimChat);
    }
  }
}
