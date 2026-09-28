package com.khuntalocal.app.ui.preview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.khuntalocal.app.data.SampleData
import com.khuntalocal.app.ui.components.BreakingNewsBanner
import com.khuntalocal.app.ui.components.NewsCard
import com.khuntalocal.app.ui.screens.onboarding.OnboardingScreen
import com.khuntalocal.app.ui.theme.KhuntaLocalTheme

@Preview(name = "News card", showBackground = true)
@Composable
private fun NewsCardPreview() {
    KhuntaLocalTheme {
        Surface {
            NewsCard(
                article = SampleData.articles[1],
                onClick = {},
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}

@Preview(name = "Breaking banner", showBackground = true)
@Composable
private fun BreakingBannerPreview() {
    KhuntaLocalTheme {
        Surface {
            BreakingNewsBanner(
                items = SampleData.articles.filter { it.isBreaking },
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            )
        }
    }
}

@Preview(name = "Onboarding", showBackground = true, heightDp = 900)
@Composable
private fun OnboardingPreview() {
    KhuntaLocalTheme {
        Column {
            OnboardingScreen(onGetStarted = {}, onLogin = {})
        }
    }
}
