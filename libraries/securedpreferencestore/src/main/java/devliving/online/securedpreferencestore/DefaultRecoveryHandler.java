package devliving.online.securedpreferencestore;

import android.content.SharedPreferences;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.List;


public class DefaultRecoveryHandler extends RecoveryHandler {
    @Override
    protected boolean recover(Exception e, KeyStore keyStore, List<String> keyAliases, SharedPreferences preferences) {
        Logger.e(e);

        try {
            clearKeyStore(keyStore, keyAliases);
            clearPreferences(preferences);
            return true;
        } catch (KeyStoreException e1) {
            Logger.e(e1);
        }

        return false;
    }
}
