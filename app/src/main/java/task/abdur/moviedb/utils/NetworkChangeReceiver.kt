package task.abdur.moviedb.utils
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class NetworkChangeReceiver(private val rootView: View) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action == ConnectivityManager.CONNECTIVITY_ACTION) {
            val isConnected = NetworkUtils.isNetworkConnected(context)

            if (isConnected) {
                // Network connectivity is back online
                showSnackbar("Network is back online")
            }else{
                showSnackbar("Network is back offline")
            }
        }
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).show()
    }
}
