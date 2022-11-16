import android.location.Location
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GPSContent(location: Location?) {
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Text(
            text = "Location",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        if(location !== null) {
            Text("Latitude: ${location.latitude}, Longitude: ${location.longitude}")
        } else {
            Text("Loading")
        }
    }
}