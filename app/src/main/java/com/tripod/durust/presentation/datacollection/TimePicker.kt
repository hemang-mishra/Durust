package com.tripod.durust.presentation.datacollection

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tripod.durust.ui.theme.bodyFontFamily

data class TimeEntity(
    val hour: Int,
    val minute: Int,
    val amPm: String
)


@Preview
@Composable
fun PreviewTimePicker(){
    TimePickerCard(
        initialHour = 12,
        initialMinute = 30,
        initialAmPm = "PM",
        isEnabled = true,
        onTimeChanged = { hour, minute, amPm ->
            Log.i("TimePickerCard", "hour: $hour, minute: $minute, amPm: $amPm")
        }
    )
}

@Composable
fun TimePickerCard(
    initialHour: Int,
    initialMinute: Int,
    initialAmPm: String,
    isEnabled: Boolean,
    onTimeChanged: (Int, Int, String) -> Unit
) {
    val hours = remember { (1..12).map { it.toString().padStart(2, '0') } }
    val minutes = remember { (0..59).map { it.toString().padStart(2, '0') } }
    val amPm = remember { listOf("AM", "PM") }

    val hourState = rememberDatePickerState()
    val minuteState = rememberDatePickerState()
    val amPmState = rememberDatePickerState()

    LaunchedEffect(key1 = hourState.selectedItem, key2 = minuteState.selectedItem, key3 = amPmState.selectedItem) {
        try {
            Log.i(
                "TimePickerCard",
                "hourState: ${hourState.selectedItem}, minuteState: ${minuteState.selectedItem}, amPmState: ${amPmState.selectedItem}"
            )
            onTimeChanged(hourState.selectedItem.toInt(), minuteState.selectedItem.toInt(), amPmState.selectedItem)
        } catch (_: Exception) {
        }
    }

    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF7788F4),
        ),
        modifier = Modifier
            .shadow(
                elevation = 3.dp,
                spotColor = Color.Black,
                ambientColor = Color(0x40000000),
                shape = RoundedCornerShape(10.dp)
            )
            .border(
                BorderStroke(width = 0.75.dp, color = Color(0xFFEEF6F8)),
                shape = RoundedCornerShape(size = 10.dp)
            )
            .width(316.dp)
            .height(198.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                HeadingCard(text = "Hour")
                HeadingCard(text = "Minute")
                HeadingCard(text = "AM/PM")
            }
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .shadow(
                        elevation = 5.dp,
                        spotColor = Color(0x40000000),
                        ambientColor = Color(0x40000000)
                    )
                    .width(300.dp)
                    .height(145.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(size = 9.dp))
            ) {
                if (isEnabled) {
                    DatePicker(
                        items = hours,
                        state = hourState,
                        visibleItemsCount = 3,
                        textStyle = TextStyle(fontSize = 15.sp),
                        modifier = Modifier.weight(1f),
                        startIndex = initialHour - 1,
                        isEnabled = isEnabled
                    )
                    DatePicker(
                        modifier = Modifier.weight(1f),
                        items = minutes,
                        state = minuteState,
                        startIndex = initialMinute,
                        visibleItemsCount = 3,
                        textStyle = TextStyle(fontSize = 15.sp),
                        isEnabled = isEnabled
                    )
                    DatePicker(
                        modifier = Modifier.weight(1f),
                        items = amPm,
                        state = amPmState,
                        startIndex = amPm.indexOf(initialAmPm),
                        visibleItemsCount = 3,
                        textStyle = TextStyle(fontSize = 15.sp),
                        isEnabled = isEnabled
                    )
                } else {
                    Text(
                        text = initialHour.toString().padStart(2, '0'),
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = bodyFontFamily,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF454547)
                        ),
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = initialMinute.toString().padStart(2, '0'),
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = bodyFontFamily,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF454547)
                        ),
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = initialAmPm,
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = bodyFontFamily,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF454547)
                        ),
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}