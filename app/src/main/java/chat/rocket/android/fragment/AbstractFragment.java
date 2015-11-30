package chat.rocket.android.fragment;

import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;

import chat.rocket.android.content.RocketChatDatabaseHelper;
import chat.rocket.android.model.ServerConfig;
import chat.rocket.android.model.User;

public class AbstractFragment extends Fragment {
    protected void finish(){
        if(getFragmentManager().getBackStackEntryCount()==0){
            getActivity().finish();
        }
        else {
            getFragmentManager().popBackStack();
        }
    }

    protected ServerConfig getPrimaryServerConfig() {
        return RocketChatDatabaseHelper.read(getContext(), new RocketChatDatabaseHelper.DBCallback<ServerConfig>() {
            @Override
            public ServerConfig process(SQLiteDatabase db) {
                return ServerConfig.getPrimaryConfig(db);
            }
        });
    }

    protected User getMe() {
        return RocketChatDatabaseHelper.read(getContext(), new RocketChatDatabaseHelper.DBCallback<User>() {
            @Override
            public User process(SQLiteDatabase db) {
                return User.getMe(db);
            }
        });
    }

}