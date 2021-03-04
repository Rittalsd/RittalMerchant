package sd.rittal.qrsdk.beans;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sd.rittal.qrsdk.R;

public class UserCardAdapter extends RecyclerView.Adapter<UserCardAdapter.UserHolder> {

    private final ArrayList<UserCard> userCards;
    private final UserCardsOnClickListener listener;

    public UserCardAdapter(ArrayList<UserCard> userCards, UserCardsOnClickListener listener) {
        this.userCards = userCards;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cards, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        UserCard card = userCards.get(position);
        holder.card_name.setText(card.getCardName());
        holder.pan_number.setText(card.getCardNumber());
        holder.cardViewFavorite.setOnClickListener(lV -> {
            listener.itemCustomerFavorite(card);
        });

    }

    @Override
    public int getItemCount() {
        if (userCards.isEmpty()) return 0;
        else {
            return userCards.size();
        }

    }

    public class UserHolder extends RecyclerView.ViewHolder {
        private final TextView pan_number;
        private final TextView card_name;
        private final CardView cardViewFavorite;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            card_name = itemView.findViewById(R.id.card_name);
            pan_number = itemView.findViewById(R.id.pan_number);
            cardViewFavorite = itemView.findViewById(R.id.cardViewFavorite);

        }
    }
}
