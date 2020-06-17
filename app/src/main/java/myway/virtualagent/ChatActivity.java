package myway.virtualagent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import com.freshchat.consumer.sdk.Freshchat;
import com.freshchat.consumer.sdk.FreshchatConfig;
import com.freshchat.consumer.sdk.FreshchatImageLoader;
import com.freshchat.consumer.sdk.FreshchatImageLoaderRequest;
import com.freshchat.consumer.sdk.FreshchatUser;
import com.freshchat.consumer.sdk.exception.MethodNotAllowedException;
import com.freshchat.consumer.sdk.j.af;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class ChatActivity extends AppCompatActivity {

    private Object Context;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_chat);

        FreshchatConfig freshchatConfig = new FreshchatConfig("4ac30fe6-e9ca-4cd2-8044-1110a044c6e3","83f4f757-9f2f-4597-9095-df61dd93cc35");
        freshchatConfig.setCameraCaptureEnabled(true);
        freshchatConfig.setGallerySelectionEnabled(true);


//        Freshchat.getInstance(getApplicationContext()).init(freshchatConfig);
        FreshchatUser freshUser=Freshchat.getInstance(getApplicationContext()).getUser();
       // Freshchat.getInstance(getApplicationContext()).init(freshchatConfig);
        freshUser.setFirstName("Abdulloh");
        freshUser.setLastName("Maxmudov");
        freshUser.setEmail("maxmudovabdullo97@gmail.com");
        freshUser.setPhone("+998", "933769197");

        Freshchat.setImageLoader(Objects.requireNonNull(af.aw((android.content.Context) Context)));
        try {
            Freshchat.getInstance(getApplicationContext()).setUser(freshUser);
        } catch (MethodNotAllowedException e) {
            e.printStackTrace();
        }
        Freshchat.getInstance(getApplicationContext()).init(freshchatConfig);
        Freshchat.showConversations(getApplicationContext());
    }
}
