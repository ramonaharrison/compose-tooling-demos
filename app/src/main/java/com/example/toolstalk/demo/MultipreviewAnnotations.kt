package com.example.toolstalk.demo

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.toolstalk.model.sampleRow2
import com.example.toolstalk.ui.WordRow
import com.example.toolstalk.ui.theme.ToolsTalkTheme

@Preview(
    name = "phone",
    group = "devices",
    device = "spec:width=411dp,height=891dp,dpi=420",
    showSystemUi = true
)
@Preview(
    name = "tablet",
    group = "devices",
    device = "spec:width=1280dp,height=800dp",
    showSystemUi = true
)
@Preview(
    name = "foldable",
    group = "devices",
    device = "spec:width=673.5dp,height=841dp",
    showSystemUi = true
)
@Preview(
    name = "desktop",
    group = "devices",
    device = "spec:width=1920dp,height=1080dp",
    showSystemUi = true
)
annotation class DevicePreviews

@Preview(
    name = "night theme",
    group = "themes",
    uiMode = UI_MODE_NIGHT_YES
)
annotation class ThemePreviews

@DevicePreviews
@ThemePreviews
annotation class CombinedPreviews

@CombinedPreviews
@Composable
fun DefaultPreview() {
    ToolsTalkTheme {
        WordRow(rowState = sampleRow2)
    }
}