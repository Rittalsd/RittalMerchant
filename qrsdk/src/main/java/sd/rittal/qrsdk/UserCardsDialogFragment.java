package sd.rittal.qrsdk;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

import sd.rittal.qrsdk.beans.UserCard;
import sd.rittal.qrsdk.beans.UserCardAdapter;
import sd.rittal.qrsdk.beans.UserCardsOnClickListener;

public class UserCardsDialogFragment extends DialogFragment implements UserCardsOnClickListener {
    private final UserCardsOnClickListener userCardsOnClickListener;

    public UserCardsDialogFragment(UserCardsOnClickListener userCardsOnClickListener) {
        this.userCardsOnClickListener = userCardsOnClickListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getLayoutInflater().inflate(R.layout.user_card_dialog_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView img_add_card = view.findViewById(R.id.img_add_card);
        Button img_dialog_fav_close = view.findViewById(R.id.img_dialog_fav_close);
        RecyclerView recyclerView_cards = view.findViewById(R.id.recyclerView_cards);

        img_dialog_fav_close.setOnClickListener(kk -> {
            Objects.requireNonNull(UserCardsDialogFragment.this.getDialog()).dismiss();
        });
        img_add_card.setOnClickListener(lll -> {
            userCardsOnClickListener.onAddCard();
            Objects.requireNonNull(UserCardsDialogFragment.this.getDialog()).dismiss();
        });

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        Gson gson = new Gson();
        String json = sharedPrefs.getString("strList", "");
        Type type = new TypeToken<ArrayList<UserCard>>() {
        }.getType();
        ArrayList<UserCard> arrayList = gson.fromJson(json, type);
        if (arrayList != null) {
            UserCardAdapter adapter = new UserCardAdapter(arrayList, this);

            recyclerView_cards.setHasFixedSize(true);
            recyclerView_cards.setAdapter(adapter);
        }


    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;


    }

    @Override
    public void itemCustomerFavorite(UserCard card) {
        userCardsOnClickListener.itemCustomerFavorite(card);
        Objects.requireNonNull(UserCardsDialogFragment.this.getDialog()).dismiss();

    }

    @Override
    public void onAddCard() {
        userCardsOnClickListener.onAddCard();
        Objects.requireNonNull(UserCardsDialogFragment.this.getDialog()).dismiss();
    }
}
