package com.example.volunteerapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.volunteerapp.ui.theme.VolunteerAppTheme

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VolunteerAppTheme {
        Greeting("Android")
    }
}